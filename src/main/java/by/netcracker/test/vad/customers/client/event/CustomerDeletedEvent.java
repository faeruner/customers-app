package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CustomerDeletedEvent extends GwtEvent<CustomerDeletedEventHandler>{
  public static Type<CustomerDeletedEventHandler> TYPE = new Type<CustomerDeletedEventHandler>();
  
  @Override
  public Type<CustomerDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CustomerDeletedEventHandler handler) {
    handler.onCustomerDeleted(this);
  }
}
