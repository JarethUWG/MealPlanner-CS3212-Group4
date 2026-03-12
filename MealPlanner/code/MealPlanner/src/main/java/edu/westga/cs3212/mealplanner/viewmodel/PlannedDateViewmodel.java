package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.enums.Hour;
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
    private SimpleListProperty<Meal> plannedMeals0Property;
    private SimpleListProperty<Meal> plannedMeals1Property;
    private SimpleListProperty<Meal> plannedMeals2Property;
    private SimpleListProperty<Meal> plannedMeals3Property;
    private SimpleListProperty<Meal> plannedMeals4Property;
    private SimpleListProperty<Meal> plannedMeals5Property;
    private SimpleListProperty<Meal> plannedMeals6Property;
    private SimpleListProperty<Meal> plannedMeals7Property;
    private SimpleListProperty<Meal> plannedMeals8Property;
    private SimpleListProperty<Meal> plannedMeals9Property;
    private SimpleListProperty<Meal> plannedMeals10Property;
    private SimpleListProperty<Meal> plannedMeals11Property;
    private SimpleListProperty<Meal> plannedMeals12Property;
    private SimpleListProperty<Meal> plannedMeals13Property;
    private SimpleListProperty<Meal> plannedMeals14Property;
    private SimpleListProperty<Meal> plannedMeals15Property;
    private SimpleListProperty<Meal> plannedMeals16Property;
    private SimpleListProperty<Meal> plannedMeals17Property;
    private SimpleListProperty<Meal> plannedMeals18Property;
    private SimpleListProperty<Meal> plannedMeals19Property;
    private SimpleListProperty<Meal> plannedMeals20Property;
    private SimpleListProperty<Meal> plannedMeals21Property;
    private SimpleListProperty<Meal> plannedMeals22Property;
    private SimpleListProperty<Meal> plannedMeals23Property;
    private Map<Hour, SimpleListProperty<Meal>> plannedMealProperties;

    /**
     * 12AM planned meals.
     * @return Property for meals planned at 12AM
     */
    public ObservableValue<ObservableList<Meal>> PlannedMeals0Property() {
        return this.plannedMeals0Property;
    }

    /**
     * 1AM planned meals.
     * @return Property for meals planned at 1AM
     */
    public ObservableList<Meal> PlannedMeals1Property() {
        return this.plannedMeals1Property;
    }

    /**
     * 2AM planned meals.
     * @return Property for meals planned at 2AM
     */
    public ObservableList<Meal> PlannedMeals2Property() {
        return this.plannedMeals2Property;
    }

    /**
     * 3AM planned meals.
     * @return Property for meals planned at 3AM
     */
    public ObservableList<Meal> PlannedMeals3Property() {
        return this.plannedMeals3Property;
    }

    /**
     * 4AM planned meals.
     * @return Property for meals planned at 4AM
     */
    public ObservableList<Meal> PlannedMeals4Property() {
        return this.plannedMeals4Property;
    }

    /**
     * 5AM planned meals.
     * @return Property for meals planned at 5AM
     */
    public ObservableList<Meal> PlannedMeals5Property() {
        return this.plannedMeals5Property;
    }

    /**
     * 6AM planned meals.
     * @return Property for meals planned at 6AM
     */
    public ObservableList<Meal> PlannedMeals6Property() {
        return this.plannedMeals6Property;
    }

    /**
     * 7AM planned meals.
     * @return Property for meals planned at 7AM
     */
    public ObservableList<Meal> PlannedMeals7Property() {
        return this.plannedMeals7Property;
    }

    /**
     * 8AM planned meals.
     * @return Property for meals planned at 8AM
     */
    public ObservableList<Meal> PlannedMeals8Property() {
        return this.plannedMeals8Property;
    }

    /**
     * 9AM planned meals.
     * @return Property for meals planned at 9AM
     */
    public ObservableList<Meal> PlannedMeals9Property() {
        return this.plannedMeals9Property;
    }

    /**
     * 10AM planned meals.
     * @return Property for meals planned at 10AM
     */
    public ObservableList<Meal> PlannedMeals10Property() {
        return this.plannedMeals10Property;
    }

    /**
     * 11AM planned meals.
     * @return Property for meals planned at 11AM
     */
    public ObservableList<Meal> PlannedMeals11Property() {
        return this.plannedMeals11Property;
    }

    /**
     * 12PM planned meals.
     * @return Property for meals planned at 12PM
     */
    public ObservableList<Meal> PlannedMeals12Property() {
        return this.plannedMeals12Property;
    }

    /**
     * 1PM planned meals.
     * @return Property for meals planned at 1PM
     */
    public ObservableList<Meal> PlannedMeals13Property() {
        return this.plannedMeals13Property;
    }

    /**
     * 2PM planned meals.
     * @return Property for meals planned at 2PM
     */
    public ObservableList<Meal> PlannedMeals14Property() {
        return this.plannedMeals14Property;
    }

    /**
     * 3PM planned meals.
     * @return Property for meals planned at 3PM
     */
    public ObservableList<Meal> PlannedMeals15Property() {
        return this.plannedMeals15Property;
    }

    /**
     * 4PM planned meals.
     * @return Property for meals planned at 4PM
     */
    public ObservableList<Meal> PlannedMeals16Property() {
        return this.plannedMeals16Property;
    }

    /**
     * 5PM planned meals.
     * @return Property for meals planned at 5PM
     */
    public ObservableList<Meal> PlannedMeals17Property() {
        return this.plannedMeals17Property;
    }

    /**
     * 6PM planned meals.
     * @return Property for meals planned at 6PM
     */
    public ObservableList<Meal> PlannedMeals18Property() {
        return this.plannedMeals18Property;
    }

    /**
     * 7PM planned meals.
     * @return Property for meals planned at 7PM
     */
    public ObservableList<Meal> PlannedMeals19Property() {
        return this.plannedMeals19Property;
    }

    /**
     * 8PM planned meals.
     * @return Property for meals planned at 8PM
     */
    public ObservableList<Meal> PlannedMeals20Property() {
        return this.plannedMeals20Property;
    }

    /**
     * 9PM planned meals.
     * @return Property for meals planned at 9PM
     */
    public ObservableList<Meal> PlannedMeals21Property() {
        return this.plannedMeals21Property;
    }

    /**
     * 10PM planned meals.
     * @return Property for meals planned at 10PM
     */
    public ObservableList<Meal> PlannedMeals22Property() {
        return this.plannedMeals22Property;
    }

    /**
     * 11PM planned meals.
     * @return Property for meals planned at 11PM
     */
    public ObservableList<Meal> PlannedMeals23Property() {
        return this.plannedMeals23Property;
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
        this.plannedMeals0Property = new SimpleListProperty<Meal>();
        this.plannedMeals1Property = new SimpleListProperty<Meal>();
        this.plannedMeals2Property = new SimpleListProperty<Meal>();
        this.plannedMeals3Property = new SimpleListProperty<Meal>();
        this.plannedMeals4Property = new SimpleListProperty<Meal>();
        this.plannedMeals5Property = new SimpleListProperty<Meal>();
        this.plannedMeals6Property = new SimpleListProperty<Meal>();
        this.plannedMeals7Property = new SimpleListProperty<Meal>();
        this.plannedMeals8Property = new SimpleListProperty<Meal>();
        this.plannedMeals9Property = new SimpleListProperty<Meal>();
        this.plannedMeals10Property = new SimpleListProperty<Meal>();
        this.plannedMeals11Property = new SimpleListProperty<Meal>();
        this.plannedMeals12Property = new SimpleListProperty<Meal>();
        this.plannedMeals13Property = new SimpleListProperty<Meal>();
        this.plannedMeals14Property = new SimpleListProperty<Meal>();
        this.plannedMeals15Property = new SimpleListProperty<Meal>();
        this.plannedMeals16Property = new SimpleListProperty<Meal>();
        this.plannedMeals17Property = new SimpleListProperty<Meal>();
        this.plannedMeals18Property = new SimpleListProperty<Meal>();
        this.plannedMeals19Property = new SimpleListProperty<Meal>();
        this.plannedMeals20Property = new SimpleListProperty<Meal>();
        this.plannedMeals21Property = new SimpleListProperty<Meal>();
        this.plannedMeals22Property = new SimpleListProperty<Meal>();
        this.plannedMeals23Property = new SimpleListProperty<Meal>();
        this.initializeHourPropertyMap();
        this.updateDisplayedDate();
        this.updateDisplayedPlannedMeals();
    }

    private void initializeHourPropertyMap() {
        this.plannedMealProperties = Map.ofEntries(
                Map.entry(Hour.ZERO, this.plannedMeals0Property),
                Map.entry(Hour.ONE, this.plannedMeals1Property),
                Map.entry(Hour.TWO, this.plannedMeals2Property),
                Map.entry(Hour.THREE, this.plannedMeals3Property),
                Map.entry(Hour.FOUR, this.plannedMeals4Property),
                Map.entry(Hour.FIVE, this.plannedMeals5Property),
                Map.entry(Hour.SIX, this.plannedMeals6Property),
                Map.entry(Hour.SEVEN, this.plannedMeals7Property),
                Map.entry(Hour.EIGHT, this.plannedMeals8Property),
                Map.entry(Hour.NINE, this.plannedMeals9Property),
                Map.entry(Hour.TEN, this.plannedMeals10Property),
                Map.entry(Hour.ELEVEN, this.plannedMeals11Property),
                Map.entry(Hour.TWELVE, this.plannedMeals12Property),
                Map.entry(Hour.THIRTEEN, this.plannedMeals13Property),
                Map.entry(Hour.FOURTEEN, this.plannedMeals14Property),
                Map.entry(Hour.FIFTEEN, this.plannedMeals15Property),
                Map.entry(Hour.SIXTEEN, this.plannedMeals16Property),
                Map.entry(Hour.SEVENTEEN, this.plannedMeals17Property),
                Map.entry(Hour.EIGHTEEN, this.plannedMeals18Property),
                Map.entry(Hour.NINETEEN, this.plannedMeals19Property),
                Map.entry(Hour.TWENTY, this.plannedMeals20Property),
                Map.entry(Hour.TWENTY_ONE, this.plannedMeals21Property),
                Map.entry(Hour.TWENTY_TWO, this.plannedMeals22Property),
                Map.entry(Hour.TWENTY_THREE, this.plannedMeals23Property)
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
            newPlannedMeals.addAll((ArrayList<Meal>)entry.getValue());

            this.plannedMealProperties.get(associatedHour).set(newPlannedMeals);
        }
    }

    private void clearPlannedMeals() {
        for (var value : this.plannedMealProperties.values()) {
            value.clear();
        }
    }
}
