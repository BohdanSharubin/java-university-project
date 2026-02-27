package org.bohdansharubin.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Utility class for serializing objects to files.
 * <p>
 * Provides methods to write objects to a file using
 * {@link ObjectOutputStream}.
 */
public class Serializer {

    /**
     * Private constructor to prevent instantiation.
     */
    private Serializer() {
    }

    /**
     * Serializes and writes an object to the specified file.
     *
     * @param object   the object to serialize (must implement {@link java.io.Serializable})
     * @param filePath the path to the file where the object will be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveObject(Object object, String filePath) throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {

            out.writeObject(object);
        }
    }
}
