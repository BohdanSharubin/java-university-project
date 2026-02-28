package org.bohdansharubin;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.exceptions.InvalidFieldValueException;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.models.Pants;
import org.bohdansharubin.models.Shirt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @DisplayName("Constructor with valid arguments")
    @Test
    void shouldCreateClothesWhenValidArguments() {
        Clothes clothes = new Shirt("Red", 40, AmericanSize.M);

        assertAll(
                () -> assertEquals("Red", clothes.getColor()),
                () -> assertEquals(ClothesType.SHIRT, clothes.getType()),
                () -> assertEquals(40, clothes.getEuropeanSize()),
                () -> assertEquals(AmericanSize.M, clothes.getAmericanSize())
        );
    }

    @DisplayName("Constructor with null color")
    @Test
    void shouldThrowExceptionWhenColorIsNullInConstructor() {
        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> new Shirt(null, 40, AmericanSize.M)
        );

        assertEquals("Color cannot be empty", ex.getMessage());
    }

    @DisplayName("Constructor with invalid european size")
    @Test
    void shouldThrowExceptionWhenEuropeanSizeInvalidInConstructor() {
        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> new Shirt("Red", 10, AmericanSize.M)
        );

        assertEquals("European size has to be between 32 and 60", ex.getMessage());
    }

    @DisplayName("Constructor with null american size")
    @Test
    void shouldThrowExceptionWhenAmericanSizeIsNullInConstructor() {
        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> new Shirt("Red", 40, null)
        );

        assertTrue(ex.getMessage().contains("American size"));
    }

    @DisplayName("Copy constructor test")
    @Test
    void shouldCopyConstructorCreateEqualObject() {
        Clothes original = new Pants("Blue", 42, AmericanSize.L);
        Clothes copy = new Pants(original);

        assertEquals(original, copy);
    }

    @DisplayName("Setting valid color")
    @Test
    void shouldSetColorWhenValid() {
        Clothes clothes = new Shirt();

        assertDoesNotThrow(() -> clothes.setColor("Black"));
        assertEquals("Black", clothes.getColor());
    }

    @DisplayName("Setting invalid color")
    @Test
    void shouldThrowExceptionWhenColorInvalidInSetter() {
        Clothes clothes = new Shirt();

        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> clothes.setColor(" ")
        );

        assertEquals("Invalid color", ex.getMessage());
    }

    @DisplayName("Setting valid european size")
    @Test
    void shouldSetEuropeanSizeWhenValid() {
        Clothes clothes = new Shirt();

        assertDoesNotThrow(() -> clothes.setEuropeanSize(40));
        assertEquals(40, clothes.getEuropeanSize());
    }

    @DisplayName("Setting invalid european size")
    @Test
    void shouldThrowExceptionWhenEuropeanSizeInvalidInSetter() {
        Clothes clothes = new Shirt();

        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> clothes.setEuropeanSize(100)
        );

        assertEquals("Invalid size", ex.getMessage());
    }

    @DisplayName("Setting valid american size")
    @Test
    void shouldSetAmericanSizeWhenValid() {
        Clothes clothes = new Shirt();

        assertDoesNotThrow(() -> clothes.setAmericanSize(AmericanSize.XL));
        assertEquals(AmericanSize.XL, clothes.getAmericanSize());
    }

    @DisplayName("Setting null american size")
    @Test
    void shouldThrowExceptionWhenAmericanSizeNullInSetter() {
        Clothes clothes = new Shirt();

        InvalidFieldValueException ex = assertThrows(
                InvalidFieldValueException.class,
                () -> clothes.setAmericanSize(null)
        );

        assertEquals("American size can't be null", ex.getMessage());
    }
}
