package org.bohdansharubin.models;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.enums.SleeveLength;

import java.util.Objects;

/**
 * Represents a Shirt item.
 * <p>
 * A Shirt is a concrete subclass of {@link Clothes} with a fixed
 * {@link ClothesType#SHIRT} type. The type of Shirt instance
 * cannot be changed after creation.
 * <p>
 * Additional characteristic specific to Shirt is its {@link SleeveLength}.
 */
public class Shirt extends Clothes {

    private SleeveLength sleeveLength;

    /**
     * Constructs a Shirt with the specified basic parameters.
     *
     * @param color        the color of the shirt; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     *
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Shirt(String color, int europeanSize, AmericanSize americanSize) {
        super(color, ClothesType.SHIRT, europeanSize, americanSize);
    }

    /**
     * Constructs a Shirt with the specified parameters including sleeve length.
     *
     * @param color        the color of the shirt; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     * @param sleeveLength the sleeve length; may be {@code null} if not specified
     *
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Shirt(String color, int europeanSize, AmericanSize americanSize, SleeveLength sleeveLength) {
        super(color, ClothesType.SHIRT, europeanSize, americanSize);
        if(sleeveLength == null) {
            throw new  IllegalArgumentException("SleeveLength can't be null");
        }
        this.sleeveLength = sleeveLength;
    }

    /**
     * Copy constructor.
     * <p>
     * Creates a new Shirt instance by copying the state of the given {@link Clothes} object.
     *
     * @param other the Clothes object to copy from; must not be {@code null}
     */
    public Shirt(Clothes other) {
        super(other);
    }

    /**
     * Default constructor.
     * <p>
     * Intended for frameworks or serialization mechanisms.
     */
    public Shirt() {
    }

    /**
     * Returns the sleeve length of this Shirt.
     *
     * @return the sleeve length, or {@code null} if not specified
     */
    public SleeveLength getSleeveLength() {
        return sleeveLength;
    }

    /**
     * Sets the sleeve length of this Shirt.
     *
     * @param sleeveLength the sleeve length to set
     * @throws IllegalArgumentException if sleeveLength is null
     */
    public void setSleeveLength(SleeveLength sleeveLength) {
        if(sleeveLength == null) {
            throw new  IllegalArgumentException("SleeveLength can't be null");
        }
        this.sleeveLength = sleeveLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shirt shirt = (Shirt) o;
        return sleeveLength == shirt.sleeveLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sleeveLength);
    }

    /**
     * Returns a string representation of this Shirt.
     *
     * @return a formatted string containing shirt details
     */
    @Override
    public String toString() {
        return "Shirt{" +
                "uuid=" + getUuid() +
                ", color='" + getColor() + '\'' +
                ", type='" + getType() + '\'' +
                ", europeanSize=" + getEuropeanSize() +
                ", americanSize='" + getAmericanSize() + '\'' +
                ", sleeveLength=" + getSleeveLength() +
                '}';
    }
}