package org.bohdansharubin.views;

/**
 * Console view responsible for displaying the search menu.
 * <p>
 * Provides options for searching clothes by various criteria.
 */
public class SearchView implements View {

    /**
     * Stores the formatted search menu content.
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs and initializes the search menu content.
     */
    public SearchView() {
        stringBuilder.append("Search menu:")
                .append(LINE_SEPARATOR)
                .append("1. Search by type")
                .append(LINE_SEPARATOR)
                .append("2. Search by color")
                .append(LINE_SEPARATOR)
                .append("3. Search by american size")
                .append(LINE_SEPARATOR)
                .append("4. Search by european size(between min and max)")
                .append(LINE_SEPARATOR)
                .append("99. Go back")
                .append(LINE_SEPARATOR);
    }

    /**
     * Prints the search menu to the console.
     */
    @Override
    public void displayMenu() {
        System.out.println(stringBuilder.toString());
    }
}