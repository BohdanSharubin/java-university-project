package org.bohdansharubin;

import org.bohdansharubin.models.AmericanSize;
import org.bohdansharubin.models.Clothes;
import org.bohdansharubin.models.ClothesType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @DisplayName("Constructor with valid arguments")
    @Test
    void shouldCreateClothesWhenValidArguments() {
        Clothes clothes = new Clothes("Red", ClothesType.SHIRT, 40, AmericanSize.M);

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
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Clothes(null, ClothesType.SHIRT, 40, AmericanSize.M)
        );

        assertEquals("Color cannot be empty", ex.getMessage());
    }

    @DisplayName("Constructor with null type")
    @Test
    void shouldThrowExceptionWhenTypeIsNullInConstructor() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Clothes("Red", null, 40, AmericanSize.M)
        );

        assertEquals("Type cannot be null", ex.getMessage());
    }

    @DisplayName("Constructor with invalid european size")
    @Test
    void shouldThrowExceptionWhenEuropeanSizeInvalidInConstructor() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Clothes("Red", ClothesType.SHIRT, 10, AmericanSize.M)
        );

        assertEquals("European size has to be between 32 and 60", ex.getMessage());
    }

    @DisplayName("Constructor with null american size")
    @Test
    void shouldThrowExceptionWhenAmericanSizeIsNullInConstructor() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Clothes("Red", ClothesType.SHIRT, 40, null)
        );

        assertTrue(ex.getMessage().contains("American size"));
    }

    @DisplayName("Copy constructor test")
    @Test
    void shouldCopyConstructorCreateEqualObject() {
        Clothes original = new Clothes("Blue", ClothesType.PANTS, 42, AmericanSize.L);
        Clothes copy = new Clothes(original);

        assertEquals(original, copy);
    }

    @DisplayName("Setting valid color")
    @Test
    void shouldSetColorWhenValid() {
        Clothes clothes = new Clothes();

        assertDoesNotThrow(() -> clothes.setColor("Black"));
        assertEquals("Black", clothes.getColor());
    }

    @DisplayName("Setting invalid color")
    @Test
    void shouldThrowExceptionWhenColorInvalidInSetter() {
        Clothes clothes = new Clothes();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> clothes.setColor(" ")
        );

        assertEquals("Invalid color", ex.getMessage());
    }

    @DisplayName("Setting valid type")
    @Test
    void shouldSetTypeWhenValid() {
        Clothes clothes = new Clothes();

        assertDoesNotThrow(() -> clothes.setType(ClothesType.HAT));
        assertEquals(ClothesType.HAT, clothes.getType());
    }

    @DisplayName("Setting null type")
    @Test
    void shouldThrowExceptionWhenTypeNullInSetter() {
        Clothes clothes = new Clothes();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> clothes.setType(null)
        );

        assertEquals("Clothes type can't be null", ex.getMessage());
    }

    @DisplayName("Setting valid european size")
    @Test
    void shouldSetEuropeanSizeWhenValid() {
        Clothes clothes = new Clothes();

        assertDoesNotThrow(() -> clothes.setEuropeanSize(40));
        assertEquals(40, clothes.getEuropeanSize());
    }

    @DisplayName("Setting invalid european size")
    @Test
    void shouldThrowExceptionWhenEuropeanSizeInvalidInSetter() {
        Clothes clothes = new Clothes();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> clothes.setEuropeanSize(100)
        );

        assertEquals("Invalid size", ex.getMessage());
    }

    @DisplayName("Setting valid american size")
    @Test
    void shouldSetAmericanSizeWhenValid() {
        Clothes clothes = new Clothes();

        assertDoesNotThrow(() -> clothes.setAmericanSize(AmericanSize.XL));
        assertEquals(AmericanSize.XL, clothes.getAmericanSize());
    }

    @DisplayName("Setting null american size")
    @Test
    void shouldThrowExceptionWhenAmericanSizeNullInSetter() {
        Clothes clothes = new Clothes();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> clothes.setAmericanSize(null)
        );

        assertEquals("American size can't be null", ex.getMessage());
    }
}
