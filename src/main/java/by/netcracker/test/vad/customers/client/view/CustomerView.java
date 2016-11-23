package by.netcracker.test.vad.customers.client.view;

import by.netcracker.test.vad.customers.client.presenter.CustomerPresenter;
import by.netcracker.test.vad.customers.shared.Customer;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.Date;
import java.util.List;

public class CustomerView extends Composite implements CustomerPresenter.Display {

    private final FlexTable contentTable;
    private final Button addButton;
    private final Button editButton;
    private final Button deleteButton;
    private final Button findButton;
    private final TextBox findText;
    private final CellTable<Customer> table;

    public void InitializeTable() {
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        // Add a selection model to handle user selection.
        final SingleSelectionModel<Customer> selectionModel = new SingleSelectionModel<Customer>();
        table.setSelectionModel(selectionModel);

        // Add a text column to show the Title.
        TextColumn<Customer> titleColumn = new TextColumn<Customer>() {
            @Override
            public String getValue(Customer object) {
                return object.getTitle();
            }
        };
        table.addColumn(titleColumn, "Title");

        // Add a text column to show the First Name.
        TextColumn<Customer> firstNameColumn = new TextColumn<Customer>() {
            @Override
            public String getValue(Customer object) {
                return object.getFirstName();
            }
        };
        table.addColumn(firstNameColumn, "First Name");

        // Add a text column to show the Last Name.
        TextColumn<Customer> lastNameColumn = new TextColumn<Customer>() {
            @Override
            public String getValue(Customer object) {
                return object.getLastName();
            }
        };
        table.addColumn(lastNameColumn, "Last Name");

        // Add a text column to show the Type.
        TextColumn<Customer> typeColumn = new TextColumn<Customer>() {
            @Override
            public String getValue(Customer object) {
                return object.getType().getCustomerTypeCaption();
            }
        };
        table.addColumn(typeColumn, "Type");

        // Add a date column to show the Modified When.
        DateTimeFormat format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT);
        DateCell dateCell = new DateCell(format);
        Column<Customer, Date> dateColumn = new Column<Customer, Date>(dateCell) {
            @Override
            public Date getValue(Customer object) {
                return object.getModifiedWhen();
            }
        };
        table.addColumn(dateColumn, "Modified When");
    }

    public CustomerView() {

        addButton = new Button("Add");
        addButton.setStyleName("btn btn-default");

        editButton = new Button("Edit");
        editButton.setStyleName("btn btn-default");

        deleteButton = new Button("Delete");
        deleteButton.setStyleName("btn btn-default");

        findText = new TextBox();
        findText.setMaxLength(50);
        findText.setStyleName("form-control");

        findButton = new Button("Find");
        findButton.setStyleName("btn btn-default");

        // Create the menu
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setBorderWidth(0);
        hPanel.setSpacing(8);
        hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

        hPanel.add(addButton);
        hPanel.add(editButton);
        hPanel.add(deleteButton);
        hPanel.add(findText);
        hPanel.add(findButton);

        // Create the customers list
        table = new CellTable<>();
        InitializeTable();

        contentTable = new FlexTable();
        contentTable.setWidth("100%");
//        contentTable.getCellFormatter().addStyleName(0, 0, "customers-ListContainer");
        contentTable.getCellFormatter().addStyleName(0, 0, "customers-ListMenu");
        contentTable.getCellFormatter().setWidth(0, 0, "100%");
        contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);
        contentTable.getCellFormatter().addStyleName(1, 0, "customers-ListMenu");
        contentTable.getCellFormatter().setWidth(1, 0, "100%");
        contentTable.getFlexCellFormatter().setVerticalAlignment(1, 0, DockPanel.ALIGN_TOP);
        contentTable.setWidget(0, 0, new HTML("<h2>Customers</h2>"));
        contentTable.setWidget(1, 0, hPanel);
        contentTable.setWidget(2, 0, table);

        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.add(contentTable);
    }

    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public HasClickHandlers getEditButton() {
        return editButton;
    }

    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    public HasClickHandlers getFindButton() {
        return findButton;
    }

    public void setData(List<Customer> data) {
        table.setRowCount(data.size(), true);
        // Push the data into the widget.
        table.setRowData(0, data);
    }

    public Customer getSelectedRow() {
        return ((SingleSelectionModel<Customer>) table.getSelectionModel()).getSelectedObject();
    }

    public HasValue<String> getFindText() {
        return findText;
    }

    public Widget asWidget() {
        return this;
    }
}
