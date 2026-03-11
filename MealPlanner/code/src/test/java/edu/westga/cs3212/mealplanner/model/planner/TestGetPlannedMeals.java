package edu.westga.cs3212.mealplanner.model.planner;

import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestGetPlannedMeals {

    private Planner planner;

    @BeforeEach
    void setUp() throws Exception {
        this.planner = new Planner();
    }

    @Test
    void testParameterlessWhenNoPlannedMeals() {
        var expected = new ArrayList<Meal>();
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testParameterlessWhenOnePlannedMeal() {
        var onlyMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now(), onlyMeal);

        var expected = new ArrayList<Meal>(List.of(onlyMeal));
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testParameterlessWhenMultiplePlannedMeals() {
        var firstMeal = new Meal();
        var middleMeal = new Meal();
        var lastMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now(), firstMeal);
        this.planner.addMeal(LocalDateTime.now(), middleMeal);
        this.planner.addMeal(LocalDateTime.now(), lastMeal);

        var expected = new ArrayList<Meal>(Arrays.asList(firstMeal, middleMeal, lastMeal));
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void throwsWhenFromDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> this.planner.getPlannedMeals(null, LocalDateTime.now()));
    }

    @Test
    void throwsWhenToDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> this.planner.getPlannedMeals(LocalDateTime.now(), null));
    }

    @Test
    void testWhenNoPlannedMealsInDateRange() {
        var plannedMeal = new Meal();
        var plannedDate = LocalDateTime.now();
        this.planner.addMeal(plannedDate, plannedMeal);

        var expected = new ArrayList<Meal>();
        var actual = this.planner.getPlannedMeals(plannedDate.plusDays(1), plannedDate.plusDays(2));

        assertEquals(expected, actual);
    }

    @Test
    void testWhenOnePlannedMealInDateRange() {
        var firstDate = LocalDateTime.now();
        var firstMeal = new Meal();
        var secondDate = firstDate.plusDays(5);
        var secondMeal = new Meal();
        this.planner.addMeal(firstDate, firstMeal);
        this.planner.addMeal(secondDate, secondMeal);

        var expected = new ArrayList<Meal>(List.of(secondMeal));
        var actual = this.planner.getPlannedMeals(secondDate.minusDays(1), secondDate.plusDays(1));

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultiplePlannedMealsInDateRange() {
        var firstDate = LocalDateTime.now();
        var firstMeal = new Meal();
        var secondDate = firstDate.plusDays(1);
        var secondMeal = new Meal();
        var thirdDate = firstDate.plusDays(2);
        var thirdMeal = new Meal();
        var fourthDate = firstDate.plusDays(3);
        var fourthMeal = new Meal();
        var fifthDate = firstDate.plusDays(4);
        var fifthMeal = new Meal();
        this.planner.addMeal(firstDate, firstMeal);
        this.planner.addMeal(secondDate, secondMeal);
        this.planner.addMeal(thirdDate, thirdMeal);
        this.planner.addMeal(fourthDate, fourthMeal);
        this.planner.addMeal(fifthDate, fifthMeal);

        var expected = new ArrayList<Meal>(Arrays.asList(secondMeal, thirdMeal, fourthMeal));
        var actual = this.planner.getPlannedMeals(firstDate.plusDays(1), fifthDate.minusDays(1));

        assertEquals(expected, actual);
    }

}
