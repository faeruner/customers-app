package by.netcracker.test.vad.customers.client.presenter;

import by.netcracker.test.vad.customers.client.CustomerServiceAsync;
import by.netcracker.test.vad.customers.client.event.CustomerUpdatedEvent;
import by.netcracker.test.vad.customers.client.event.EditCustomerCancelledEvent;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback; 
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;


public class EditCustomerPresenter implements Presenter {

  public interface Display {
    HasClickHandlers getSaveButton();
    HasClickHandlers getCancelButton();
    HasValue<String> getFirstName();
    HasValue<String> getLastName();
    HasValue<String> getCustomerTitle();
    Widget asWidget();
  }
  
  private Customer customer;
  private final CustomerServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public EditCustomerPresenter(CustomerServiceAsync rpcService, HandlerManager eventBus, Display display) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.customer = new Customer();
    this.display = display;
    bind();
  }
  
  public EditCustomerPresenter(CustomerServiceAsync rpcService, HandlerManager eventBus, Display display, Integer id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();

    rpcService.findOne(id, new AsyncCallback<Customer>() {
      public void onSuccess(Customer result) {
        customer = result;
        EditCustomerPresenter.this.display.getFirstName().setValue(customer.getFirstName());
        EditCustomerPresenter.this.display.getLastName().setValue(customer.getLastName());
        EditCustomerPresenter.this.display.getCustomerTitle().setValue(customer.getTitle());
      }

      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving customer");
      }
    });

  }
  
  public void bind() {
    this.display.getSaveButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditCustomerCancelledEvent());
      }
    });
  }

  public void go(final HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave() {
    customer.setFirstName(display.getFirstName().getValue());
    customer.setLastName(display.getLastName().getValue());
    customer.setTitle(display.getCustomerTitle().getValue());

    rpcService.save(customer, new AsyncCallback<Customer>() {
        public void onSuccess(Customer result) {
          eventBus.fireEvent(new CustomerUpdatedEvent(result));
        }
        public void onFailure(Throwable caught) {
          Window.alert("Error updating customer");
        }
    });
  }
  
}
