package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Messenger;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Meal adder view model.
 */
public class AddMealViewModel {
    private LocalDate currDate;
    private ObservableList<Ingredient> plannedIngredients = FXCollections.observableArrayList(new ArrayList<>());

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
     * Adds an ingredient to be added to the next meal.
     *
     * @param toAdd Ingredient to be stored
     * @post this.plannedIngredients contains toAdd if toAdd is a valid ingredient.
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
     * Removes an ingredient from the current pending meal ingredients.
     *
     * @param toRemove Ingredient to be removed
     * @post this.planned ingredients will not contain toRemove.
     * @return true if ingredient was successfully removed, false if not (if ingredient was null, or not found)
     */
    public boolean removeIngredient(Ingredient toRemove) {
        if (toRemove != null && this.plannedIngredients.contains(toRemove)) {
            this.plannedIngredients.remove(toRemove);
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
            HashMap<String, Object> request = new HashMap<>();
            request.put("id", SystemInfo.getLoggedInUserId());
            request.put("reqtype", "GET_USER");
            Map<String, Object> response = Messenger.request(request);
            SystemInfo.getLoggedInUserId().getUserPlanner().addMeal(this.currDate.atStartOfDay(), toAdd);
            this.resetIngredients();
            return toAdd;
        }
    }

    /**
     * Gets the current list of stored ingredients.
     *
     * @return List of all ingredients slated for the next meal
     */
    public ObservableList<Ingredient> getPlannedIngredients() {
        return this.plannedIngredients;
    }
}
