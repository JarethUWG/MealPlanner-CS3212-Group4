package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.enums.MealType;
import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Messenger;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Meal adder view model.
 */
public class AddMealViewModel {
    private LocalDate currDate;
    private ObjectProperty<MealType> selectedMealType = new SimpleObjectProperty<>(MealType.BREAKFAST);
    private ObservableList<MealType> mealTypes = FXCollections.observableArrayList(MealType.values());
    private ObservableList<Ingredient> plannedIngredients = FXCollections.observableArrayList(new ArrayList<>());

    /**
     * The selected meal type.
     * @return The selected meal types.
     */
    public ObjectProperty<MealType> SelectedMealType() {
        return this.selectedMealType;
    }

    /**
     * The possible meal types.
     * @return The possible meal types.
     */
    public ObservableList<MealType> MealTypes() {
        return this.mealTypes;
    }

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
     * @return The meal that was created, or null if the meal was failed to be added.
     * Fails on either making a meal with no ingredients, or if an error occurs when passing to the server.
     */
    public Meal addMeal(String name, String desc) {
        if (this.plannedIngredients.isEmpty()) {
            return null;
        } else {
            var toAdd = new Meal(this.plannedIngredients, name, desc);
            var selectedMealType = this.selectedMealType.get();
            var mealHour = new ArrayList<MealType>(List.of(MealType.values())).indexOf(selectedMealType);
            var plannedTime = this.currDate.atTime(mealHour, 0);
            LocalDateTime truncatedDate = plannedTime.truncatedTo(ChronoUnit.HOURS);
            long epochHour = truncatedDate.toEpochSecond(ZoneOffset.UTC);

            String serializedMeal = toAdd.serialize();
            HashMap<String, Object> message = new HashMap<>();
            message.put("reqtype", "ADD MEAL");
            message.put("meal", serializedMeal);
            message.put("time", epochHour);
            message.put("id", SystemInfo.getId());
            Map<String, Object> response = Messenger.request(message);
            if (response.get("restype").equals("VALID")) {
                SystemInfo.getLoggedInUser().getUserPlanner().addMeal(plannedTime, toAdd);
                this.resetIngredients();
                return toAdd;
            }
            return null;
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
