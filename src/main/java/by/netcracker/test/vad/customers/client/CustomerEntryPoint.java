package by.netcracker.test.vad.customers.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class CustomerEntryPoint implements EntryPoint {

  public void onModuleLoad() {
    CustomerServiceAsync rpcCustomerService = GWT.create(CustomerService.class);
    CustomerTypeServiceAsync rpcCustomerTypeService = GWT.create(CustomerTypeService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcCustomerService, rpcCustomerTypeService, eventBus);
    appViewer.go(RootPanel.get());
  }
}
