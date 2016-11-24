package by.netcracker.test.vad.customers.client;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;

@DefaultLocale("en-US")
@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends Constants {

  @DefaultStringValue("Add")
  String addButton();

  @DefaultStringValue("Edit")
  String editButton();

  @DefaultStringValue("Delete")
  String deleteButton();

  @DefaultStringValue("Find")
  String findButton();

  @DefaultStringValue("Save")
  String saveButton();

  @DefaultStringValue("Cancel")
  String cancelButton();

  @DefaultStringValue("First Name")
  String firsNameField();

  @DefaultStringValue("Last Name")
  String lastNameField();

  @DefaultStringValue("Title")
  String titleField();

  @DefaultStringValue("Modified When")
  String modifiedWhenField();

  @DefaultStringValue("Type")
  String typeField();

  @DefaultStringValue("Customers")
  String customerList();

  @DefaultStringValue("Customer attributes")
  String customerEdit();

  @DefaultStringValue("Error fetching customers")
  String fetchingError();

  @DefaultStringValue("Error deleting customer")
  String deletingError();

  @DefaultStringValue("Error retrieving customer")
  String retrievingError();

  @DefaultStringValue("Error updating customer")
  String updatingError();

  @DefaultStringValue("Value is empty!")
  String emptyValueWarning();

  @DefaultStringValue("Only letters is allowed!")
  String lettersValueWarning();

  @DefaultStringArrayValue({"Mr", "Ms", "Mrs", "Dr"})
  String[] titleArray();
}
