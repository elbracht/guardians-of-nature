package gon.controllers.utils;

/**
 * @author Alexander Elbracht
 *
 * This class contains all methods to manipulate a string
 */
public class Text {
    public static String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }
}
