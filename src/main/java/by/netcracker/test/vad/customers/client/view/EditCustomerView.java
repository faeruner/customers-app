package by.netcracker.test.vad.customers.client.view;

import by.netcracker.test.vad.customers.client.Messages;
import by.netcracker.test.vad.customers.client.presenter.EditCustomerPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.List;

public class EditCustomerView extends Composite implements EditCustomerPresenter.Display {

    private final Messages messages = (Messages) GWT.create(Messages.class);

    private final TextBox title;
    private final HorizontalPanel titlePanel;
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

        // Create fields

        title = new TextBox();
        title.setMaxLength(3);
        title.setStyleName("form-control");
        titleHelp = new Label();
        titleHelp.addStyleName("alert-warning");

        titlePanel = new HorizontalPanel();
        titlePanel.setSpacing(8);
        titlePanel.addStyleName("form-control");
        String[] titles = messages.titleArray();
        for (int i = 0; i < titles.length; i++) {
            String titleCode = titles[i];
            RadioButton radioButton = new RadioButton("title", titleCode);
            radioButton.addStyleName("customers-HeaderContainer");
            radioButton.ensureDebugId("rbtn-title-" + titleCode);
            radioButton.setValue("".equals(title.getValue()) && i == 0 || titleCode.equals(title.getValue()));
            radioButton.addClickHandler(clickEvent -> title.setValue(((RadioButton) clickEvent.getSource()).getText()));
            titlePanel.add(radioButton);
        }

        title.addValueChangeHandler(changeEvent -> titlePanel.forEach(widget -> {
            if (widget instanceof RadioButton) {
                RadioButton btn = (RadioButton) widget;
                btn.setValue(changeEvent.getValue().equals(btn.getText()));
            }
        }));

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

        // Create buttons
        saveButton = new Button(messages.saveButton());
        saveButton.setStyleName("btn btn-primary");

        cancelButton = new Button(messages.cancelButton());
        cancelButton.setStyleName("btn btn-default");

        // Create the customers list
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(4);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("customers-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-customer-input");
        initDetailsTable();

        HorizontalPanel menuPanel = new HorizontalPanel();
        menuPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        menuPanel.add(saveButton);
        menuPanel.add(cancelButton);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setBorderWidth(0);
        hPanel.setSpacing(0);
        hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
        hPanel.add(new HTML("<h3>" + messages.customerEdit() + "</h3>"));

        FlexTable contentDetailsPanel = new FlexTable();
        contentDetailsPanel.setWidth("100%");
        contentDetailsPanel.getCellFormatter().addStyleName(0, 0, "customers-ListMenu");
        contentDetailsPanel.getCellFormatter().setWidth(0, 0, "100%");
        contentDetailsPanel.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);
        contentDetailsPanel.getCellFormatter().addStyleName(2, 0, "customers-ListMenu");
        contentDetailsPanel.getCellFormatter().setWidth(2, 0, "100%");
        contentDetailsPanel.getFlexCellFormatter().setVerticalAlignment(2, 0, DockPanel.ALIGN_MIDDLE);


        contentDetailsPanel.setWidget(0, 0, hPanel);
        contentDetailsPanel.setWidget(1, 0, detailsTable);
        contentDetailsPanel.setWidget(2, 0, menuPanel);

        DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        initWidget(contentDetailsDecorator);
        contentDetailsDecorator.setWidth("24em");
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    private void initDetailsTable() {
        int row = 0;

        detailsTable.setWidget(row, 0, new Label(messages.titleField()));
        detailsTable.setWidget(row++, 1, titlePanel);
        detailsTable.setWidget(row++, 1, titleHelp);

        detailsTable.setWidget(row, 0, new Label(messages.firsNameField()));
        detailsTable.setWidget(row++, 1, firstName);
        detailsTable.setWidget(row++, 1, firstNameHelp);

        detailsTable.setWidget(row, 0, new Label(messages.lastNameField()));
        detailsTable.setWidget(row++, 1, lastName);
        detailsTable.setWidget(row++, 1, lastNameHelp);

        detailsTable.setWidget(row, 0, new Label(messages.typeField()));
        detailsTable.setWidget(row++, 1, typeCustomer);

        detailsTable.setWidget(row, 0, new Label(messages.modifiedWhenField()));
        detailsTable.setWidget(row, 1, modifiedDate);

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
        data.forEach(typeCustomer::addItem);
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
