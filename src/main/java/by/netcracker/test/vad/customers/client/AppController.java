package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.client.event.*;
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
  private final HandlerManager eventBus;
  private final CustomerServiceAsync rpcService;
  private HasWidgets container;
  
  public AppController(CustomerServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(AddCustomerEvent.TYPE,
        new AddCustomerEventHandler() {
          public void onAddCustomer(AddCustomerEvent event) {
            doAddNewCustomer();
          }
        });  

    eventBus.addHandler(EditCustomerEvent.TYPE,
        new EditCustomerEventHandler() {
          public void onEditCustomer(EditCustomerEvent event) {
            doEditCustomer(event.getId());
          }
        });  

    eventBus.addHandler(EditCustomerCancelledEvent.TYPE,
        new EditCustomerCancelledEventHandler() {
          public void onEditCustomerCancelled(EditCustomerCancelledEvent event) {
            doEditCustomerCancelled();
          }
        });  

    eventBus.addHandler(CustomerUpdatedEvent.TYPE,
        new CustomerUpdatedEventHandler() {
          public void onCustomerUpdated(CustomerUpdatedEvent event) {
            doCustomerUpdated();
          }
        });  
  }
  
  private void doAddNewCustomer() {
    History.newItem("add");
  }
  
  private void doEditCustomer(Integer id) {
    History.newItem("edit", false);
    Presenter presenter = new EditCustomerPresenter(rpcService, eventBus, new EditCustomerView(), id);
    presenter.go(container);
  }
  
  private void doEditCustomerCancelled() {
    History.newItem("list");
  }
  
  private void doCustomerUpdated() {
    History.newItem("list");
  }
  
  public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("list");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      Presenter presenter = null;

      if (token.equals("list")) {
        presenter = new CustomerPresenter(rpcService, eventBus, new CustomerView());
      }
      else if (token.equals("add")) {
        presenter = new EditCustomerPresenter(rpcService, eventBus, new EditCustomerView());
      }
      else if (token.equals("edit")) {
        presenter = new EditCustomerPresenter(rpcService, eventBus, new EditCustomerView());
      }
      
      if (presenter != null) {
        presenter.go(container);
      }
    }
  } 
}
