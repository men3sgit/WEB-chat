package vn.edu.nlu.fit.web.chat.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class ValidationUtil {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    private static final UrlValidator urlValidator = UrlValidator.getInstance();


    private ValidationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if the given string is a valid email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return emailValidator.isValid(email);
    }

    /**
     * Checks if the given string is not null or empty (whitespace-only).
     *
     * @param string the string to validate
     * @return true if the string is not null or empty, false otherwise
     */
    public static boolean isNotBlank(String string) {
        return !string.isBlank();
    }

    /**
     * Checks if the given string is within the specified length range (inclusive).
     *
     * @param string    the string to validate
     * @param minLength the minimum allowed length
     * @param maxLength the maximum allowed length
     * @return true if the string length is within the range, false otherwise
     */
    public static boolean hasLengthInRange(String string, int minLength, int maxLength) {
        if (string == null) {
            return false;
        }
        int length = string.length();
        return length >= minLength && length <= maxLength;
    }

    /**
     * Checks if the given string is a valid URL.
     *
     * @param url the URL to validate
     * @return true if the URL is valid, false otherwise
     */
    public static boolean isValidUrl(String url) {
        return urlValidator.isValid(url);
    }

    /**
     * Checks if the given string represents a valid integer value.
     *
     * @param value the string to validate
     * @return true if the value is a valid integer, false otherwise
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given string represents a valid decimal value.
     *
     * @param value the string to validate
     * @return true if the value is a valid decimal, false otherwise
     */
    public static boolean isDecimal(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given string matches a specific regular expression pattern.
     *
     * @param value the string to validate
     * @param regex the regular expression pattern
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean matchesPattern(String value, String regex) {
        return value != null && value.matches(regex);
    }
}
