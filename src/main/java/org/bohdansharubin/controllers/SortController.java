package org.bohdansharubin.controllers;

import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.View;

import java.util.*;
/**
 * Controller responsible for sorting clothes.
 * <p>
 * This class handles user input via a {@link Scanner} and delegates sorting
 * operations to the {@link ClothesService} based on the user's choice.
 * The sorting can be performed by type, color, American size, European size,
 * or using the default sorting provided by the service.
 */
public class SortController {

    /** View used to display sorting menu and prompts. */
    private final View view;

    /** Service that manages clothes and provides sorting methods. */
    private final ClothesService service;

    /** Scanner used to read user input from console. */
    private final Scanner scanner;

    /**
     * Constructs a SortController with the specified view, service, and scanner.
     *
     * @param view the view to display the sorting menu
     * @param service the service managing the clothes list
     * @param scanner the scanner for reading user input
     */
    public SortController(View view, ClothesService service, Scanner scanner) {
        this.view = view;
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Prompts the user to choose a sorting criterion and returns
     * a sorted list of {@link Clothes} according to that criterion.
     * <p>
     * The available sorting options are:
     * <ul>
     *     <li>1 - Sort by type</li>
     *     <li>2 - Sort by color</li>
     *     <li>3 - Sort by American size</li>
     *     <li>4 - Sort by European size</li>
     *     <li>5 - Default sort (as provided by service)</li>
     *     <li>99 - Exit / cancel</li>
     * </ul>
     * <p>
     * The method handles invalid input and will repeatedly prompt the user
     * until a valid choice is entered.
     *
     * @return a sorted {@link List} of {@link Clothes} based on user's choice,
     *         or {@code null} if the user chooses to exit (option 99)
     */
    public List<Clothes> sort() {
        while (true) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                return switch (choice) {
                    case 1 -> service.getSortedListByComparator(new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes o1, Clothes o2) {
                            return o1.getType().compareTo(o2.getType());
                        }
                    });
                    case 2 -> service.getSortedListByComparator(new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes o1, Clothes o2) {
                            return o1.getColor().compareTo(o2.getColor());
                        }
                    });
                    case 3 -> service.getSortedListByComparator(new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes o1, Clothes o2) {
                            return o1.getAmericanSize().compareTo(o2.getAmericanSize());
                        }
                    });
                    case 4 -> service.getSortedListByComparator(new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes o1, Clothes o2) {
                            return Integer.compare(o1.getEuropeanSize(), o2.getEuropeanSize());
                        }
                    });
                    case 5 -> service.getSortedList();
                    case 99 -> null;
                    default -> throw new InputMismatchException("Invalid choice");
                };

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }
}