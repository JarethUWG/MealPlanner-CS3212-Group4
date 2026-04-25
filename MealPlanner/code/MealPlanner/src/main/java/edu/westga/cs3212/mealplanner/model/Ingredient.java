package edu.westga.cs3212.mealplanner.model;

import java.util.Map;
import java.util.Objects;

/**
 * Basic class for Ingredients.
 */
public class Ingredient {
    private String name;
    private double calories;

    /**
     * Constructor for an Ingredient.
     * @param name Name of the ingredient
     * @param calories Caloric value of the ingredient
     *
     * @post this.name = name, this.calories = calories.
     * If name is not provided, defaults to "Unnamed Ingredient".
     * If calories given are below 0, defaults to 0.
     */
    public Ingredient(String name, double calories) {
        this.name = name;
        this.calories = calories;
        if (name == null || name.isBlank()) {
            this.name = "Unnamed Ingredient";
        }
        if (calories < 0) {
            this.calories = 0;
        }
    }

    /**
     * Retrieves the ingredient name.
     * @return The name of the ingredient.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the individual ingredient calories.
     * @return Caloric value of the ingredient.
     */
    public double getCalories() {
        return this.calories;
    }

    /**
     * Returns a new Ingredient object from the given info.
     * @param ingredientInfo The serialized information to deserialize from.
     * @return The newly instantiated Ingredient
     * @throws IllegalArgumentException If the name or calories is null in ingredientInformation
     */
    public static Ingredient deserialize(Map<String, Object> ingredientInfo) {
        var rawName = ingredientInfo.get("name");
        var rawCalories = ingredientInfo.get("calories");
        var ingredientName = (rawName instanceof String) ? (String) rawName : null;
        var ingredientCalories = (rawCalories instanceof Number) ? (Number) rawCalories : null;

        if (ingredientName == null) {
            throw new IllegalArgumentException("Ingredient name must be provided as a string");
        }
        if (ingredientCalories == null) {
            throw new IllegalArgumentException("Ingredient calories must be provided as a double");
        }

        return new Ingredient(ingredientName, ingredientCalories.doubleValue());
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getCalories() + " calories";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == Ingredient.class)) {
            return false;
        }
        return (Objects.equals(this.name, ((Ingredient) obj).name)
                && this.calories == ((Ingredient) obj).calories);
    }
}