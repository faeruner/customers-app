package by.netcracker.test.vad.customers.client.view;

import by.netcracker.test.vad.customers.client.presenter.CustomerPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class CustomerView extends Composite implements CustomerPresenter.Display {
  private final Button addButton;
  private final Button deleteButton;
  private FlexTable customerTable;
  private final FlexTable contentTable;
  
  public CustomerView() {
    DecoratorPanel contentTableDecorator = new DecoratorPanel();
    initWidget(contentTableDecorator);
    contentTableDecorator.setWidth("100%");
    contentTableDecorator.setWidth("18em");

    contentTable = new FlexTable();
    contentTable.setWidth("100%");
    contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListContainer");
    contentTable.getCellFormatter().setWidth(0, 0, "100%");
    contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);
    
    // Create the menu
    //
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setBorderWidth(0);
    hPanel.setSpacing(0);
    hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
    addButton = new Button("Add");
    hPanel.add(addButton);
    deleteButton = new Button("Delete");
    hPanel.add(deleteButton);
    contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
    contentTable.setWidget(0, 0, hPanel);
    
    // Create the customers list
    //
    customerTable = new FlexTable();
    customerTable.setCellSpacing(0);
    customerTable.setCellPadding(0);
    customerTable.setWidth("100%");
    customerTable.addStyleName("contacts-ListContents");
    customerTable.getColumnFormatter().setWidth(0, "15px");
    contentTable.setWidget(1, 0, customerTable);
    
    contentTableDecorator.add(contentTable);
  }
  
  public HasClickHandlers getAddButton() {
    return addButton;
  }
  
  public HasClickHandlers getDeleteButton() {
    return deleteButton;
  }
  
  public HasClickHandlers getList() {
    return customerTable;
  }

  public void setData(List<String> data) {
    customerTable.removeAllRows();
    
    for (int i = 0; i < data.size(); ++i) {
      customerTable.setWidget(i, 0, new CheckBox());
      customerTable.setText(i, 1, data.get(i));
    }
  }
  
  public int getClickedRow(ClickEvent event) {
    int selectedRow = -1;
    HTMLTable.Cell cell = customerTable.getCellForEvent(event);
    
    if (cell != null) {
      // Suppress clicks if the user is actually selecting the 
      //  check box
      //
      if (cell.getCellIndex() > 0) {
        selectedRow = cell.getRowIndex();
      }
    }
    
    return selectedRow;
  }
  
  public List<Integer> getSelectedRows() {
    List<Integer> selectedRows = new ArrayList<Integer>();
    
    for (int i = 0; i < customerTable.getRowCount(); ++i) {
      CheckBox checkBox = (CheckBox) customerTable.getWidget(i, 0);
      if (checkBox.getValue()) {
        selectedRows.add(i);
      }
    }
    
    return selectedRows;
  }
  
  public Widget asWidget() {
    return this;
  }
}
