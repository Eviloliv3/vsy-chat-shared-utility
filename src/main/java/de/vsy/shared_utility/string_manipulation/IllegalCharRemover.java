/*
 *
 */
package de.vsy.shared_utility.string_manipulation;

/**
 * Simple tool for removing special characters from Strings.
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
     * If illegal characters are found, new String without illegal characters is returned.
     *
     * @param toValidate the to validate
     * @return the validated string
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
