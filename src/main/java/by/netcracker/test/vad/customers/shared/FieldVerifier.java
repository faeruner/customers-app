package by.netcracker.test.vad.customers.shared;

public class FieldVerifier {

  public static boolean isValidName(String name) {
    return name != null && name.length() > 3;
  }

  public static String validateInput(String input) {
    String help = "";
    if (input == null || input.trim().equals("")) {
      help = "Value is empty!";
    } else if (!input.matches("^[- a-zA-Z]*$")) {
      help = "Only letters is allowed!";
    }
    return help;
  }
}
