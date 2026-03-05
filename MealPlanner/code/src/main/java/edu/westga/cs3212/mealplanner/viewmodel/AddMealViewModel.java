package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.SystemInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Meal adder view model.
 */
public class AddMealViewModel {
    private LocalDate currDate;
    private List<Ingredient> plannedIngredients = new ArrayList<>();

    /**
     * Sets the date meals are to be added to.
     *
     * @param date LocalDate of the day on the planner chosen.
     * @post this.currDate == date
     */
    public void setDate(LocalDate date) {
        this.currDate = date;
    }

    /**
     * Queues an ingredient to be added to the next meal.
     *
     * @param toAdd Ingredient to be stored
     * @post this.plannedIngredients contains toAdd
     * @return true if ingredient was added successfully, false if not (if ingredient was null)
     */
    public boolean addIngredient(Ingredient toAdd) {
        if (toAdd != null) {
            this.plannedIngredients.add(toAdd);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Empties the list of stored ingredients.
     *
     * @post this.plannedIngredients.isEmpty == true
     */
    public void resetIngredients() {
        this.plannedIngredients.clear();
    }

    /**
     * Creates a meal using the stored ingredients, and a given name/description, and adds it to the current planner.
     * @param name Name of the new meal (if given)
     * @param desc Description of the new meal (if given)
     * @return The meal that was created, or null if the meal was failed to be added (no ingredients were provided)
     */
    public Meal addMeal(String name, String desc) {
        if (this.plannedIngredients.isEmpty()) {
            return null;
        } else {
            var toAdd = new Meal(this.plannedIngredients, name, desc);
            SystemInfo.getCurrentPlanner().addMeal(this.currDate, toAdd);
            this.resetIngredients();
            return toAdd;
        }
    }

    /**
     * Gets the current date selected on the planner.
     *
     * @return LocalDate of the date selected
     */
    public LocalDate getDate() {
        return this.currDate;
    }

    /**
     * Gets the current list of stored ingredients.
     *
     * @return List of all ingredients slated for the next meal
     */
    public List<Ingredient> getPlannedIngredients() {
        return this.plannedIngredients;
    }
}
