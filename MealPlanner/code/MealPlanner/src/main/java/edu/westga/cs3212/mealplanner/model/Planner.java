package edu.westga.cs3212.mealplanner.model;

import edu.westga.cs3212.mealplanner.enums.MealType;

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
    public Map<MealType, Iterable<Meal>> getSelectedDatePlannedMeals() {
        var clientSelectedDate = SystemInfo.getSelectedCalendarDate();
        if (clientSelectedDate == null) {
            return new HashMap<MealType, Iterable<Meal>>();
        }

        var mealTypeValues = MealType.values();
        HashMap<MealType, Iterable<Meal>> convertedMap = new HashMap<>();
        var startOfDate = this.simplifyDateTime(clientSelectedDate);
        var endOfDate = this.simplifyDateTime(clientSelectedDate.plusDays(1));

        for (var entry : this.plannedMeals.subMap(startOfDate, endOfDate).entrySet()) {
            LocalDateTime associatedDate = LocalDateTime.ofEpochSecond(entry.getKey(), 0, ZoneOffset.UTC);
            var plannedMealType = mealTypeValues[associatedDate.getHour()];
            var plannedMeals = entry.getValue();

            convertedMap.put(plannedMealType, plannedMeals);
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
     * @throws IllegalArgumentException If necessary information is missing from serializedInfo
     */
    public static Planner deserialize(Map<String, Object> serializedInfo) {
        System.out.println(serializedInfo);
        var newPlanner = new Planner();

        for (var keyVar : serializedInfo.entrySet()) {
            var timeEpoch = LocalDateTime.ofEpochSecond(Long.parseLong(keyVar.getKey()), 0, ZoneOffset.UTC);
            var plannedMealsInfo = (List<Map<String, Object>>) keyVar.getValue();

            for (var mealInfo : plannedMealsInfo) {
                newPlanner.addMeal(timeEpoch, Meal.deserialize(mealInfo));
            }
        }

        return newPlanner;
    }

    private long simplifyDateTime(LocalDateTime dateToSimplify) {
        var truncatedToHour = dateToSimplify.truncatedTo(ChronoUnit.HOURS);
        return truncatedToHour.toEpochSecond(ZoneOffset.UTC);
    }
}
