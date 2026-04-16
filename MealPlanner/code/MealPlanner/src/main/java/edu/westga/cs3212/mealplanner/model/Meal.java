package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic class for Meals.
 */
public class Meal {
    private List<Ingredient> ingredients;
    private String name;
    private String description;

    /**
     * Default, non parameter constructor for a meal.
     * Assigns a placeholder name and description, and an empty ingredient list.
     */
    public Meal() {
        this.ingredients = new ArrayList<>();
        this.name = "New Meal";
        this.description = "No further description";
    }

    /**
     * Constructor for a meal.
     * @param ingredients List of all ingredients to add to the meal.
     * @param name Name of the meal.
     * @param description Additional descriptor for the meal.
     *
     * @post this.ingredients = ingredients, this.name = name, this.description = description
     * If ingredients are null, defaults to an empty ingredient list.
     * If name is not provided, defaults to "New Meal"
     * If description is not provided, defaults to "No further description".
     *
     * This does not currently account for a list of ingredients that contains null values,
     * rather than a null list in itself.
     */
    public Meal(List<Ingredient> ingredients, String name, String description) {
        this.ingredients = new ArrayList<>(ingredients);
        this.name = name;
        this.description = description;
        if (ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        if (name == null || name.isBlank()) {
            this.name = "New Meal";
        }
        if (description == null || description.isBlank()) {
            this.description = "No further description";
        }
    }

    /**
     * Retrieves the ingredients used in this meal.
     * @return List of ingredients in meal.
     */
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /**
     * Retrieves the name of this meal.
     * @return The name of the meal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description on this meal.
     * @return The description of the meal.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Adds a single ingredient to the meal.
     * @param toAdd Ingredient to be added.
     * @return True if added successfully, false if not (the ingredient is null)
     */
    public boolean addIngredient(Ingredient toAdd) {
        if (toAdd != null) {
            this.ingredients.add(toAdd);
            return true;
        }
        return false;
    }

    /**
     * Removes a single ingredient from the meal.
     * @param toRemove Ingredient to be removed.
     * @return True if removed successfully, false if not (ingredient could not be found)
     */
    public boolean removeIngredient(Ingredient toRemove) {
        return this.ingredients.remove(toRemove);
    }

    /**
     * Removes all ingredients from the meal.
     *
     * @post this.ingredients.isEmpty() == true
     */
    public void emptyIngredients() {
        this.ingredients.clear();
    }

    /**
     * Changes the meal's name to the given string.
     * If no name is given, defaults to "Meal".
     * @param newName Name to change the meal to.
     */
    public void setName(String newName) {
        this.name = newName;
        if (newName == null || newName.isBlank()) {
            this.name = "Meal";
        }
    }

    /**
     * Changes the meal's description to the given string.
     * If no description is given, defaults to "No further description".
     * @param newDesc Description to give the meal.
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
        if (newDesc == null || newDesc.isBlank()) {
            this.description = this.name = "No further description";
        }
    }

    /**
     * Retrieves the total caloric value of this meal.
     * @return The sum of calories from all ingredients in this meal.
     */
    public double getTotalCalories() {
        double totalCalories = 0;
        for (Ingredient currIngredient : this.ingredients) {
            totalCalories += currIngredient.getCalories();
        }
        return totalCalories;
    }

    @Override
    public String toString() {
        StringBuilder mealString = new StringBuilder(this.getName() + ": " + this.getDescription() + "\n"
                + "Ingredients:\n");
        if (this.getIngredients().isEmpty()) {
            mealString.append("- No ingredients\n");
        } else {
            for (Ingredient currIng : this.getIngredients()) {
                mealString.append("- ").append(currIng.toString()).append("\n");
            }
        }
        return mealString.toString();
    }
}
