package org.bohdansharubin.services;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Clothes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClothesService {
    private final List<Clothes> clothesList;

    public ClothesService(List<Clothes> clothesList) {
        if (clothesList == null) {
            throw new IllegalArgumentException("clothesList must not be null");
        }
        this.clothesList = clothesList;
    }

    public List<Clothes> getClothesList() {
        return clothesList;
    }

    public void addClothes(Clothes clothes) {
        clothesList.add(clothes);
    }

    public List<Clothes> findClothesByType(ClothesType clothesType) {
        Iterator<Clothes> iterator = clothesList.iterator();
        List<Clothes> filteredList = new ArrayList<>();
        while (iterator.hasNext()) {
            Clothes clothes = iterator.next();
            if(clothes.getType() == clothesType) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    public List<Clothes> findClothesByColor(String color) {
        Iterator<Clothes> iterator = clothesList.iterator();
        List<Clothes> filteredList = new ArrayList<>();
        while (iterator.hasNext()) {
            Clothes clothes = iterator.next();
            if(clothes.getColor().equals(color)) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    public List<Clothes> findClothesByAmericanSize(AmericanSize size) {
        Iterator<Clothes> iterator = clothesList.iterator();
        List<Clothes> filteredList = new ArrayList<>();
        while (iterator.hasNext()) {
            Clothes clothes = iterator.next();
            if(clothes.getAmericanSize().equals(size)) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }

    public List<Clothes> findClothesInEuropeanSizeBetween(int min, int max) {
        if(min >= max) {
            throw new IllegalArgumentException("Min value must be greater than max value");
        }
        Iterator<Clothes> iterator = clothesList.iterator();
        List<Clothes> filteredList = new ArrayList<>();
        while (iterator.hasNext()) {
            Clothes clothes = iterator.next();
            if(clothes.getEuropeanSize() > min && clothes.getEuropeanSize() <= max) {
                filteredList.add(clothes);
            }
        }
        return filteredList;
    }
}
