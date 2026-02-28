package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the deleting clothes menu.
 * <p>
 * Provides navigation options for deleting clothes functionality.
 */
public class DeleteView implements View {

    /**
     * Stores the formatted delete menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the delete menu content.
     */
    public DeleteView() {
        stringBuilder.append("Delete the clothes?")
                .append(LINE_SEPARATOR)
                .append("1. Yes.")
                .append(LINE_SEPARATOR)
                .append("2. No.")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the delete menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}

