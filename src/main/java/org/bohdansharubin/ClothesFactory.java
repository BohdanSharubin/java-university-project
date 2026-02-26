package org.bohdansharubin;
import org.bohdansharubin.enums.*;
import org.bohdansharubin.models.*;

import java.util.Arrays;
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

                System.out.println("Please enter american size of the clothes: " + Arrays.toString(AmericanSize.values()));
                AmericanSize americanSize = AmericanSize.valueOf(input.nextLine().toUpperCase());

                System.out.println("Please enter european size of the clothes: ");
                europeanSize = input.nextInt();
                input.nextLine();
                clothes =  switch (clothesType) {
                    case PANTS -> {
                        System.out.println("Enter is pants has pockets(true/false):");
                        boolean hasPockets = input.nextBoolean();
                        yield new Pants(color, europeanSize, americanSize, hasPockets);
                    }
                    case SHIRT -> {
                        System.out.println("Enter shirt sleeve length " + Arrays.toString(SleeveLength.values()));
                        SleeveLength sleeveLength = SleeveLength.valueOf(input.nextLine());
                        yield new Shirt(color, europeanSize, americanSize, sleeveLength);
                    }
                    case HAT -> {
                        System.out.println("Enter if hat is waterproof(true/false):");
                        boolean hasWaterproof = input.nextBoolean();
                        System.out.println("Enter hat type " +  Arrays.toString(HatType.values()));
                        HatType hatType = HatType.valueOf(input.nextLine().toUpperCase());
                        yield new Hat(color, europeanSize, americanSize, hasWaterproof, hatType);
                    }
                    case SKIRT -> {
                        System.out.println("Enter skirt length " + Arrays.toString(SkirtLength.values()));
                        SkirtLength skirtLength = SkirtLength.valueOf(input.nextLine().toUpperCase());
                        yield new Skirt(color, europeanSize, americanSize, skirtLength);
                    }
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