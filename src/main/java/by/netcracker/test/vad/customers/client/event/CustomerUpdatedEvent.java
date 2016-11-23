package by.netcracker.test.vad.customers.client.event;

import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.event.shared.GwtEvent;

public class CustomerUpdatedEvent extends GwtEvent<CustomerUpdatedEventHandler>{
    public static final Type<CustomerUpdatedEventHandler> TYPE = new Type<>();
  private final Customer updatedCustomer;
  
  public CustomerUpdatedEvent(Customer updatedCustomer) {
    this.updatedCustomer = updatedCustomer;
  }
  
  @Override
  public Type<CustomerUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CustomerUpdatedEventHandler handler) {
    handler.onCustomerUpdated(this);
  }
}
