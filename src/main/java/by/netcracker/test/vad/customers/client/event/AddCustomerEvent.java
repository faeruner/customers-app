package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddCustomerEvent extends GwtEvent<AddCustomerEventHandler> {
    public static final Type<AddCustomerEventHandler> TYPE = new Type<>();
  
  @Override
  public Type<AddCustomerEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddCustomerEventHandler handler) {
    handler.onAddCustomer(this);
  }
}
