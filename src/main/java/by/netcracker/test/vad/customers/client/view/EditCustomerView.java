package by.netcracker.test.vad.customers.client.view;

import by.netcracker.test.vad.customers.client.presenter.EditCustomerPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.List;

public class EditCustomerView extends Composite implements EditCustomerPresenter.Display {
    private final TextBox firstName;
    private final TextBox lastName;
    private final TextBox title;
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

        // Create the contacts list
        //
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(4);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("contacts-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-contact-input");
        firstName = new TextBox();
        lastName = new TextBox();
        typeCustomer = new ListBox();
        typeCustomer.ensureDebugId("ListBox-dropBox");


        title = new TextBox();
        modifiedDate = new DateBox();
        initDetailsTable();
        contentDetailsPanel.add(detailsTable);

        HorizontalPanel menuPanel = new HorizontalPanel();
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        menuPanel.add(saveButton);
        menuPanel.add(cancelButton);
        contentDetailsPanel.add(menuPanel);
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    private void initDetailsTable() {
        detailsTable.setWidget(0, 0, new Label("Firstname"));
        detailsTable.setWidget(0, 1, firstName);
        detailsTable.setWidget(1, 0, new Label("Lastname"));
        detailsTable.setWidget(1, 1, lastName);
        detailsTable.setWidget(2, 0, new Label("Title"));
        detailsTable.setWidget(2, 1, title);
        detailsTable.setWidget(3, 0, new Label("Modified Date"));
        detailsTable.setWidget(3, 1, modifiedDate);
        detailsTable.setWidget(4, 0, new Label("Type"));
        detailsTable.setWidget(4, 1, typeCustomer);
        firstName.setFocus(true);
    }

    public HasValue<String> getFirstName() {
        return firstName;
    }

    public HasValue<String> getLastName() {
        return lastName;
    }

    public HasValue<String> getCustomerTitle() {
        return title;
    }

    @Override
    public HasValue<Date> getCustomerModifiedWhen() {
        return modifiedDate;
    }

    @Override
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
