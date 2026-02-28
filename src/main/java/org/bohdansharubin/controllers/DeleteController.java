package org.bohdansharubin.controllers;

import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.View;

import java.util.*;

/**
 * Controller responsible for deleting {@link Clothes}.
 * <p>
 * Provides method for deleting clothes based on user input.
 */
public class DeleteController {
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
    public DeleteController(View view, ClothesService clothesService, Scanner scanner) {
        this.view = view;
        this.clothesService = clothesService;
        this.scanner = scanner;
    }

    /**
     * Delete clothes based on selected uuid and given user answer.
     * <p>
     *
     * @return true if clothes {@link Clothes} was deleted or false elsewhere
     */
    public boolean delete() {
        System.out.println("\nEnter uuid of clothes which you want to delete:");
        Optional<Clothes> clothesForDeleting;
        try {
            UUID uuid = UUID.fromString(scanner.nextLine());
            clothesForDeleting = clothesService.findClothesByUuid(uuid);
            if(clothesForDeleting.isEmpty()) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID");
            return false;
        }
        Clothes clothes = clothesForDeleting.get();
        while (true) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                return switch (choice) {
                    case 1 -> {
                        Iterator<Clothes> clothesIterator = clothesService.getClothesList().iterator();
                        Clothes item;
                        while(clothesIterator.hasNext()) {
                            item = clothesIterator.next();
                            if(item.getUuid().equals(clothes.getUuid())) {
                                clothesIterator.remove();
                                yield true;
                            }
                        }
                        yield false;
                    }
                    case 2 -> false;
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
