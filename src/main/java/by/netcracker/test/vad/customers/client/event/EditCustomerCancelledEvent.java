package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCustomerCancelledEvent extends GwtEvent<EditCustomerCancelledEventHandler>{
    public static final Type<EditCustomerCancelledEventHandler> TYPE = new Type<>();
  
  @Override
  public Type<EditCustomerCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCustomerCancelledEventHandler handler) {
    handler.onEditCustomerCancelled(this);
  }
}
