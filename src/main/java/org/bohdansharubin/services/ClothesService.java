package org.bohdansharubin.services;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Clothes;

import java.util.*;

import static org.bohdansharubin.views.View.LINE_SEPARATOR;

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

    /**
     * Returns sorted list of clothes with given comparator not modified original list
     * @return sorted list of clothes with comparator
     */
    public List<Clothes> getSortedListByComparator(Comparator<Clothes> comparator) {
        List<Clothes> copy = new ArrayList<>(clothesList);
        Collections.sort(copy, comparator);
        return copy;
    }

    /**
     * Finds clothes by given uuid
     * @return Optional of clothes with given uuid or empty optional
     */
    public Optional<Clothes> findClothesByUuid(UUID uuid) {
        for (Clothes clothes : clothesList) {
            if (clothes.getUuid().equals(uuid)) {
                return Optional.of(clothes);
            }
        }
        return Optional.empty();
    }

    /**
     * Builds a formatted string representation of the clothes collection
     * in a table-like view.
     *
     * <p>The output contains a header row and aligned columns for:
     * UUID, type, American size, European size, and color.</p>
     *
     * <p>Column widths are dynamically calculated based on the longest
     * values (e.g., enum names) to ensure proper alignment.</p>
     *
     * @return formatted string representing all clothes in the collection
     */
    public String toFormattedString() {
        final String americanSizeColumn = "american size";
        final String europeanSizeColumn = "european size";
        final String space = " ";
        final String columnDelimiter = space + "|" + space;
        StringBuilder sb = new StringBuilder();

        int typeLength = Arrays.stream(ClothesType.values())
                .map(e -> e.toString().length())
                .max(Comparator.naturalOrder())
                .orElseThrow();
        int americanLength = americanSizeColumn.length();
        int europeanLength = europeanSizeColumn.length();
        sb.append(getFormattedHeader());

        for(Clothes clothes : clothesList) {
            UUID uuid = clothes.getUuid();
            ClothesType clothesType = clothes.getType();
            AmericanSize americanSize = clothes.getAmericanSize();
            int europeanSize = clothes.getEuropeanSize();
            String color = clothes.getColor();
            sb.append(uuid)
                    .append(columnDelimiter)
                    .append(clothesType)
                    .append(space.repeat(typeLength - clothesType.toString().length()))
                    .append(columnDelimiter)
                    .append(americanSize)
                    .append(space.repeat(americanLength - americanSize.toString().length()))
                    .append(columnDelimiter)
                    .append(europeanSize)
                    .append(space.repeat(europeanLength - 2))
                    .append(columnDelimiter)
                    .append(color)
                    .append(LINE_SEPARATOR)
            ;

        }
        return sb.toString();
    }

    /**
     * Removes a clothes item from the collection by its UUID.
     *
     * <p>If the provided UUID is {@code null}, the method immediately
     * returns {@code false}.</p>
     *
     * <p>The method iterates through the internal collection and removes
     * the first item whose UUID matches the provided value.</p>
     *
     * @param uuid the unique identifier of the clothes item to remove
     * @return {@code true} if an item was found and removed,
     *         {@code false} otherwise
     */
    public boolean deleteClothesByUuid(UUID uuid) {
        if(uuid == null) {
            return false;
        }
        Iterator<Clothes> clothesIterator = clothesList.iterator();
        Clothes item;
        while(clothesIterator.hasNext()) {
            item = clothesIterator.next();
            if(item.getUuid().equals(uuid)) {
                clothesIterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a formatted header row for the clothes table.
     *
     * <p>The header includes column names: UUID, type, American size,
     * European size, and color. Column widths are aligned with the data
     * rows to maintain consistent formatting.</p>
     *
     * @return formatted header string
     */
    private String getFormattedHeader() {
        final String americanSizeColumn = "american size";
        final String europeanSizeColumn = "european size";
        final String uuidColumn = "uuid";
        final String typeColumn = "type";
        final String colorColumn = "color";
        final String space = " ";
        StringBuilder sb = new StringBuilder();

        int uuidLength = UUID.randomUUID().toString().length();
        int typeLength = Arrays.stream(ClothesType.values())
                .map(e -> e.toString().length())
                .max(Comparator.naturalOrder())
                .orElseThrow();
        sb.append(uuidColumn)
                .append(space.repeat(uuidLength - uuidColumn.length()))
                .append(" | ")
                .append(typeColumn)
                .append(space.repeat(typeLength - typeColumn.length()))
                .append(" | ")
                .append(americanSizeColumn)
                .append(" | ")
                .append(europeanSizeColumn)
                .append(" | ")
                .append(colorColumn)
                .append(LINE_SEPARATOR);
        return sb.toString();
    }
}
