package org.bohdansharubin;

import org.bohdansharubin.models.Clothes;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a clothes shop that stores and manages a collection of clothes.
 */
public class ClothesShop {

    /** List of clothes available in the shop */
    private List<Clothes> clothesList;

    /**
     * Constructs an empty ClothesShop.
     */
    public ClothesShop() {
        clothesList = new ArrayList<>();
    }

    /**
     * Adds a piece of clothes to the shop.
     *
     * @param clothes the clothes item to add
     * @return true if the clothes was successfully added
     */
    public boolean addClothes(Clothes clothes) {
        return clothesList.add(clothes);
    }

    /**
     * Removes a piece of clothes from the shop.
     *
     * @param clothes the clothes item to remove
     * @return true if the clothes was found and removed
     */
    public boolean removeClothes(Clothes clothes) {
        return clothesList.remove(clothes);
    }

    /**
     * Checks if the shop contains a specific clothes item.
     *
     * @param clothes the clothes item to check
     * @return true if the clothes exists in the shop
     */
    public boolean hasClothes(Clothes clothes) {
        return clothesList.contains(clothes);
    }
}
