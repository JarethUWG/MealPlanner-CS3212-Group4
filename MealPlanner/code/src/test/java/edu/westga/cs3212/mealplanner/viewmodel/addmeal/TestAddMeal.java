package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TestAddMeal {
    private AddMealViewModel viewModel;

    @BeforeEach
    void setup() {
        this.viewModel = new AddMealViewModel();
        SystemInfo.setCurrentPlanner(new Planner());
    }

    @Test
    void testCannotAddWithNoIngredients() {
        assertNull(this.viewModel.addMeal("a meal", ""));
    }

    @Test
    void testAddingOneMeal() {
        LocalDate time = LocalDate.now();
        this.viewModel.addIngredient(new Ingredient("test", 99));
        this.viewModel.setDate(time);
        var mealAdded = this.viewModel.addMeal("meal", "");
        var mealMade = new ArrayList<Meal>(Collections.singletonList(mealAdded));
        var storedMeals = SystemInfo.getCurrentPlanner().getPlannedMeals();
        assertEquals(mealMade, storedMeals);
    }

    @Test
    void testAddingThreeMeals() {
        LocalDate time = LocalDate.now();
        this.viewModel.addIngredient(new Ingredient("ing 1", 99));
        this.viewModel.setDate(time);
        var mealA = this.viewModel.addMeal("A", "");
        this.viewModel.addIngredient(new Ingredient("ing 2", 8881));
        this.viewModel.setDate(time);
        var mealB = this.viewModel.addMeal("B", "");
        this.viewModel.addIngredient(new Ingredient("ing 3", 37));
        this.viewModel.setDate(time);
        var mealC = this.viewModel.addMeal("C", "");
        var mealsMade = new ArrayList<Meal>(Arrays.asList(mealA, mealB, mealC));
        var storedMeals = SystemInfo.getCurrentPlanner().getPlannedMeals();
        assertEquals(mealsMade, storedMeals);
    }
}
