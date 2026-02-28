package org.bohdansharubin.exceptions;

import java.util.UUID;

public class ClothesNotFoundException extends RuntimeException {
    public ClothesNotFoundException(String message) {
        super(message);
    }
    public ClothesNotFoundException(UUID uuid) {
        super("Clothes with uuid = "+ uuid + " not found");
    }
}
