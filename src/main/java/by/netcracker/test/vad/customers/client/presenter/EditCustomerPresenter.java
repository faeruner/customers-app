package by.netcracker.test.vad.customers.client.presenter;

import by.netcracker.test.vad.customers.client.CustomerServiceAsync;
import by.netcracker.test.vad.customers.client.CustomerTypeServiceAsync;
import by.netcracker.test.vad.customers.client.event.CustomerUpdatedEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerCancelledEvent;
import by.netcracker.test.vad.customers.shared.Customer;
import by.netcracker.test.vad.customers.shared.CustomerType;
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
    List<CustomerType> typesList;
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
                Window.alert("Error retrieving customer");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(event -> doSave());
        this.display.getCancelButton().addClickHandler(event -> eventBus.fireEvent(new EditCustomerCancelledEvent()));
        this.display.getCustomerTitle().addValueChangeHandler(valueChangeEvent -> EditCustomerPresenter.this.display.setTitleHelp(validate(valueChangeEvent.getValue())));
        this.display.getFirstName().addValueChangeHandler(valueChangeEvent -> EditCustomerPresenter.this.display.setFirstNameHelp(validate(valueChangeEvent.getValue())));
        this.display.getLastName().addValueChangeHandler(valueChangeEvent -> EditCustomerPresenter.this.display.setLastNameHelp(validate(valueChangeEvent.getValue())));
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {

        if (!validate(display.getCustomerTitle().getValue(),
                display.getFirstName().getValue(),
                display.getLastName().getValue()).isEmpty()) return;

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
                Window.alert("Error updating customer");
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
                Window.alert("Error retrieving customer");
            }
        });

    }

    private String validate(String... input) {
        String help = "";
        for (String t : input) {
            if (t == null || t.trim().equals("")) {
                help = "Value is empty!";
                break;
            } else if (!t.matches("^[- a-zA-Z]*$")) {
                help = "Only letters is allowed!";
                break;
            }
        }

        return help;
    }

}
