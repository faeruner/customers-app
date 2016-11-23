package by.netcracker.test.vad.customers.client.presenter;

import by.netcracker.test.vad.customers.client.CustomerServiceAsync;
import by.netcracker.test.vad.customers.client.CustomerTypeServiceAsync;
import by.netcracker.test.vad.customers.client.Messages;
import by.netcracker.test.vad.customers.client.event.CustomerUpdatedEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerCancelledEvent;
import by.netcracker.test.vad.customers.shared.Customer;
import by.netcracker.test.vad.customers.shared.CustomerType;
import by.netcracker.test.vad.customers.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EditCustomerPresenter implements Presenter {

    private final Messages messages = (Messages) GWT.create(Messages.class);

    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getCustomerTitle();

        void setTitleHelp(String help);

        HasValue<String> getFirstName();

        void setFirstNameHelp(String help);

        HasValue<String> getLastName();

        void setLastNameHelp(String help);

        HasValue<Date> getCustomerModifiedWhen();

        void setTypes(List<String> data);

        void setSelectedType(String type);

        String getSelectedType();

        Widget asWidget();
    }

    private Customer customer;
    private List<CustomerType> typesList;
    private final CustomerServiceAsync rpcCustomerService;
    private final CustomerTypeServiceAsync rpcTypeService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditCustomerPresenter(CustomerServiceAsync rpcCustomerService, CustomerTypeServiceAsync rpcTypeService, HandlerManager eventBus, Display display) {
        this.rpcCustomerService = rpcCustomerService;
        this.rpcTypeService = rpcTypeService;
        this.eventBus = eventBus;
        this.customer = new Customer();
        this.display = display;
        fillCustomerType();
        bind();
    }

    public EditCustomerPresenter(CustomerServiceAsync rpcCustomerService, CustomerTypeServiceAsync rpcTypeService, HandlerManager eventBus, Display display, Integer id) {
        this.rpcCustomerService = rpcCustomerService;
        this.rpcTypeService = rpcTypeService;
        this.eventBus = eventBus;
        this.display = display;
        fillCustomerType();
        bind();

        rpcCustomerService.findOne(id, new AsyncCallback<Customer>() {
            public void onSuccess(Customer result) {
                customer = result;
                EditCustomerPresenter.this.display.getCustomerTitle().setValue(customer.getTitle(), true);
                EditCustomerPresenter.this.display.getFirstName().setValue(customer.getFirstName(), true);
                EditCustomerPresenter.this.display.getLastName().setValue(customer.getLastName(), true);
                EditCustomerPresenter.this.display.getCustomerModifiedWhen().setValue(customer.getModifiedWhen());
                EditCustomerPresenter.this.display.setSelectedType(customer.getType().getCustomerTypeCaption());
            }

            public void onFailure(Throwable caught) {
                Window.alert(messages.retrievingError());
            }
        });

    }

    private void bind() {
        this.display.getSaveButton().addClickHandler(event -> doSave());
        this.display.getCancelButton().addClickHandler(event -> eventBus.fireEvent(new EditCustomerCancelledEvent()));
        this.display.getCustomerTitle().addValueChangeHandler(valueChangeEvent -> display.setTitleHelp(FieldVerifier.validateInput(valueChangeEvent.getValue())));
        this.display.getFirstName().addValueChangeHandler(valueChangeEvent -> display.setFirstNameHelp(FieldVerifier.validateInput(valueChangeEvent.getValue())));
        this.display.getLastName().addValueChangeHandler(valueChangeEvent -> display.setLastNameHelp(FieldVerifier.validateInput(valueChangeEvent.getValue())));
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {

        String help = FieldVerifier.validateInput(display.getCustomerTitle().getValue());
        display.setTitleHelp(help);
        Boolean valid = help.isEmpty();

        help = FieldVerifier.validateInput(display.getFirstName().getValue());
        display.setFirstNameHelp(help);
        valid &= help.isEmpty();

        help = FieldVerifier.validateInput(display.getLastName().getValue());
        display.setLastNameHelp(help);
        valid &= help.isEmpty();

        if (!valid) return;

        customer.setTitle(display.getCustomerTitle().getValue());
        customer.setFirstName(display.getFirstName().getValue());
        customer.setLastName(display.getLastName().getValue());
        customer.setModifiedWhen(display.getCustomerModifiedWhen().getValue());

        String type = display.getSelectedType();

        typesList.forEach(item -> {
            if (item.getCustomerTypeCaption().equals(type)) {
                customer.setType(item);
            }
        });

        rpcCustomerService.save(customer, new AsyncCallback<Customer>() {
            public void onSuccess(Customer result) {
                eventBus.fireEvent(new CustomerUpdatedEvent(result));
            }

            public void onFailure(Throwable caught) {
                Window.alert(messages.updatingError());
            }
        });
    }

    private void fillCustomerType() {
        rpcTypeService.findAll(new AsyncCallback<List<CustomerType>>() {
            public void onSuccess(List<CustomerType> result) {
                typesList = result;
                List<String> data = new ArrayList<>();
                result.forEach(item -> data.add(item.getCustomerTypeCaption()));
                EditCustomerPresenter.this.display.setTypes(data);
            }

            public void onFailure(Throwable caught) {
                Window.alert(messages.retrievingError());
            }
        });
    }
}
