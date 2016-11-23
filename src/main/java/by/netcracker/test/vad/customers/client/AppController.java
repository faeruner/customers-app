package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.client.event.AddCustomerEvent;
import by.netcracker.test.vad.customers.client.event.CustomerUpdatedEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerCancelledEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerEvent;
import by.netcracker.test.vad.customers.client.presenter.CustomerPresenter;
import by.netcracker.test.vad.customers.client.presenter.EditCustomerPresenter;
import by.netcracker.test.vad.customers.client.presenter.Presenter;
import by.netcracker.test.vad.customers.client.view.CustomerView;
import by.netcracker.test.vad.customers.client.view.EditCustomerView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

    private static final String EVENT_LIST = "list";
    private static final String EVENT_ADD = "add";
    private static final String EVENT_EDIT = "edit";

    private final HandlerManager eventBus;
    private final CustomerServiceAsync rpcCustomerService;
    private final CustomerTypeServiceAsync rpcCustomerTypeService;
    private HasWidgets container;

    public AppController(CustomerServiceAsync rpcCustomerService, CustomerTypeServiceAsync rpcCustomerTypeService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcCustomerService = rpcCustomerService;
        this.rpcCustomerTypeService = rpcCustomerTypeService;

        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
        eventBus.addHandler(AddCustomerEvent.TYPE, event -> doAddNewCustomer());
        eventBus.addHandler(EditCustomerEvent.TYPE, event -> doEditCustomer(event.getId()));
        eventBus.addHandler(EditCustomerCancelledEvent.TYPE, event -> doEditCustomerCancelled());
        eventBus.addHandler(CustomerUpdatedEvent.TYPE, event -> doCustomerUpdated());
    }

    private void doAddNewCustomer() {
        History.newItem(EVENT_ADD);
    }

    private void doEditCustomer(Integer id) {
        History.newItem(EVENT_EDIT, false);
        Presenter presenter = new EditCustomerPresenter(rpcCustomerService, rpcCustomerTypeService, eventBus, new EditCustomerView(), id);
        presenter.go(container);
    }

    private void doEditCustomerCancelled() {
        History.newItem(EVENT_LIST);
    }

    private void doCustomerUpdated() {
        History.newItem(EVENT_LIST);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem(EVENT_LIST);
        } else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            switch (token) {
                case EVENT_LIST:
                    presenter = new CustomerPresenter(rpcCustomerService, eventBus, new CustomerView());
                    break;
                case EVENT_ADD:
                    presenter = new EditCustomerPresenter(rpcCustomerService, rpcCustomerTypeService, eventBus, new EditCustomerView());
                    break;
                case EVENT_EDIT:
                    presenter = new EditCustomerPresenter(rpcCustomerService, rpcCustomerTypeService, eventBus, new EditCustomerView());
                    break;
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
