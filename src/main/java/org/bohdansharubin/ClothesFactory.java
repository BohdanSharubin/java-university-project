package org.bohdansharubin;
import org.bohdansharubin.models.AmericanSize;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.models.ClothesType;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Factory class for creating {@link Clothes} objects via console input.
 * <p>
 * This class handles user interaction, input validation, and ensures that
 * a valid {@link Clothes} object is created before returning it.
 * </p>
 */
public class ClothesFactory {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ClothesFactory() {}

    /**
     * Prompts the user to enter clothing attributes and creates a valid
     * {@link Clothes} object.
     * <p>
     * The method keeps asking for input until all values are valid.
     * It handles:
     * <ul>
     *     <li>{@link InputMismatchException} – when numeric input is invalid</li>
     *     <li>{@link IllegalArgumentException} – when validation fails in {@link Clothes}</li>
     * </ul>
     *
     * @return a valid {@link Clothes} object based on user input
     */
    public static Clothes createClothes(Scanner input) {
        Clothes clothes = null;
        boolean isCreated = false;

        while (!isCreated) {
            try {
                String color;
                String type;
                int europeanSize;

                System.out.println("Please enter the color of the clothes: ");
                color = input.nextLine();

                System.out.println("Please enter the type of the clothes: ");
                type = input.nextLine();
                ClothesType clothesType = ClothesType.valueOf(type.toUpperCase());

                System.out.println("Please enter american size of the clothes: ");
                AmericanSize americanSize = AmericanSize.valueOf(input.nextLine().toUpperCase());

                System.out.println("Please enter european size of the clothes: ");
                europeanSize = input.nextInt();
                input.nextLine();

                clothes = new Clothes(color, clothesType, europeanSize, americanSize);
                isCreated = true;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong input");
                System.out.println("Try again");
                input.nextLine();
            }
        }

        return clothes;
    }
}