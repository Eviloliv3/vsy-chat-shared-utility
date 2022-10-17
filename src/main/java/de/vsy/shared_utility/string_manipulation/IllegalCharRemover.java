/*
 *
 */
package de.vsy.shared_utility.string_manipulation;

/**
 * Simple tools for removing special characters from Strings.
 */
public class IllegalCharRemover {

  /**
   * The meta chars.
   */
  private static final String[] META_CHARS = {"!", "$", "%", "/", "&", "=", "[", "]", "{", "}", "'",
      "^", "°", "#",
      "~", "+", "*", "'", "`", "|", "<", ">", "\\"};

  private IllegalCharRemover() {
  }

  /**
   * Validate string.
   *
   * @param toValidate the to validate
   * @return the string
   */
  public static String adjustString(final String toValidate) {
    var tmpString = toValidate;

    for (var i = (META_CHARS.length - 1); i >= 0; i--) {

      if (toValidate.contains(META_CHARS[i])) {
        tmpString = tmpString.replace(META_CHARS[i], "");
      }
    }
    return tmpString;
  }
}
