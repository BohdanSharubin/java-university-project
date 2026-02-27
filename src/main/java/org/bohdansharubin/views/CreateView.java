package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the clothes creation menu.
 * <p>
 * Provides options for creating different types of clothes.
 */
public class CreateView implements View {

    /**
     * Stores the formatted creation menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the creation menu content.
     */
    public CreateView() {
        stringBuilder.append("1. Create pants")
                .append(LINE_SEPARATOR)
                .append("2. Create shirt")
                .append(LINE_SEPARATOR)
                .append("3. Create hat")
                .append(LINE_SEPARATOR)
                .append("4. Create skirt")
                .append(LINE_SEPARATOR)
                .append("5. Create default clothes")
                .append(LINE_SEPARATOR)
                .append("99. Go back")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the creation menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}