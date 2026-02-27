package org.bohdansharubin.models;

import org.bohdansharubin.enums.AmericanSize;
import org.bohdansharubin.enums.ClothesType;
import org.bohdansharubin.enums.HatType;
/**
 * Represents a Hat item.
 * <p>
 * A Hat is a concrete subclass of {@link Clothes} with a fixed
 * {@link ClothesType#HAT} type. The type of Hat instance
 * cannot be changed after creation.
 * <p>
 * Additional characteristics specific to Hat include whether it is waterproof
 * and its {@link HatType}.
 */
public class Hat extends Clothes {

    private boolean isWaterProof;
    private HatType hatType;

    /**
     * Constructs a Hat with the specified basic parameters.
     *
     * @param color        the color of the hat; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     *
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Hat(String color, int europeanSize, AmericanSize americanSize) {
        super(color, ClothesType.HAT, europeanSize, americanSize);
    }

    /**
     * Constructs a Hat with all parameters.
     *
     * @param color        the color of the hat; must not be {@code null} or blank
     * @param europeanSize the European size; must be within the valid range
     * @param americanSize the American size; must not be {@code null}
     * @param isWaterProof indicates whether the hat is waterproof
     * @param hatType      the type of the hat; must not be {@code null}
     *
     * @throws IllegalArgumentException if {@code hatType} is {@code null}
     */
    public Hat(String color, int europeanSize, AmericanSize americanSize, boolean isWaterProof, HatType hatType) {
        super(color, ClothesType.HAT, europeanSize, americanSize);
        this.isWaterProof = isWaterProof;
        if (hatType == null) {
            throw new IllegalArgumentException("hatType cannot be null");
        }
        this.hatType = hatType;
    }

    /**
     * Copy constructor.
     * <p>
     * Creates a new Hat instance by copying the state of the given {@link Clothes} object
     * and applying additional Hat-specific properties.
     *
     * @param other        the Clothes object to copy from; must not be {@code null}
     * @param isWaterProof indicates whether the hat is waterproof
     * @param hatType      the type of the hat; must not be {@code null}
     *
     * @throws IllegalArgumentException if {@code hatType} is {@code null}
     */
    public Hat(Clothes other, boolean isWaterProof, HatType hatType) {
        super(other);
        this.isWaterProof = isWaterProof;
        if (hatType == null) {
            throw new IllegalArgumentException("hatType cannot be null");
        }
        this.hatType = hatType;
    }

    /**
     * Returns whether this Hat is waterproof.
     *
     * @return {@code true} if waterproof, {@code false} otherwise
     */
    public boolean isWaterProof() {
        return isWaterProof;
    }

    /**
     * Sets whether this Hat is waterproof.
     *
     * @param waterProof {@code true} to make it waterproof, {@code false} otherwise
     */
    public void setWaterProof(boolean waterProof) {
        isWaterProof = waterProof;
    }

    /**
     * Returns the type of this Hat.
     *
     * @return the hat type; never {@code null}
     */
    public HatType getHatType() {
        return hatType;
    }

    /**
     * Sets the type of this Hat.
     *
     * @param hatType the hat type to set; must not be {@code null}
     * @throws IllegalArgumentException if {@code hatType} is {@code null}
     */
    public void setHatType(HatType hatType) {
        if (hatType == null) {
            throw new IllegalArgumentException("hatType cannot be null");
        }
        this.hatType = hatType;
    }

    /**
     * Returns a string representation of this Hat.
     *
     * @return a formatted string containing hat details
     */
    @Override
    public String toString() {
        return "Hat{" +
                "color='" + getColor() + '\'' +
                ", type='" + getType() + '\'' +
                ", europeanSize=" + getEuropeanSize() +
                ", americanSize='" + getAmericanSize() + '\'' +
                ", hatType=" + getHatType() +
                ", isWaterProof=" + isWaterProof +
                '}';
    }
}
