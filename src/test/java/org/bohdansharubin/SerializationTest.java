package org.bohdansharubin;

import org.bohdansharubin.enums.*;
import org.bohdansharubin.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationTest {

    @Test
    @DisplayName("Clothes serialize and deserialize test")
    void shouldSerializeAndDeserializeClothes() throws Exception {
        String color = "red";
        int europeanSize = 33;
        AmericanSize americanSize = AmericanSize.L;
        ClothesType type = ClothesType.CLOTHES;
        Clothes original = new Shirt(color, europeanSize, americanSize);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(original);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Clothes deserialized = (Clothes) in.readObject();
        in.close();


        assertAll(
                () -> assertNotNull(deserialized),
                () -> assertEquals(original, deserialized),
                () -> assertEquals(original.getColor(), deserialized.getColor()),
                () -> assertEquals(original.getType(), deserialized.getType()),
                () -> assertEquals(original.getEuropeanSize(), deserialized.getEuropeanSize()),
                () -> assertEquals(original.getAmericanSize(), deserialized.getAmericanSize())
        );
    }

    @Test
    @DisplayName("Shirt serialize and deserialize test")
    void shouldSerializeAndDeserializeShirt() throws Exception {
        String color = "red";
        int europeanSize = 33;
        AmericanSize americanSize = AmericanSize.L;
        Shirt original = new Shirt(color, europeanSize, americanSize, SleeveLength.LONG);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(original);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Shirt deserialized = (Shirt) in.readObject();
        in.close();

        assertAll(
                () -> assertNotNull(deserialized),
                () -> assertEquals(original, deserialized),
                () -> assertEquals(original.getColor(), deserialized.getColor()),
                () -> assertEquals(original.getType(), deserialized.getType()),
                () -> assertEquals(original.getEuropeanSize(), deserialized.getEuropeanSize()),
                () -> assertEquals(original.getAmericanSize(), deserialized.getAmericanSize()),
                () -> assertEquals(original.getSleeveLength(), deserialized.getSleeveLength())
        );
    }

    @Test
    @DisplayName("Skirt serialize and deserialize test")
    void shouldSerializeAndDeserializeSkirt() throws Exception {
        String color = "red";
        int europeanSize = 33;
        AmericanSize americanSize = AmericanSize.L;
        Skirt original = new Skirt(color, europeanSize, americanSize, SkirtLength.MIDI);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(original);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Skirt deserialized = (Skirt) in.readObject();
        in.close();

        assertAll(
                () -> assertNotNull(deserialized),
                () -> assertEquals(original, deserialized),
                () -> assertEquals(original.getColor(), deserialized.getColor()),
                () -> assertEquals(original.getType(), deserialized.getType()),
                () -> assertEquals(original.getEuropeanSize(), deserialized.getEuropeanSize()),
                () -> assertEquals(original.getAmericanSize(), deserialized.getAmericanSize()),
                () -> assertEquals(original.getLength(), deserialized.getLength())
        );
    }

    @Test
    @DisplayName("Pants serialize and deserialize test")
    void shouldSerializeAndDeserializePants() throws Exception {
        String color = "red";
        int europeanSize = 33;
        AmericanSize americanSize = AmericanSize.L;
        Pants original = new Pants(color, europeanSize, americanSize, true);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(original);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Pants deserialized = (Pants) in.readObject();
        in.close();

        assertAll(
                () -> assertNotNull(deserialized),
                () -> assertEquals(original, deserialized),
                () -> assertEquals(original.getColor(), deserialized.getColor()),
                () -> assertEquals(original.getType(), deserialized.getType()),
                () -> assertEquals(original.getEuropeanSize(), deserialized.getEuropeanSize()),
                () -> assertEquals(original.getAmericanSize(), deserialized.getAmericanSize()),
                () -> assertEquals(original.isHasPockets(), deserialized.isHasPockets())
        );
    }

    @Test
    @DisplayName("Hat serialize and deserialize test")
    void shouldSerializeAndDeserializeHat() throws Exception {
        String color = "red";
        int europeanSize = 33;
        AmericanSize americanSize = AmericanSize.L;
        Hat original = new Hat(color, europeanSize, americanSize, true ,HatType.CAP);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(original);
        out.close();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Hat deserialized = (Hat) in.readObject();
        in.close();

        assertAll(
                () -> assertNotNull(deserialized),
                () -> assertEquals(original, deserialized),
                () -> assertEquals(original.getColor(), deserialized.getColor()),
                () -> assertEquals(original.getType(), deserialized.getType()),
                () -> assertEquals(original.getEuropeanSize(), deserialized.getEuropeanSize()),
                () -> assertEquals(original.getAmericanSize(), deserialized.getAmericanSize()),
                () -> assertEquals(original.isWaterProof(), deserialized.isWaterProof()),
                () -> assertEquals(original.getHatType(), deserialized.getHatType())
        );
    }

}
