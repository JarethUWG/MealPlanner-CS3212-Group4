package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.enums.MealType;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Planned Date Page viewmodel.
 *
 * @author Kirya
 * @version Spring 2025
 */
public class PlannedDateViewmodel {

    private DateTimeFormatter dateTimeFormatter;
    private SimpleStringProperty dateProperty;
    private SimpleListProperty<Meal> plannedBreakfastsProperty;
    private SimpleListProperty<Meal> plannedLunchesProperty;
    private SimpleListProperty<Meal> plannedDinnersProperty;
    private Map<MealType, SimpleListProperty<Meal>> plannedMealProperties;

    /**
     * Planned breakfast meals.
     * @return Property for meals planned for breakfast
     */
    public ObservableValue<ObservableList<Meal>> PlannedBreakfastsProperty() {
        return this.plannedBreakfastsProperty;
    }

    /**
     * Planned lunch meals.
     * @return Property for meals planned for lunch
     */
    public ObservableValue<ObservableList<Meal>> PlannedLunchesProperty() {
        return this.plannedLunchesProperty;
    }

    /**
     * Planned dinner meals.
     * @return Property for meals planned for dinner
     */
    public ObservableValue<ObservableList<Meal>> PlannedDinnersProperty() {
        return this.plannedDinnersProperty;
    }

    /**
     * Date header property.
     * @return The date header property
     */
    public SimpleStringProperty DateProperty() {
        return this.dateProperty;
    }

    /**
     * Initializes a new Planned Date Viewmodel.
     */
    public PlannedDateViewmodel() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        this.dateProperty = new SimpleStringProperty();
        this.plannedBreakfastsProperty = new SimpleListProperty<Meal>();
        this.plannedLunchesProperty = new SimpleListProperty<Meal>();
        this.plannedDinnersProperty = new SimpleListProperty<Meal>();
        this.initializeHourPropertyMap();
        this.updateDisplayedDate();
        this.updateDisplayedPlannedMeals();
    }

    private void initializeHourPropertyMap() {
        this.plannedMealProperties = Map.ofEntries(
                Map.entry(MealType.BREAKFAST, this.plannedBreakfastsProperty),
                Map.entry(MealType.LUNCH, this.plannedLunchesProperty),
                Map.entry(MealType.DINNER, this.plannedDinnersProperty)
        );
    }

    private void updateDisplayedDate() {
        var currentPlanner = SystemInfo.getLoggedInUser().getUserPlanner();
        var selectedDate = currentPlanner.getSelectedDate();

        this.dateProperty.set(selectedDate.format(this.dateTimeFormatter));
    }

    private void updateDisplayedPlannedMeals() {
        this.clearPlannedMeals();
        var currentPlanner = SystemInfo.getLoggedInUser().getUserPlanner();
        var plannedDateMeals = currentPlanner.getSelectedDatePlannedMeals();

        for (var entry : plannedDateMeals.entrySet()) {
            var associatedHour = entry.getKey();
            ObservableList<Meal> newPlannedMeals = FXCollections.observableArrayList();
            newPlannedMeals.addAll((ArrayList<Meal>) entry.getValue());

            this.plannedMealProperties.get(associatedHour).set(newPlannedMeals);
        }
    }

    private void clearPlannedMeals() {
        for (var value : this.plannedMealProperties.values()) {
            value.clear();
        }
    }
}
