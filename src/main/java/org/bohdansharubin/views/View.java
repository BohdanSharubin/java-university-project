package org.bohdansharubin.views;

/**
 * Represents a generic view in the console-based application.
 * <p>
 * Implementations of this interface are responsible for displaying
 * specific menus or UI sections to the user.
 */
public interface View {

    /**
     * System-dependent line separator used for formatting console output.
     */
    String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Displays the menu or view content to the console.
     */
    void displayMenu();
}
