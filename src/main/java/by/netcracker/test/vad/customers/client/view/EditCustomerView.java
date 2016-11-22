package by.netcracker.test.vad.customers.client.view;

import by.netcracker.test.vad.customers.client.presenter.EditCustomerPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.List;

public class EditCustomerView extends Composite implements EditCustomerPresenter.Display {
    private final TextBox title;
    private final Label titleHelp;

    private final TextBox firstName;
    private final Label firstNameHelp;

    private final TextBox lastName;
    private final Label lastNameHelp;

    private final DateBox modifiedDate;
    private final ListBox typeCustomer;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    public EditCustomerView() {
        DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("18em");
        initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");

        // Create the customers list
        //
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(4);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("customers-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-customer-input");

        title = new TextBox();
        title.setMaxLength(3);
        title.setStyleName("form-control");
        titleHelp = new Label();
        titleHelp.addStyleName("alert-warning");

        firstName = new TextBox();
        firstName.setMaxLength(50);
        firstName.setStyleName("form-control");
        firstNameHelp = new Label();
        firstNameHelp.addStyleName("alert-warning");

        lastName = new TextBox();
        lastName.setMaxLength(50);
        lastName.setStyleName("form-control");
        lastNameHelp = new Label();
        lastNameHelp.addStyleName("alert-warning");

        typeCustomer = new ListBox();
        typeCustomer.ensureDebugId("ListBox-dropBox");
        typeCustomer.setStyleName("form-control");
        modifiedDate = new DateBox();
        modifiedDate.setEnabled(false);
        modifiedDate.setStyleName("form-control");
        DateTimeFormat format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT);
        modifiedDate.setFormat(new DateBox.DefaultFormat(format));

        initDetailsTable();
        contentDetailsPanel.add(detailsTable);

        HorizontalPanel menuPanel = new HorizontalPanel();
        saveButton = new Button("Save");
        saveButton.setStyleName("btn btn-primary");
        cancelButton = new Button("Cancel");
        cancelButton.setStyleName("btn btn-default");
        menuPanel.add(saveButton);
        menuPanel.add(cancelButton);
        contentDetailsPanel.add(menuPanel);
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    private void initDetailsTable() {
        int row = 0;
        detailsTable.setWidget(row, 0, new Label("Title"));
        detailsTable.setWidget(row++, 1, title);
        detailsTable.setWidget(row++, 1, titleHelp);

        detailsTable.setWidget(row, 0, new Label("First Name"));
        detailsTable.setWidget(row++, 1, firstName);
        detailsTable.setWidget(row++, 1, firstNameHelp);

        detailsTable.setWidget(row, 0, new Label("Last Name"));
        detailsTable.setWidget(row++, 1, lastName);
        detailsTable.setWidget(row++, 1, lastNameHelp);

        detailsTable.setWidget(row, 0, new Label("Type"));
        detailsTable.setWidget(row++, 1, typeCustomer);

        detailsTable.setWidget(row, 0, new Label("Modified Date"));
        detailsTable.setWidget(row++, 1, modifiedDate);

        title.setFocus(true);
    }

    public HasValue<String> getFirstName() {
        return firstName;
    }

    public void setFirstNameHelp(String help) {
        firstNameHelp.setText(help);
    }

    public HasValue<String> getLastName() {
        return lastName;
    }

    public void setLastNameHelp(String help) {
        lastNameHelp.setText(help);
    }

    public HasValue<String> getCustomerTitle() {
        return title;
    }

    public void setTitleHelp(String help) {
        titleHelp.setText(help);
    }

    public HasValue<Date> getCustomerModifiedWhen() {
        return modifiedDate;
    }

    public void setTypes(List<String> data) {
        typeCustomer.clear();
        data.forEach(item -> typeCustomer.addItem(item));
    }

    @Override
    public void setSelectedType(String type) {
        for (int i = 0; i < typeCustomer.getItemCount(); i++) {
            if (typeCustomer.getItemText(i).equals(type))
                typeCustomer.setItemSelected(i, true);
            else
                typeCustomer.setItemSelected(i, false);
        }
    }

    @Override
    public String getSelectedType() {
        return typeCustomer.getSelectedItemText();
    }

    public HasClickHandlers getSaveButton() {
        return saveButton;
    }

    public HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    public Widget asWidget() {
        return this;
    }
}
