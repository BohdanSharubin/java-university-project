package org.bohdansharubin.controllers;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.View;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Controller responsible for searching and filtering {@link Clothes}.
 * <p>
 * Provides multiple filtering options based on user input.
 */
public class SearchController {

    private final View view;
    private final ClothesService clothesService;
    private final Scanner scanner;

    /**
     * Constructs SearchController with dependencies.
     *
     * @param view            view for displaying menu
     * @param clothesService  service for clothes operations
     * @param scanner         scanner for user input
     */
    public SearchController(View view, ClothesService clothesService, Scanner scanner) {
        this.view = view;
        this.clothesService = clothesService;
        this.scanner = scanner;
    }

    /**
     * Filters clothes based on selected criteria.
     * <p>
     * Available filters:
     * <ul>
     *     <li>Type</li>
     *     <li>Color</li>
     *     <li>American size</li>
     *     <li>European size range</li>
     * </ul>
     *
     * @return list of filtered {@link Clothes} or null if user cancels (choice 99)
     */
    public List<Clothes> filter() {
        while (true) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                return switch (choice) {
                    case 1 -> {
                        System.out.println("Enter clothes type " + Arrays.toString(ClothesType.values()));
                        ClothesType clothesType = ClothesType.valueOf(scanner.nextLine().toUpperCase());
                        yield clothesService.findClothesByType(clothesType);
                    }
                    case 2 -> {
                        System.out.println("Enter color of clothes :");
                        yield clothesService.findClothesByColor(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.println("Enter clothes american size " + Arrays.toString(AmericanSize.values()));
                        AmericanSize size = AmericanSize.valueOf(scanner.nextLine().toUpperCase());
                        yield clothesService.findClothesByAmericanSize(size);
                    }
                    case 4 -> {
                        System.out.println("Enter min and max european size (32-60)");
                        int min = scanner.nextInt();
                        int max = scanner.nextInt();
                        yield clothesService.findClothesInEuropeanSizeBetween(min, max);
                    }
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
