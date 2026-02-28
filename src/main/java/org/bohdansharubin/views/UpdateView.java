package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the updating clothes menu.
 * <p>
 * Provides navigation options for updating clothes functionality.
 */
public class UpdateView implements View {

    /**
     * Stores the formatted update menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the update menu content.
     */
    public UpdateView() {
        stringBuilder.append("1. Update color of the clothes")
                .append(LINE_SEPARATOR)
                .append("2. Update european size of the clothes")
                .append(LINE_SEPARATOR)
                .append("3. Update american size of the clothes")
                .append(LINE_SEPARATOR)
                .append("99. Exit")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the update menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}

