package edu.westga.cs3211.mealplanner.model;

/**
 * Basic class for Ingredients.
 */
public class Ingredient {
    private String name;
    private int calories;
    private String desc;

    /**
     * Basic constructor for an Ingredient.
     * @param name Name of the ingredient
     * @param calories Caloric value of the ingredient
     * @param desc Extra descriptor for ingredient
     *
     * @post this.name = name, this.calories = calories, this.desc = desc.
     * If name is not provided, defaults to "Unnamed Ingredient".
     * If calories given are below 0, defaults to 0.
     * If description is not provided, defaults to an empty string;
     */
    public Ingredient(String name, int calories, String desc) {
        this.name = name;
        this.calories = calories;
        this.desc = desc;
        if (name == null || name.isEmpty()) {
            this.name = "Unnamed Ingredient";
        }
        if (calories < 0) {
            this.calories = 0;
        }
        if (desc == null || desc.isEmpty()) {
            this.desc = "";
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
    public int getCalories() {
        return this.calories;
    }

    /**
     * Retrieves the description of the ingredient.
     * @return Extra description of the ingredient.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Changes the name of the ingredient.
     * @param newName New name of ingredient. Defaults to "Unnamed Ingredient" if  not given.
     */
    public void setName(String newName) {
        if (newName == null || newName.isEmpty()) {
            this.name = "Unnamed Ingredient";
            return;
        }
        this.name = newName;
    }

    /**
     * Changes the calories of the ingredient.
     * @param newCalories New calories of ingredient. Defaults to 0 if negative.
     */
    public void setCalories(int newCalories) {
        if (newCalories < 0) {
            this.calories = 0;
            return;
        }
        this.calories = newCalories;
    }

    /**
     * Changes the description of the ingredient.
     * @param newDesc New descriptor of ingredient. Defaults to empty string if not given.
     */
    public void setDesc(String newDesc) {
        if (newDesc == null || newDesc.isEmpty()) {
            this.desc = "";
            return;
        }
        this.desc = newDesc;
    }

}
