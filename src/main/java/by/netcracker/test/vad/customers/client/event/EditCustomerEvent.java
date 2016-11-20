package by.netcracker.test.vad.customers.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditCustomerEvent extends GwtEvent<EditCustomerEventHandler>{
  public static Type<EditCustomerEventHandler> TYPE = new Type<EditCustomerEventHandler>();
  private final Integer id;
  
  public EditCustomerEvent(Integer id) {
    this.id = id;
  }
  
  public Integer getId() { return id; }
  
  @Override
  public Type<EditCustomerEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditCustomerEventHandler handler) {
    handler.onEditCustomer(this);
  }
}
