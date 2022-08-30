package de.vsy.shared_utility.string_manipulation;

public
class StringShortener {

    private
    StringShortener () {}

    public static
    String cutTrailingChars (String toCut, int cuttingCount) {
        String result;
        if (toCut.length() >= cuttingCount) {
            result = toCut.substring(0, toCut.length() - cuttingCount);
        } else {
            result = toCut;
        }
        return result;
    }

    public static
    void cutTrailingChars (StringBuilder toCut, int cuttingCount) {
        if (toCut.length() >= cuttingCount) {
            toCut.delete(toCut.length() - cuttingCount, toCut.length() - 1);
        }
    }
}
