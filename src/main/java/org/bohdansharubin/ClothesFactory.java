package org.bohdansharubin;
import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.*;

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
     * @param clothesType is which type of object method needs to be created
     * @return a valid {@link Clothes} object based on user input
     */
    public static Clothes createClothes(Scanner input, ClothesType clothesType) {
        Clothes clothes = null;
        boolean isCreated = false;

        while (!isCreated) {
            try {
                String color;
                int europeanSize;

                System.out.println("Please enter the color of the clothes: ");
                color = input.nextLine();

                System.out.println("Please enter american size of the clothes: ");
                AmericanSize americanSize = AmericanSize.valueOf(input.nextLine().toUpperCase());

                System.out.println("Please enter european size of the clothes: ");
                europeanSize = input.nextInt();
                input.nextLine();
                clothes =  switch (clothesType) {
                    case PANTS -> new Pants(color, europeanSize, americanSize);
                    case SHIRT -> new Shirt(color, europeanSize, americanSize);
                    default -> {
                        System.out.println("Please enter the type of the clothes: ");
                        String type = input.nextLine();
                        clothesType = ClothesType.valueOf(type.toUpperCase());
                        yield new Clothes(color, clothesType, europeanSize, americanSize);
                    }
                };
                isCreated = true;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong input");
                System.out.println("Try again");
                input.nextLine();
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        return clothes;
    }
}