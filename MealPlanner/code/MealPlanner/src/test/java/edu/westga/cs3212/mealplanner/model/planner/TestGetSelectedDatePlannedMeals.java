package edu.westga.cs3212.mealplanner.model.planner;

import edu.westga.cs3212.mealplanner.enums.MealType;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGetSelectedDatePlannedMeals {

    private Planner planner;
    private int breakfastHour = MealType.BREAKFAST.ordinal();
    private int lunchHour = MealType.LUNCH.ordinal();
    private int dinnerHour = MealType.DINNER.ordinal();

    @BeforeEach
    void setUp() throws Exception {
        this.planner = new Planner();
    }

    @Test
    void testWhenSelectedDateIsNull() {
        var expected = new HashMap<MealType, Iterable<Meal>>();
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenNoPlannedMeals() {
        var expected = new HashMap<MealType, Iterable<Meal>>();
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenOnePlannedMeal() {
        var onlyMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.breakfastHour), onlyMeal);

        var expected = new HashMap<MealType, Iterable<Meal>>();
        expected.put(MealType.BREAKFAST, new ArrayList<Meal>(List.of(onlyMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultiplePlannedMealsInSameHour() {
        var firstMeal = new Meal();
        var middleMeal = new Meal();
        var lastMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.lunchHour), firstMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.lunchHour), middleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.lunchHour), lastMeal);

        var expected = new HashMap<MealType, Iterable<Meal>>();
        expected.put(MealType.LUNCH, new ArrayList<Meal>(Arrays.asList(firstMeal, middleMeal, lastMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultiplePlannedMealsInDifferentHours() {
        var firstMeal = new Meal();
        var middleMeal = new Meal();
        var anotherMiddleMeal = new Meal();
        var lastMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.breakfastHour), firstMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.lunchHour), middleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.lunchHour), anotherMiddleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(this.dinnerHour), lastMeal);

        var expected = new HashMap<MealType, Iterable<Meal>>();
        expected.put(MealType.BREAKFAST, new ArrayList<Meal>(List.of(firstMeal)));
        expected.put(MealType.LUNCH, new ArrayList<Meal>(Arrays.asList(middleMeal, anotherMiddleMeal)));
        expected.put(MealType.DINNER, new ArrayList<Meal>(List.of(lastMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }
}
