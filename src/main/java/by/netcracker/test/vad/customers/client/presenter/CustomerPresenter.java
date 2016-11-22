package by.netcracker.test.vad.customers.client.presenter;

import by.netcracker.test.vad.customers.client.CustomerServiceAsync;
import by.netcracker.test.vad.customers.client.event.AddCustomerEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerEvent;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class CustomerPresenter implements Presenter {

    private List<Customer> customerList;

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getEditButton();

        HasClickHandlers getDeleteButton();

        HasClickHandlers getFindButton();

        HasValue<String> getFindText();

        void setData(List<Customer> data);

        Customer getSelectedRow();

        Widget asWidget();
    }

    private final CustomerServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public CustomerPresenter(CustomerServiceAsync rpcService, HandlerManager eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {
        display.getAddButton().addClickHandler(event -> eventBus.fireEvent(new AddCustomerEvent()));
        display.getEditButton().addClickHandler(event -> {
            Customer selected = display.getSelectedRow();
            if (selected != null) {
                Integer id = selected.getCustomerId();
                eventBus.fireEvent(new EditCustomerEvent(id));
            }
        });

        display.getDeleteButton().addClickHandler(event -> {
            Customer selected = display.getSelectedRow();
            if (selected != null) {
                deleteSelectedCustomer(selected);
            }
        });

        display.getFindButton().addClickHandler(event -> rpcService.findByName(display.getFindText().getValue(), new ListAsyncCallback()));
    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        rpcService.findAll(new ListAsyncCallback());
    }

    public void sortCustomers() {
        for (int i = 0; i < customerList.size(); ++i) {
            for (int j = 0; j < customerList.size() - 1; ++j) {
                if (customerList.get(j).getModifiedWhen().compareTo(customerList.get(j + 1).getModifiedWhen()) < 0) {
                    Customer tmp = customerList.get(j);
                    customerList.set(j, customerList.get(j + 1));
                    customerList.set(j + 1, tmp);
                }
            }
        }
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Customer getContactDetail(int index) {
        return customerList.get(index);
    }

    private class ListAsyncCallback implements AsyncCallback<List<Customer>> {
        public void onSuccess(List<Customer> result) {
            CustomerPresenter.this.customerList = result;
            sortCustomers();

            List<Customer> data = new ArrayList<>();
            data.addAll(result);

            display.setData(data);
        }

        public void onFailure(Throwable caught) {
            Window.alert("Error fetching customers" + caught.getMessage());
        }
    }

    private void deleteSelectedCustomer(Customer customer) {
        rpcService.delete(customer, new AsyncCallback<Void>() {
            public void onSuccess(Void result) {
                rpcService.findByName(display.getFindText().getValue(), new ListAsyncCallback());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error deleting customer");
            }
        });
    }
}
