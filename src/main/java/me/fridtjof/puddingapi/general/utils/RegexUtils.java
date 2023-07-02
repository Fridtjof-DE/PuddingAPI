package me.fridtjof.puddingapi.general.utils;

import java.util.regex.Pattern;

public class RegexUtils {
    private static Pattern isNumericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String numericString) {
        if (numericString == null) {
            return false;
        }
        return isNumericPattern.matcher(numericString).matches();
    }
}
