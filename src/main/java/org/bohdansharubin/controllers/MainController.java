package org.bohdansharubin.controllers;

import org.bohdansharubin.models.*;
import org.bohdansharubin.services.ClothesService;
import org.bohdansharubin.views.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application controller.
 * <p>
 * Coordinates user interaction, creation of clothes,
 * displaying list, and searching functionality.
 */
public class MainController {

    private final View view;
    private final ClothesService clothesService;
    private final Scanner scanner;
    private final CreateController createController;
    private final SearchController searchController;
    private final SortController sortController;
    private final UpdateController updateController;

    /**
     * Constructs MainController with dependencies.
     *
     * @param view            main view
     * @param clothesService  service for clothes operations
     * @param scanner         scanner for user input
     */
    public MainController(View view, ClothesService clothesService, Scanner scanner) {
        this.view = view;
        this.clothesService = clothesService;
        this.scanner = scanner;
        this.createController = new CreateController(new CreateView(), scanner);
        this.searchController = new SearchController(new SearchView(), clothesService, scanner);
        this.sortController = new SortController(new SortView(), clothesService, scanner);
        this.updateController = new UpdateController(new UpdateView(), clothesService, scanner);
    }

    /**
     * Starts the application loop.
     * <p>
     * Handles user actions:
     * <ul>
     *     <li>Create clothes</li>
     *     <li>View all clothes</li>
     *     <li>Search/filter clothes</li>
     *     <li>View sorted list of clothes</li>
     *     <li>Exit application</li>
     * </ul>
     */
    public void run() {
        boolean isWorking = true;

        while (isWorking) {
            try {
                view.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        Clothes clothes = createController.createClothes();
                        if (clothes != null) {
                            clothesService.addClothes(clothes);
                            System.out.println("Clothes created");
                        }
                    }
                    case 2 -> System.out.println(clothesService.getClothesList());
                    case 3 -> {
                        List<Clothes> filteredList = searchController.filter();
                        if (filteredList != null) {
                            System.out.println("Results of your search: " + filteredList);
                        }
                    }
                    case 4 -> {
                        List<Clothes> sortedList = sortController.sort();
                        if (sortedList != null) {
                            System.out.println(sortedList);
                        }
                    }
                    case 5 -> {
                        System.out.println("All clothes:");
//                        System.out.println(clothesService.getClothesList());
                        System.out.println(clothesService.toFormattedString());
                        boolean isUpdated = updateController.update();
                        if (isUpdated) {
                            System.out.println("Clothes was updated");
                        } else {
                            System.out.println("Clothes was NOT updated");
                        }
                    }
                    case 99 -> isWorking = false;
                    default -> System.out.println("Wrong choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }
}
