package by.netcracker.test.vad.customers.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {

  @DefaultMessage("Add")
  String addButton();

  @DefaultMessage("Edit")
  String editButton();

  @DefaultMessage("Delete")
  String deleteButton();

  @DefaultMessage("Find")
  String findButton();

  @DefaultMessage("Save")
  String saveButton();

  @DefaultMessage("Cancel")
  String cancelButton();

  @DefaultMessage("First Name")
  String firsNameField();

  @DefaultMessage("Last Name")
  String lastNameField();

  @DefaultMessage("Title")
  String titleField();

  @DefaultMessage("Modified When")
  String modifiedWhenField();

  @DefaultMessage("Type")
  String typeField();

  @DefaultMessage("Customer")
  String customerList();

  @DefaultMessage("Customer attributes")
  String customerEdit();

  @DefaultMessage("Error fetching customers")
  String fetchingError();

  @DefaultMessage("Error deleting customer")
  String deletingError();

  @DefaultMessage("Error retrieving customer")
  String retrievingError();

  @DefaultMessage("Error updating customer")
  String updatingError();

  @DefaultMessage("Value is empty!")
  String emptyValueWarning();

  @DefaultMessage("Only letters is allowed!")
  String lettersValueWarning();
}
