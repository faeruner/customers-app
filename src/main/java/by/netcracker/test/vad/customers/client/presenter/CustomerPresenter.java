package by.netcracker.test.vad.customers.client.presenter;

import by.netcracker.test.vad.customers.client.CustomerServiceAsync;
import by.netcracker.test.vad.customers.client.event.AddCustomerEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerEvent;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CustomerPresenter implements Presenter {

  private List<Customer> customerList;

  public interface Display {
    HasClickHandlers getAddButton();
    HasClickHandlers getDeleteButton();
    HasClickHandlers getList();
    void setData(List<String> data);
    int getClickedRow(ClickEvent event);
    List<Integer> getSelectedRows();
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
    display.getAddButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new AddCustomerEvent());
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        deleteSelectedCustomer();
      }
    });

    display.getList().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int selectedRow = display.getClickedRow(event);

        if (selectedRow >= 0) {
          Integer id = customerList.get(selectedRow).getCustomerId();
          eventBus.fireEvent(new EditCustomerEvent(id));
        }
      }
    });
  }

  public void go(final HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
    fetchCustomers();
  }

  public void sortCustomers() {

    // Yes, we could use a more optimized method of sorting, but the
    //  point is to create a test case that helps illustrate the higher
    //  level concepts used when creating MVP-based applications.
    //
    for (int i = 0; i < customerList.size(); ++i) {
      for (int j = 0; j < customerList.size() - 1; ++j) {
        if (customerList.get(j).getLastName().compareToIgnoreCase(customerList.get(j + 1).getLastName()) >= 0) {
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

  private void fetchCustomers() {
    rpcService.findAll(new AsyncCallback<List<Customer>>() {
      public void onSuccess(List<Customer> result) {
        customerList = result;
        sortCustomers();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < result.size(); ++i) {
          data.add(customerList.get(i).getDisplayName());
        }

        display.setData(data);
      }
      public void onFailure(Throwable caught) {
        Window.alert("Error fetching customers"+caught.getMessage());
      }
    });
  }

  private void deleteSelectedCustomer() {
    List<Integer> selectedRows = display.getSelectedRows();

    for (int i = 0; i < selectedRows.size(); ++i) {
      rpcService.delete(customerList.get(selectedRows.get(i)), new AsyncCallback<Void>() {
        public void onSuccess(Void result) {
        }

        public void onFailure(Throwable caught) {
          Window.alert("Error fetching customers");
        }
      });
    }
    fetchCustomers();
  }
}
