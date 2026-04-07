package edu.westga.cs3212.mealplanner.model;

import edu.westga.cs3212.mealplanner.enums.Hour;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Defines a planner object.
 *
 * @author Kirya Duncan II
 * @version Spring 2026
 */
public class Planner {

    private TreeMap<Long, ArrayList<Meal>> plannedMeals;
    private LocalDateTime selectedDate;

    /**
     * Sets the currently selected date.
     * @param newDate The new date to set the planner to.
     */
    public void setSelectedDate(LocalDateTime newDate) {
        this.selectedDate = newDate;
    }

    /**
     * Returns the currently selected date.
     * @return The current selected date
     */
    public LocalDateTime getSelectedDate() {
        return this.selectedDate;
    }

    /**
     * Initializes a new Planner.
     */
    public Planner() {
        this.plannedMeals = new TreeMap<>();
    }

    /**
     * Plans the given meal on the given date.
     *
     * @precondition date != null && newMeal != null
     * @postcondition getPlannedMeals() return will include newMeal if date is in the given range
     * @param date The date to plan the meal for
     * @param newMeal The meal to add
     */
    public void addMeal(LocalDateTime date, Meal newMeal) {
        if (date == null) {
            throw new IllegalArgumentException("Date can't be null");
        }
        if (newMeal == null) {
            throw new IllegalArgumentException("Meal can't be null");
        }

        LocalDateTime truncatedDate = date.truncatedTo(ChronoUnit.HOURS);
        long epochHour = truncatedDate.toEpochSecond(ZoneOffset.UTC);

        if (this.plannedMeals.containsKey(epochHour)) {
            var alreadyPlannedMeals = this.plannedMeals.get(epochHour);
            alreadyPlannedMeals.add(newMeal);
        } else {
            var newPlannedMeals = new ArrayList<Meal>(List.of(newMeal));
            this.plannedMeals.put(epochHour, newPlannedMeals);
        }
    }

    /**
     * Returns a mapping of meals and the hours they are planned for on the currently selected date.
     * @return An hour and Meal iterable mapping
     */
    public Map<Hour, Iterable<Meal>> getSelectedDatePlannedMeals() {
        if (this.selectedDate == null) {
            return new HashMap<Hour, Iterable<Meal>>();
        }

        var hourValues = Hour.values();
        HashMap<Hour, Iterable<Meal>> convertedMap = new HashMap<>();
        var startOfDate = this.simplifyDateTime(this.selectedDate);
        var endOfDate = this.simplifyDateTime(this.selectedDate.plusDays(1));

        for (var entry : this.plannedMeals.subMap(startOfDate, endOfDate).entrySet()) {
            LocalDateTime associatedDate = LocalDateTime.ofEpochSecond(entry.getKey(), 0, ZoneOffset.UTC);
            var plannedHour = hourValues[associatedDate.getHour()];
            var plannedMeals = entry.getValue();

            convertedMap.put(plannedHour, plannedMeals);
        }

        return convertedMap;
    }

    /**
     * Returns an iterable over all the currently planned meals.
     * @return An iterable over all planned meals
     */
    public Iterable<Meal> getPlannedMeals() {
        var allPlannedMeals = new ArrayList<Meal>();

        for (var plannedMeals : this.plannedMeals.values()) {
            allPlannedMeals.addAll(plannedMeals);
        }

        return allPlannedMeals;
    }

    /**
     * Returns an iterable over all the currently planned meals
     * in the (inclusive) date range from-to.
     *
     * @precondition from != null && to != null
     *
     * @param from The start of the date range
     * @param to The end of the date range
     * @return An iterable over the meals planned between from and to
     */
    public Iterable<Meal> getPlannedMeals(LocalDateTime from, LocalDateTime to) {
        if (from == null) {
            throw new IllegalArgumentException("Date can't be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("Meal can't be null");
        }

        var mealsInPlannedRange = new ArrayList<Meal>();
        var simpleFrom = this.simplifyDateTime(from);
        var simpleTo = this.simplifyDateTime(to);

        for (var plannedMeals : this.plannedMeals.subMap(simpleFrom, simpleTo + 1).values()) {
            mealsInPlannedRange.addAll(plannedMeals);
        }

        return mealsInPlannedRange;
    }

    /**
     * Returns a new Planner object from the given info.
     * @param serializedInfo The serialized information to deserialize from.
     * @return The newly instantiated Planner
     */
    public static Planner deserialize(Map<Long, Object> serializedInfo) {
        var newPlanner = new Planner();

        for (var keyVar : serializedInfo.entrySet()) {
            var timeEpoch = LocalDateTime.ofEpochSecond(keyVar.getKey(), 0, ZoneOffset.UTC);
            var mealInformation = (Map<String, Object>) keyVar.getValue();

            newPlanner.addMeal(timeEpoch, Meal.deserialize(mealInformation));
        }

        return newPlanner;
    }

    private long simplifyDateTime(LocalDateTime dateToSimplify) {
        var truncatedToHour = dateToSimplify.truncatedTo(ChronoUnit.HOURS);
        return truncatedToHour.toEpochSecond(ZoneOffset.UTC);
    }
}
