package org.bohdansharubin.controllers;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.exceptions.ClothesNotFoundException;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.View;

import java.util.*;

/**
 * Controller responsible for updating {@link Clothes}.
 * <p>
 * Provides method for updating clothes based on user input.
 */
public class UpdateController {

    private final View view;
    private final ClothesService clothesService;
    private final Scanner scanner;

    /**
     * Constructs UpdateController with dependencies.
     *
     * @param view            view for displaying menu
     * @param clothesService  service for clothes operations
     * @param scanner         scanner for user input
     */
    public UpdateController(View view, ClothesService clothesService, Scanner scanner) {
        this.view = view;
        this.clothesService = clothesService;
        this.scanner = scanner;
    }

    /**
     * Update clothes based on selected uuid and given user data.
     * <p>
     * Available update options:
     * <ul>
     *     <li>Update Color</li>
     *     <li>Update American size</li>
     *     <li>Update European size</li>
     * </ul>
     *
     * @return updated clothes {@link Clothes} or null if user cancels (choice 99)
     */
    public boolean update() {
        System.out.println("\nEnter uuid of clothes which you want to update:");
        Optional<Clothes> clothesForUpdate;
        try {
            UUID uuid = UUID.fromString(scanner.nextLine());
            clothesForUpdate = clothesService.findClothesByUuid(uuid);
            if(clothesForUpdate.isEmpty()) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID");
            return false;
        } catch (ClothesNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        Clothes clothes = clothesForUpdate.get();
        while (true) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                return switch (choice) {
                    case 1 -> {
                        System.out.println("Clothes color is: " + clothes.getColor());
                        System.out.println("Enter new clothes color:");
                        String color = scanner.nextLine();
                        clothes.setColor(color);
                        yield true;
                    }
                    case 2 -> {
                        System.out.println("Clothes european size is: " + clothes.getEuropeanSize());
                        System.out.println("Enter new clothes european size:");
                        String sizeString = scanner.nextLine();
                        clothes.setEuropeanSize(Integer.parseInt(sizeString));
                        yield true;
                    }
                    case 3 -> {
                        System.out.println("Clothes american size is" + clothes.getAmericanSize().toString());
                        System.out.println("Enter new clothes american size " + Arrays.toString(AmericanSize.values()));
                        AmericanSize size = AmericanSize.valueOf(scanner.nextLine().toUpperCase());
                        clothes.setAmericanSize(size);
                        yield true;
                    }
                    case 99 -> false;
                    default -> throw new InputMismatchException("Invalid choice");
                };

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong uuid. Try again");
            }
        }
    }

}
