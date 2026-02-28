package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the sort menu.
 * <p>
 * Provides options for sorting clothes by various criteria.
 */
public class SortView implements  View {
    /**
     * Stores the formatted sort menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the sort menu content.
     */
    public SortView() {
        stringBuilder.append("Sort menu:")
                .append(LINE_SEPARATOR)
                .append("1. Sort by type")
                .append(LINE_SEPARATOR)
                .append("2. Sort by color")
                .append(LINE_SEPARATOR)
                .append("3. Sort by american size")
                .append(LINE_SEPARATOR)
                .append("4. Sort by european size")
                .append(LINE_SEPARATOR)
                .append("5. Sort by default")
                .append(LINE_SEPARATOR)
                .append("99. Go back")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the sort menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}

