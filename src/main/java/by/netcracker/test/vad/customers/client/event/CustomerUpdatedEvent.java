package by.netcracker.test.vad.customers.client.event;

import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.event.shared.GwtEvent;

public class CustomerUpdatedEvent extends GwtEvent<CustomerUpdatedEventHandler>{
  public static Type<CustomerUpdatedEventHandler> TYPE = new Type<CustomerUpdatedEventHandler>();
  private final Customer updatedCustomer;
  
  public CustomerUpdatedEvent(Customer updatedCustomer) {
    this.updatedCustomer = updatedCustomer;
  }
  
  public Customer getUpdatedCustomer() { return updatedCustomer; }
  

  @Override
  public Type<CustomerUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CustomerUpdatedEventHandler handler) {
    handler.onCustomerUpdated(this);
  }
}
