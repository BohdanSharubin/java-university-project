package org.bohdansharubin.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Utility class for deserializing objects from files.
 * <p>
 * Provides methods to read serialized objects from a file using
 * {@link ObjectInputStream}.
 */
public class Deserializer {

    /**
     * Private constructor to prevent instantiation.
     */
    private Deserializer() {
    }

    /**
     * Reads and deserializes an object from the specified file.
     *
     * @param filePath the path to the file containing the serialized object
     * @return the deserialized object
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class of the serialized object
     *         cannot be found
     */
    public static Object loadObject(String filePath)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return in.readObject();
        }
    }
}
