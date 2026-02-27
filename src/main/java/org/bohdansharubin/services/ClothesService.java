package org.bohdansharubin.services;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Clothes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class responsible for managing and searching {@link Clothes} objects.
 * <p>
 * Provides methods for adding and filtering clothes based on different criteria
 * such as type, color, and size.
 */
public class ClothesService {

    /**
     * Internal storage of clothes.
     */
    private final List<Clothes> clothesList;

    /**
     * Constructs a service with the given list of clothes.
     *
     * @param clothesList initial list of clothes (must not be null)
     * @throws IllegalArgumentException if clothesList is null
     */
    public ClothesService(List<Clothes> clothesList) {
        if (clothesList == null) {
            throw new IllegalArgumentException("clothesList must not be null");
        }
        this.clothesList = clothesList;
    }

    /**
     * Returns the list of clothes.
     *
     * @return list of clothes
     */
    public List<Clothes> getClothesList() {
        return clothesList;
    }

    /**
     * Adds a new clothes item to the list.
     *
     * @param clothes the clothes to add
     */
    public void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }

    /**
     * Finds clothes by type.
     *
     * @param clothesType the type to search for
     * @return list of clothes matching the given type
     */
    public List<Clothes> findClothesByType(ClothesType clothesType) {
        List<Clothes> filteredList = new ArrayList<>();
        for(Clothes clothes : clothesList) {
            if(clothes.getType() == clothesType) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    /**
     * Finds clothes by color.
     *
     * @param color the color to search for
     * @return list of clothes matching the given color
     */
    public List<Clothes> findClothesByColor(String color) {
        List<Clothes> filteredList = new ArrayList<>();
        for(Clothes clothes : clothesList) {
            if(clothes.getColor().equals(color)) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    /**
     * Finds clothes by American size.
     *
     * @param size the size to search for
     * @return list of clothes matching the given size
     */
    public List<Clothes> findClothesByAmericanSize(AmericanSize size) {
        List<Clothes> filteredList = new ArrayList<>();
        for(Clothes clothes : clothesList) {
            if(clothes.getAmericanSize().equals(size)) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    /**
     * Finds clothes within a European size range.
     *
     * @param min minimum size (exclusive)
     * @param max maximum size (inclusive)
     * @return list of clothes within the specified size range
     * @throws IllegalArgumentException if min is greater than or equal to max
     */
    public List<Clothes> findClothesInEuropeanSizeBetween(int min, int max) {
        if(min >= max) {
            throw new IllegalArgumentException("Min value must be greater than max value");
        }
        List<Clothes> filteredList = new ArrayList<>();
        for(Clothes clothes : clothesList) {
            if(clothes.getEuropeanSize() > min && clothes.getEuropeanSize() <= max) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    /**
     * Returns sorted list of clothes not modified original list
     * @return sorted list of clothes
     */
    public List<Clothes> getSortedList() {
        return clothesList.stream()
                .sorted()
                .toList();
    }
}
