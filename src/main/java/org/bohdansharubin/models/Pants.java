package org.bohdansharubin.models;

/**
 * Represents a Pants item.
 * <p>
 * Pants are a specific type of {@link Clothes} whose type is always {@link ClothesType#PANTS}.
 * The type cannot be changed after creation.
 */
public class Pants extends Clothes {

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
