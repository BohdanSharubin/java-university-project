package org.bohdansharubin.models;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.enums.SleeveLength;

/**
 * Represents a Pants item.
 * <p>
 * A Pants is a concrete subclass of {@link Clothes} with a fixed
 * {@link ClothesType#PANTS} type. The type of Pants instance
 * cannot be changed after creation.
 * <p>
 * Additional characteristic specific to Pants is its existence of pockets.
 */
public class Pants extends Clothes {
    private boolean hasPockets;

    /**
     * Constructs Pants with specified parameters.
     *
     * @param color        the color of the pants (must not be null or blank)
     * @param europeanSize the European size (must be within valid range)
     * @param americanSize the American size (must not be null)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Pants(String color, int europeanSize, AmericanSize americanSize) {
        super(color, ClothesType.PANTS, europeanSize, americanSize);
    }

    /**
     * Constructs Pants with specified parameters including existence of pockets.
     *
     * @param color        the color of the pants (must not be null or blank)
     * @param europeanSize the European size (must be within valid range)
     * @param americanSize the American size (must not be null)
     * @param hasPockets the availability of pockets
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Pants(String color, int europeanSize, AmericanSize americanSize, boolean hasPockets) {
        super(color, ClothesType.PANTS, europeanSize, americanSize);
        this.hasPockets = hasPockets;
    }

    /**
     * Copy constructor.
     *
     * @param other the Clothes object to copy from
     */
    public Pants(Clothes other) {
        super(other);
    }

    /**
     * Default constructor.
     */
    public Pants() {
    }

    /**
     * @return if the pants has pockets
     */
    public boolean isHasPockets() {
        return hasPockets;
    }

    /**
     * Sets availability of pockets.
     *
     * @param hasPockets must be true or false
     */
    public void setHasPockets(boolean hasPockets) {
        this.hasPockets = hasPockets;
    }

    /**
     * This operation is not supported because Pants
     * always have the type {@link ClothesType#PANTS}.
     *
     * @param type ignored
     * @throws UnsupportedOperationException always thrown
     */

    @Override
    public void setType(ClothesType type) {
        throw new UnsupportedOperationException("Type of this object is always PANTS");
    }

    /**
     * Returns string representation of the Pants.
     *
     * @return formatted string with pants details
     */
    @Override
    public String toString() {
        return "Pants{" +
                "color='" + getColor() + '\'' +
                ", type='" + getType() + '\'' +
                ", europeanSize=" + getEuropeanSize() +
                ", americanSize='" + getAmericanSize() + '\'' +
                '}';
    }
}
