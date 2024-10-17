package net.dunice.advancedjavaprojectacademy.tasks.common;

import lombok.val;

public class StringUtils {

    public static String trimAndCapitalize(String input) {
        val minLength = 2;
        val formattedInput = input.trim().toLowerCase();

        if (formattedInput.length() < minLength) {
            throw new IllegalArgumentException("The length of the input must be >= " + minLength);
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static boolean isLettersOnlyString(String input) {
        val symbols = input.toCharArray();

        for (val symbol : symbols) {
            if (!Character.isLetter(symbol)) {
                return false;
            }
        }
        return true;
    }
}
