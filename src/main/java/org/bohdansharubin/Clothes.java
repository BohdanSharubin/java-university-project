package org.bohdansharubin;
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

    /** Common predefined clothing types */
    private static final String[] COMMON_TYPES;

    /** Common predefined American sizes */
    private static final String[] COMMON_AMERICAN_SIZES;

    private String color;
    private String type;
    private int europeanSize;
    private String americanSize;

    static {
        COMMON_TYPES = new String[]{"PANTS", "SHIRT", "SKIRT", "HAT"};
        COMMON_AMERICAN_SIZES = new String[]{"S", "M", "L", "XL", "XXL", "XXXL"};
    }

    /**
     * Constructs a Clothes object with all parameters.
     *
     * @param color          the color of the clothes (must not be null or blank)
     * @param type           the type of clothes (must be one of COMMON_TYPES)
     * @param europeanSize   the European size (must be between 32 and 60)
     * @param americanSize   the American size (must be one of COMMON_AMERICAN_SIZES)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Clothes(String color, String type, int europeanSize, String americanSize) {
        isValid(color, type, europeanSize, americanSize);
        this.color = color;
        this.type = type;
        this.europeanSize = europeanSize;
        this.americanSize = americanSize;
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
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the clothes.
     *
     * @param type the type to set (must be one of COMMON_TYPES)
     * @throws IllegalArgumentException if type is invalid
     */
    public void setType(String type) {
        if (!isTypeValid(type)) {
            throw new IllegalArgumentException("Invalid type");
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
    public String getAmericanSize() {
        return americanSize;
    }

    /**
     * Sets the American size.
     *
     * @param americanSize must be one of COMMON_AMERICAN_SIZES
     * @throws IllegalArgumentException if size is invalid
     */
    public void setAmericanSize(String americanSize) {
        if (!isAmericanSizeValid(americanSize)) {
            throw new IllegalArgumentException("Invalid american size");
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
    private void isValid(String color, String type, int europeanSize, String americanSize) {
        if (!isStringValid(color)) {
            throw new IllegalArgumentException("Color cannot be empty");
        } else if (!isTypeValid(type)) {
            throw new IllegalArgumentException("Type cannot be empty. Allowed types is " +
                    Arrays.toString(COMMON_TYPES));
        } else if (!isEuropeanSizeValid(europeanSize)) {
            throw new IllegalArgumentException("European size has to be between 32 and 60");
        } else if (!isAmericanSizeValid(americanSize)) {
            throw new IllegalArgumentException("American size has to be of " +
                    Arrays.toString(COMMON_AMERICAN_SIZES));
        }
    }

    /**
     * Checks if string is valid (not null and not blank).
     */
    private boolean isStringValid(String color) {
        return color != null && !color.isBlank();
    }

    /**
     * Checks if type is one of allowed values.
     */
    private boolean isTypeValid(String type) {
        if (isStringValid(type)) {
            for (String commonType : COMMON_TYPES) {
                if (commonType.equals(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if American size is valid.
     */
    private boolean isAmericanSizeValid(String size) {
        if (isStringValid(size)) {
            for (String commonSizes : COMMON_AMERICAN_SIZES) {
                if (commonSizes.equals(size)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if European size is in valid range.
     */
    private boolean isEuropeanSizeValid(int size) {
        return size > 32 && size < 60;
    }
}