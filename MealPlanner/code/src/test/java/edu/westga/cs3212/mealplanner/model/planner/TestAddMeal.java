package edu.westga.cs3212.mealplanner.model.planner;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.mealplanner.model.Planner;

class TestAddMeal {

    private Planner planner;

    @BeforeEach
    void setUp() throws Exception {
        this.planner = new Planner();
    }

    @Test
    void throwsWhenNullDate() {
        assertThrows(IllegalArgumentException.class, () -> this.planner.addMeal(null, new Meal()));
    }

    @Test
    void throwsWhenNullMeal() {
        assertThrows(IllegalArgumentException.class, () -> this.planner.addMeal(LocalDateTime.now(), null));
    }

    @Test
    void testWhenNoMealsPlannedOnDate() {
        var firstMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now(), firstMeal);

        var expected = new ArrayList<Meal>(List.of(firstMeal));
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenOneMealAlreadyPlannedOnDate() {
        var firstMeal = new Meal();
        var secondMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now(), firstMeal);
        this.planner.addMeal(LocalDateTime.now(), secondMeal);

        var expected = new ArrayList<Meal>(Arrays.asList(firstMeal, secondMeal));
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }

    @Test
    void testWhenMultipleMealsAlreadyPlannedOnDate() {
        var firstMeal = new Meal();
        var secondMeal = new Meal();
        var thirdMeal = new Meal();
        this.planner.addMeal(LocalDateTime.now(), firstMeal);
        this.planner.addMeal(LocalDateTime.now(), secondMeal);
        this.planner.addMeal(LocalDateTime.now(), thirdMeal);

        var expected = new ArrayList<Meal>(Arrays.asList(firstMeal, secondMeal, thirdMeal));
        var actual = this.planner.getPlannedMeals();

        assertEquals(expected, actual);
    }
}
