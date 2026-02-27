package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the main application menu.
 * <p>
 * Provides navigation options for core application functionality.
 */
public class MainView implements View {

    /**
     * Stores the formatted main menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the main menu content.
     */
    public MainView() {
        stringBuilder.append("1. Create a clothes")
                .append(LINE_SEPARATOR)
                .append("2. List all clothes")
                .append(LINE_SEPARATOR)
                .append("3. Search clothes")
                .append(LINE_SEPARATOR)
                .append("99. Exit")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the main menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}
