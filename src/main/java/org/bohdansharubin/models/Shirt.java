package org.bohdansharubin.models;

/**
 * Represents a Shirt item.
 * <p>
 * A Shirt is a specific type of {@link Clothes} whose type is always {@link ClothesType#SHIRT}.
 * The type cannot be changed after creation.
 */
public class Shirt extends Clothes {

    /**
     * Constructs a Shirt with specified parameters.
     *
     * @param color        the color of the shirt (must not be null or blank)
     * @param europeanSize the European size (must be within valid range)
     * @param americanSize the American size (must not be null)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Shirt(String color, int europeanSize, AmericanSize americanSize) {
        super(color, ClothesType.SHIRT, europeanSize, americanSize);
    }

    /**
     * Copy constructor.
     *
     * @param other the Clothes object to copy from
     */
    public Shirt(Clothes other) {
        super(other);
    }

    /**
     * Default constructor.
     */
    public Shirt() {
    }

    /**
     * This operation is not supported because a Shirt
     * always has the type {@link ClothesType#SHIRT}.
     *
     * @param type ignored
     * @throws UnsupportedOperationException always thrown
     */
    @Override
    public void setType(ClothesType type) {
        throw new UnsupportedOperationException("Type of the object is always SHIRT");
    }

    /**
     * Returns string representation of the Shirt.
     *
     * @return formatted string with shirt details
     */
    @Override
    public String toString() {
        return "Shirt{" +
                "color='" + getColor() + '\'' +
                ", type='" + getType() + '\'' +
                ", europeanSize=" + getEuropeanSize() +
                ", americanSize='" + getAmericanSize() + '\'' +
                '}';
    }
}