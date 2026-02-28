package org.bohdansharubin.services;

import org.bohdansharubin.enums.*;
import org.bohdansharubin.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClothesServiceTest {
    static ClothesService service;
    static List<Clothes> clothesList;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        clothesList = createClothes();
        service = new ClothesService(clothesList);
    }

    private static List<Clothes> createClothes() {
        return Arrays.asList(
                new Hat("blue", 33, AmericanSize.L, true, HatType.CAP),
                new Skirt("green", 50, AmericanSize.M, SkirtLength.MIDI),
                new Shirt("red", 51, AmericanSize.S, SleeveLength.LONG),
                new Pants("red", 33, AmericanSize.XL, true)
        );
    }

    @DisplayName("Filter list by type")
    @ParameterizedTest
    @ValueSource(strings = {"HAT", "SKIRT", "SHIRT", "PANTS"})
    void shouldReturnFilteredListByTypeWhenValidParameter(String type) {
        ClothesType clothesType = ClothesType.valueOf(type);
        List<Clothes> expected = clothesList.stream()
                .filter(clothes -> clothes.getType() == clothesType)
                .toList();
        List<Clothes> actual = service.findClothesByType(clothesType);
        assertEquals(expected, actual);
    }

    @DisplayName("Filter list by color")
    @ParameterizedTest
    @ValueSource(strings = {"red", "blue", "green"})
    void shouldReturnFilteredListByColorWhenValidParameter(String color) {
        List<Clothes> expected = clothesList.stream()
                .filter(clothes -> clothes.getColor().equals(color))
                .toList();
        List<Clothes> actual = service.findClothesByColor(color);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("Filter list by american size")
    @ParameterizedTest
    @ValueSource(strings = {"L", "M", "XL"})
    void shouldReturnFilteredListByAmericanSizeWhenValidParameter(String size) {
        AmericanSize americanSize = AmericanSize.valueOf(size);
        List<Clothes> expected = clothesList.stream()
                .filter(clothes -> clothes.getAmericanSize() == americanSize)
                .toList();
        List<Clothes> actual = service.findClothesByAmericanSize(americanSize);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("Filter by european size with invalid parameters")
    @ParameterizedTest(name = "{index}: min={0}, max={1}")
    @CsvSource({"35, 34", "41, 40"})
    void shouldThrowIllegalArgumentExceptionWhenMinGreaterThanMax(String minStr, String maxStr) {
        int min = Integer.parseInt(minStr);
        int max = Integer.parseInt(maxStr);
        assertThrows(IllegalArgumentException.class, () -> service.findClothesInEuropeanSizeBetween(min, max));
    }

    @DisplayName("Filter by european size with valid parameters")
    @ParameterizedTest(name = "{index}: min={0}, max={1}")
    @CsvSource({"30, 35", "40, 50"})
    void shouldReturnFilteredListInEuropeanSizeWhenValidParameter(String minStr, String maxStr) {
        int min = Integer.parseInt(minStr);
        int max = Integer.parseInt(maxStr);
        List<Clothes> expected = clothesList.stream()
                .filter(clothes -> clothes.getEuropeanSize() > min &&
                        clothes.getEuropeanSize() <= max)
                .toList();
        List<Clothes> actual = service.findClothesInEuropeanSizeBetween(min, max);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("Sorting empty list")
    @Test
    void shouldReturnEmptyListWhenGetSortedListWithEmptyList() {
        List<Clothes> expected = new ArrayList<>();
        ClothesService clothesService = new ClothesService(expected);
        List<Clothes> actual = clothesService.getSortedList();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("Sorting correct list")
    @Test
    void shouldReturnSortedListWhenGetSortedListWithValidList() {
        List<Clothes> beforeSorting = createClothes();
        List<Clothes> expected = beforeSorting.stream()
                .sorted(Comparator.comparingInt(Clothes::getEuropeanSize).thenComparing(Clothes::getColor))
                .toList();
        List<Clothes> actual = service.getSortedList();
        assertEquals(expected.size(), actual.size());
        assertEquals(beforeSorting.size(), actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("Find by uuid with existing uuid")
    @Test
    void shouldReturnOptionalClothesWhenFindByUuidWithValidUuid() {
        Clothes expected = service.getClothesList().get(0);
        Optional<Clothes> actual = service.findClothesByUuid(expected.getUuid());
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @DisplayName("Find by uuid with null uuid")
    @ParameterizedTest
    @NullSource
    void shouldReturnEmptyOptionalWhenFindByUuidWithInvalidUuid(UUID uuid) {
        Optional<Clothes> actual = service.findClothesByUuid(uuid);
        assertFalse(actual.isPresent());
    }

    @DisplayName("Find by uuid with random uuid")
    @Test
    void shouldReturnEmptyOptionalWhenFindByUuidWithInvalidUuid() {
        UUID uuid = UUID.randomUUID();
        Optional<Clothes> actual = service.findClothesByUuid(uuid);
        assertFalse(actual.isPresent());
    }

    @DisplayName("Delete by uuid with valid uuid")
    @Test
    void shouldReturnTrueWhenDeleteByUuidWithValidUuid() {
        ClothesService serviceForDeleting = new ClothesService(new ArrayList<>(createClothes()));
        Clothes expectedToBeDeleted = serviceForDeleting.getClothesList().get(0);
        UUID uuid = expectedToBeDeleted.getUuid();

        assertTrue(serviceForDeleting.findClothesByUuid(uuid).isPresent());

        boolean result = serviceForDeleting.deleteClothesByUuid(uuid);

        assertTrue(result);
        assertTrue(serviceForDeleting.findClothesByUuid(uuid).isEmpty());
    }

    @DisplayName("Delete by uuid with uuid that not in service")
    @Test
    void shouldReturnFalseWhenDeleteByUuidWithNotExistingUuid() {
        UUID uuid = UUID.randomUUID();
        assertFalse(service.findClothesByUuid(uuid).isPresent());

        boolean result = service.deleteClothesByUuid(uuid);

        assertFalse(result);
    }

    @DisplayName("Delete by nullable uuid")
    @ParameterizedTest
    @NullSource
    void shouldReturnFalseWhenDeleteByUuidWithInvalidUuid(UUID uuid) {
        assertFalse(service.findClothesByUuid(uuid).isPresent());

        boolean result = service.deleteClothesByUuid(uuid);

        assertFalse(result);
    }

}
