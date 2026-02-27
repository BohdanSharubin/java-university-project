package org.bohdansharubin.controllers;

import org.bohdansharubin.ClothesFactory;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.*;
import org.bohdansharubin.views.View;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Controller responsible for creating {@link Clothes} objects
 * based on user input.
 * <p>
 * Uses {@link View} to display menu and {@link Scanner} to read input.
 */
public class CreateController {

    private final View view;
    private final Scanner scanner;

    /**
     * Constructs CreateController with given view and scanner.
     *
     * @param view    view for displaying menu
     * @param scanner scanner for user input
     */
    public CreateController(View view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
    }

    /**
     * Creates a {@link Clothes} instance based on user choice.
     * <p>
     * Displays menu and processes input in a loop until valid data is provided.
     *
     * @return created {@link Clothes} object or null if user cancels (choice 99)
     */
    public Clothes createClothes() {
        while (true) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                return switch (choice) {
                    case 1 -> ClothesFactory.createClothes(scanner, ClothesType.PANTS);
                    case 2 -> ClothesFactory.createClothes(scanner, ClothesType.SHIRT);
                    case 3 -> ClothesFactory.createClothes(scanner, ClothesType.HAT);
                    case 4 -> ClothesFactory.createClothes(scanner, ClothesType.SKIRT);
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
