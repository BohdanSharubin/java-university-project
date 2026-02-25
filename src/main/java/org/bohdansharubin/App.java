package org.bohdansharubin;
import org.bohdansharubin.models.Clothes;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

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

        Clothes[] clothesArray = new Clothes[number];

        for (int i = 0; i < number; i++) {
            clothesArray[i] = ClothesFactory.createClothes(input);
            System.out.println(clothesArray[i]);
        }
        boolean isWorking = true;
        while(isWorking){

            try {
                printMenu();
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        Clothes clothes = ClothesFactory.createClothes(input);
                        Clothes[] newArray = new Clothes[clothesArray.length + 1];
                        System.arraycopy(clothesArray, 0, newArray, 0, clothesArray.length);
                        clothesArray = newArray;
                        clothesArray[clothesArray.length - 1] = clothes;
                        System.out.println("Clothes created");
                        break;
                    case 2:
                        System.out.println(Arrays.toString(clothesArray));
                        break;
                    case 3:
                        System.out.println("Was created " + Clothes.getCounter() + " clothes.");
                        break;
                    case 4:
                        isWorking = false;
                        break;
                    default:
                        System.out.println("Wrong choice");
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
        System.out.println("3. Count clothes");
        System.out.println("4. Exit");
    }
}
