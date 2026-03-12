package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAddIngredient {
    private AddMealViewModel viewModel;

    @BeforeEach
    void setup() {
        this.viewModel = new AddMealViewModel();
    }

    @Test
    void testDoesNotAddNullIngredient() {
        assertFalse(this.viewModel.addIngredient(null));
    }

    @Test
    void testAddsOneIngredient() {
        Ingredient test = new Ingredient("An Ingredient", 1);
        this.viewModel.addIngredient(test);
        assertTrue(this.viewModel.getPlannedIngredients().contains(test));
    }

    @Test
    void testAddsNumerousIngredients() {
        Ingredient testA = new Ingredient("A", 1);
        Ingredient testB = new Ingredient("B", 5);
        Ingredient testC = new Ingredient("C", 100);
        Ingredient testD = new Ingredient("D", 555);
        Ingredient testE = new Ingredient("E", 87321);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        this.viewModel.addIngredient(testD);
        this.viewModel.addIngredient(testE);
        assertTrue(this.viewModel.getPlannedIngredients().contains(testA));
        assertTrue(this.viewModel.getPlannedIngredients().contains(testB));
        assertTrue(this.viewModel.getPlannedIngredients().contains(testC));
        assertTrue(this.viewModel.getPlannedIngredients().contains(testD));
        assertTrue(this.viewModel.getPlannedIngredients().contains(testE));
    }
}
