package org.bohdansharubin.models;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.enums.SkirtLength;

/**
 * Represents a Skirt item.
 * <p>
 * A Skirt is a concrete subclass of {@link Clothes} with a fixed
 * {@link ClothesType#SKIRT} type. The type of Skirt instance
 * cannot be changed after creation.
 * <p>
 * An additional characteristic specific to Skirt is its {@link SkirtLength}.
 */
public class Skirt extends Clothes {

    private SkirtLength length;

    /**
     * Constructs a Skirt with the specified basic parameters.
     *
     * @param color        the color of the skirt; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     *
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Skirt(String color, int europeanSize, AmericanSize americanSize) {
        super(color, ClothesType.SKIRT, europeanSize, americanSize);
    }

    /**
     * Constructs a Skirt with all parameters including length.
     *
     * @param color        the color of the skirt; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     * @param length       the skirt length; must not be {@code null}
     *
     * @throws IllegalArgumentException if {@code length} is {@code null}
     */
    public Skirt(String color, int europeanSize, AmericanSize americanSize, SkirtLength length) {
        super(color, ClothesType.SKIRT, europeanSize, americanSize);
        if (length == null) {
            throw new IllegalArgumentException("length can't be null");
        }
        this.length = length;
    }

    /**
     * Copy constructor.
     * <p>
     * Creates a new Skirt instance by copying the state of the given {@link Clothes} object.
     *
     * @param other the Clothes object to copy from; must not be {@code null}
     */
    public Skirt(Clothes other) {
        super(other);
    }

    /**
     * Default constructor.
     * <p>
     * Intended for frameworks or serialization mechanisms.
     */
    public Skirt() {
    }

    /**
     * Returns the length of this Skirt.
     *
     * @return the skirt length, or {@code null} if not specified
     */
    public SkirtLength getLength() {
        return length;
    }

    /**
     * Sets the length of this Skirt.
     *
     * @param length the skirt length to set; must not be {@code null}
     * @throws IllegalArgumentException if {@code length} is {@code null}
     */
    public void setLength(SkirtLength length) {
        if (length == null) {
            throw new IllegalArgumentException("length can't be null");
        }
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a string representation of this Skirt.
     *
     * @return a formatted string containing skirt details
     */
    @Override
    public String toString() {
        return "Skirt{" +
                "uuid=" + getUuid() +
                ", color='" + getColor() + '\'' +
                ", type='" + getType() + '\'' +
                ", europeanSize=" + getEuropeanSize() +
                ", americanSize='" + getAmericanSize() + '\'' +
                ", length=" + getLength() +
                '}';
    }
}
