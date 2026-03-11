package edu.westga.cs3212.mealplanner.model.planner;

import edu.westga.cs3212.mealplanner.enums.Hour;
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

    @BeforeEach
    void setUp() throws Exception {
        this.planner = new Planner();
        this.planner.setSelectedDate(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(0));
    }

    @Test
    void testWhenSelectedDateIsNull() {
        this.planner.setSelectedDate(null);
        var expected = new HashMap<Hour, Iterable<Meal>>();
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenNoPlannedMeals() {
        var expected = new HashMap<Hour, Iterable<Meal>>();
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenOnePlannedMeal() {
        var onlyMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(12), onlyMeal);

        var expected = new HashMap<Hour, Iterable<Meal>>();
        expected.put(Hour.TWELVE, new ArrayList<Meal>(List.of(onlyMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultiplePlannedMealsInSameHour() {
        var firstMeal = new Meal();
        var middleMeal = new Meal();
        var lastMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(11), firstMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(11), middleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(11), lastMeal);

        var expected = new HashMap<Hour, Iterable<Meal>>();
        expected.put(Hour.ELEVEN, new ArrayList<Meal>(Arrays.asList(firstMeal, middleMeal, lastMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultiplePlannedMealsInDifferentHours() {
        var firstMeal = new Meal();
        var middleMeal = new Meal();
        var anotherMiddleMeal = new Meal();
        var lastMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(10), firstMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(11), middleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(11), anotherMiddleMeal);
        this.planner.addMeal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).withHour(17), lastMeal);

        var expected = new HashMap<Hour, Iterable<Meal>>();
        expected.put(Hour.TEN, new ArrayList<Meal>(List.of(firstMeal)));
        expected.put(Hour.ELEVEN, new ArrayList<Meal>(Arrays.asList(middleMeal, anotherMiddleMeal)));
        expected.put(Hour.SEVENTEEN, new ArrayList<Meal>(List.of(lastMeal)));
        var actual = this.planner.getSelectedDatePlannedMeals();

        assertEquals(expected, actual);
    }
}
