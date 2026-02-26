package org.bohdansharubin.models;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a piece of clothing with basic attributes such as color, type,
 * European size, and American size.
 * <p>
 * The class provides validation for all fields to ensure only valid data is assigned.
 * Allowed types and sizes are restricted to predefined constants.
 */
public class Clothes {

    private String color;
    private int europeanSize;
    private AmericanSize americanSize;
    private ClothesType type;

    /**
     * Constructs a Clothes object with all parameters.
     *
     * @param color          the color of the clothes (must not be null or blank)
     * @param type           the type of clothes (must be one of COMMON_TYPES)
     * @param europeanSize   the European size (must be between 32 and 60)
     * @param americanSize   the American size (must be one of COMMON_AMERICAN_SIZES)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Clothes(String color, ClothesType type, int europeanSize, AmericanSize americanSize) {
        isValid(color, type, europeanSize, americanSize);
        this.color = color;
        this.type = type;
        this.europeanSize = europeanSize;
        this.americanSize = americanSize;
    }

    /**
     * Copy constructor.
     */
    public Clothes(Clothes other) {
        this.color = other.color;
        this.type = other.type;
        this.europeanSize = other.europeanSize;
        this.americanSize = other.americanSize;
    }

    /**
     * Default constructor.
     */
    public Clothes() {
    }

    /**
     * @return the color of the clothes
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the clothes.
     *
     * @param color the color to set (must not be null or blank)
     * @throws IllegalArgumentException if color is invalid
     */
    public void setColor(String color) {
        if (!isStringValid(color)) {
            throw new IllegalArgumentException("Invalid color");
        }
        this.color = color;
    }

    /**
     * @return the type of the clothes
     */
    public ClothesType getType() {
        return type;
    }

    /**
     * Sets the type of the clothes.
     *
     * @param type the type to set (must be one of COMMON_TYPES)
     * @throws IllegalArgumentException if type is invalid
     */
    public void setType(ClothesType type) {
        if (!isNotNull(type)) {
            throw new IllegalArgumentException("Clothes type can't be null");
        }
        this.type = type;
    }

    /**
     * @return the European size
     */
    public int getEuropeanSize() {
        return europeanSize;
    }

    /**
     * Sets the European size.
     *
     * @param europeanSize size between 32 and 60
     * @throws IllegalArgumentException if size is invalid
     */
    public void setEuropeanSize(int europeanSize) {
        if (!isEuropeanSizeValid(europeanSize)) {
            throw new IllegalArgumentException("Invalid size");
        }
        this.europeanSize = europeanSize;
    }

    /**
     * @return the American size
     */
    public AmericanSize getAmericanSize() {
        return americanSize;
    }

    /**
     * Sets the American size.
     *
     * @param americanSize must be one of AmericanSize enum
     * @throws IllegalArgumentException if size is invalid
     */
    public void setAmericanSize(AmericanSize americanSize) {
        if (!isNotNull(americanSize)) {
            throw new IllegalArgumentException("American size can't be null");
        }
        this.americanSize = americanSize;
    }

    /**
     * Compares this object with another for equality.
     *
     * @param o the object to compare
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return europeanSize == clothes.europeanSize &&
                Objects.equals(color, clothes.color) &&
                Objects.equals(type, clothes.type) &&
                Objects.equals(americanSize, clothes.americanSize);
    }

    /**
     * Generates hash code based on object fields.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, type, europeanSize, americanSize);
    }

    /**
     * Returns string representation of the object.
     *
     * @return formatted string with all fields
     */
    @Override
    public String toString() {
        return "Clothes{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", europeanSize=" + europeanSize +
                ", americanSize='" + americanSize + '\'' +
                '}';
    }

    /**
     * Validates all fields.
     */
    private void isValid(String color, ClothesType type, int europeanSize, AmericanSize americanSize) {
        if (!isStringValid(color)) {
            throw new IllegalArgumentException("Color cannot be empty");
        } else if (!isNotNull(type)) {
            throw new IllegalArgumentException("Type cannot be null");
        } else if (!isEuropeanSizeValid(europeanSize)) {
            throw new IllegalArgumentException("European size has to be between 32 and 60");
        } else if (!isNotNull(americanSize)) {
            throw new IllegalArgumentException("American size cannot be null ");
        }
    }

    /**
     * Checks if string is valid (not null and not blank).
     */
    private boolean isStringValid(String color) {
        return color != null && !color.isBlank();
    }

    /**
     * Checks if object is not null.
     */
    private boolean isNotNull(Object type) {
        return Objects.nonNull(type);
    }

    /**
     * Checks if European size is in valid range.
     */
    private boolean isEuropeanSizeValid(int size) {
        return size > 32 && size < 60;
    }
}