package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddCustomerEventHandler extends EventHandler  {
    void onAddCustomer(AddCustomerEvent event);
}
