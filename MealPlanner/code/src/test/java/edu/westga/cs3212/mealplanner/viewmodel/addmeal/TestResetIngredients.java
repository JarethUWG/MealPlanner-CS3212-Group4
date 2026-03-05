package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestResetIngredients {
    private AddMealViewModel viewModel;

    @BeforeEach
    void setup() {
        this.viewModel = new AddMealViewModel();
    }

    @Test
    void testResetAnEmptyList() {
        this.viewModel.resetIngredients();
        assertTrue(this.viewModel.getPlannedIngredients().isEmpty());
    }

    @Test
    void testResetListOfOneIngredient() {
        this.viewModel.addIngredient(new Ingredient("test", 0.1));
        this.viewModel.resetIngredients();
        assertTrue(this.viewModel.getPlannedIngredients().isEmpty());
    }

    @Test
    void testResetListOfNumerousIngredient() {
        this.viewModel.addIngredient(new Ingredient("1", 0.1));
        this.viewModel.addIngredient(new Ingredient("2", 99));
        this.viewModel.addIngredient(new Ingredient("3", 9));
        this.viewModel.addIngredient(new Ingredient("4", 3));
        this.viewModel.addIngredient(new Ingredient("5", 50000));
        this.viewModel.resetIngredients();
        assertTrue(this.viewModel.getPlannedIngredients().isEmpty());
    }
}
