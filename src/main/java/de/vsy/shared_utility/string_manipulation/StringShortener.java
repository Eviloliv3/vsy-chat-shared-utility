package de.vsy.shared_utility.string_manipulation;

public class StringShortener {

    private StringShortener() {
    }

    /**
     * Removes specified amount of characters/spaces from back of passed String argument. If
     * cuttingCount is longer than the String, the String will be returned unchanged.
     *
     * @param toCut        the String to shorten
     * @param cuttingCount the amount of trailing characters to remove
     * @return the shortened String
     */
    public static String cutTrailingChars(String toCut, int cuttingCount) {
        String result;
        if (toCut.length() >= cuttingCount) {
            result = toCut.substring(0, toCut.length() - cuttingCount);
        } else {
            result = toCut;
        }
        return result;
    }

    /**
     * Removes specified amount of characters/spaces from back of passed StringBuilder argument. If
     * the cuttingCount is longer than the StringBuilder's length the StringBuilder will be returned
     * unchanged.
     *
     * @param toCut        the String to shorten
     * @param cuttingCount the amount of trailing characters to remove
     * @return the shortened String
     */
    public static void cutTrailingChars(StringBuilder toCut, int cuttingCount) {
        if (toCut.length() >= cuttingCount) {
            toCut.delete(toCut.length() - cuttingCount, toCut.length() - 1);
        }
    }
}
