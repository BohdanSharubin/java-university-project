package org.bohdansharubin;
import org.bohdansharubin.models.*;
import org.bohdansharubin.enums.ClothesType;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello in Clothes App");
        System.out.println("Init clothes");
        final String inputFileName = "input.txt";

        Scanner input = new Scanner(System.in);

        List<Clothes> clothesList = loadFromDisk(inputFileName);

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
                    case 5 -> {
                        Hat hat = (Hat) ClothesFactory.createClothes(input, ClothesType.HAT);
                        clothesList.add(hat);
                        System.out.println("Hat created");
                    }
                    case 6 -> {
                        Skirt skirt = (Skirt)  ClothesFactory.createClothes(input, ClothesType.SKIRT);
                        clothesList.add(skirt);
                        System.out.println("Skirt created");
                    }
                    case 99 -> {
                        isWorking = false;
                        Serializer.saveObject(clothesList, inputFileName);
                    }
                    default -> System.out.println("Wrong choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        input.close();
    }

    /**
     * Method prints console menu
     */
    public static void printMenu() {
        System.out.println("1. Create a clothes");
        System.out.println("2. List all clothes");
        System.out.println("3. Create pants");
        System.out.println("4. Create shirt");
        System.out.println("5. Create hat");
        System.out.println("6. Create skirt");
        System.out.println("99. Exit");
    }

    /**
     * Method load from file clothes data
     * @param filename file name via app download saved list of clothes
     * @return list of clothes from file or empty list
     */
    public static List<Clothes> loadFromDisk(String filename) {
        try {
           List<Clothes> list = (List<Clothes>) Deserializer.loadObject(filename);
            System.out.println("Loaded " + list.size() + " clothes");
           return list;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't find file " + filename);
        }
        return new ArrayList<>();
    }
}
