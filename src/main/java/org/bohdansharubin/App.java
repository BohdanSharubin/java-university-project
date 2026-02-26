package org.bohdansharubin;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Pants;
import org.bohdansharubin.models.Shirt;

import java.util.*;

/**
 * Entry point of the application.
 * <p>
 * This class is responsible for:
 * <ul>
 *     <li>Reading the number of clothes from user input</li>
 *     <li>Creating an array of {@link Clothes}</li>
 *     <li>Delegating object creation to {@link ClothesFactory}</li>
 *     <li>Displaying created objects</li>
 * </ul>
 */
public class App {

    /**
     * Main method that starts the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Hello in Clothes App");
        System.out.println("Init clothes");
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number of clothes: ");
        int number = input.nextInt();
        input.nextLine();

        List<Clothes> clothesList = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            clothesList.add(ClothesFactory.createClothes(input, ClothesType.CLOTHES));
            System.out.println(clothesList);
        }
        boolean isWorking = true;
        while(isWorking){

            try {
                printMenu();
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> {
                        Clothes clothes = ClothesFactory.createClothes(input, ClothesType.CLOTHES);
                        clothesList.add(clothes);
                        System.out.println("Clothes created");
                    }
                    case 2 -> System.out.println(clothesList);
                    case 3 -> {
                        Pants pants = (Pants) ClothesFactory.createClothes(input, ClothesType.PANTS);
                        clothesList.add(pants);
                        System.out.println("Pants created");
                    }
                    case 4 -> {
                        Shirt shirt = (Shirt) ClothesFactory.createClothes(input, ClothesType.SHIRT);
                        clothesList.add(shirt);
                        System.out.println("Shirt created");
                    }
                    case 99 -> isWorking = false;
                    default -> System.out.println("Wrong choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        input.close();
    }

    public static void printMenu() {
        System.out.println("1. Create a clothes");
        System.out.println("2. List all clothes");
        System.out.println("3. Create pants");
        System.out.println("4. Create shirt");
        System.out.println("99. Exit");
    }
}
