package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface CustomerDeletedEventHandler extends EventHandler {
  void onCustomerDeleted(CustomerDeletedEvent event);
}
