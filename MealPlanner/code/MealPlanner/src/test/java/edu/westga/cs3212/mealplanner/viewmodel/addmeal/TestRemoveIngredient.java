package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRemoveIngredient {
    private AddMealViewModel viewModel;

    @BeforeEach
    void setup() {
        this.viewModel = new AddMealViewModel();
    }

    @Test
    void TestDoesNotRemoveOnEmptyList() {
        Ingredient test = new Ingredient("Not in list", 11);
        assertFalse(this.viewModel.removeIngredient(test));
    }

    @Test
    void TestDoesNotRemoveOnNull() {
        Ingredient testA = new Ingredient("A", 1);
        Ingredient testB = new Ingredient("B", 2);
        Ingredient testC = new Ingredient("C", 3);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        assertFalse(this.viewModel.removeIngredient(null));
    }

    @Test
    void TestDoesNotRemoveWhenNotFound() {
        Ingredient testA = new Ingredient("A", 1);
        Ingredient testB = new Ingredient("B", 2);
        Ingredient testC = new Ingredient("C", 3);
        Ingredient testD = new Ingredient("D", 4);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        assertFalse(this.viewModel.removeIngredient(testD));
    }

    @Test
    void TestRemovesFirstValue() {
        Ingredient testA = new Ingredient("A", 1);
        Ingredient testB = new Ingredient("B", 2);
        Ingredient testC = new Ingredient("C", 3);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        assertTrue(this.viewModel.removeIngredient(testA));
        assertFalse(this.viewModel.getPlannedIngredients().contains(testA));
    }

    @Test
    void TestRemovesSecondValue() {
        Ingredient testA = new Ingredient("A", 1192831);
        Ingredient testB = new Ingredient("B", 22);
        Ingredient testC = new Ingredient("C", 99);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        assertTrue(this.viewModel.removeIngredient(testB));
        assertFalse(this.viewModel.getPlannedIngredients().contains(testB));
    }

    @Test
    void TestRemovesThirdValue() {
        Ingredient testA = new Ingredient("A", 765);
        Ingredient testB = new Ingredient("B", 19);
        Ingredient testC = new Ingredient("C", 9.15);
        this.viewModel.addIngredient(testA);
        this.viewModel.addIngredient(testB);
        this.viewModel.addIngredient(testC);
        assertTrue(this.viewModel.removeIngredient(testC));
        assertFalse(this.viewModel.getPlannedIngredients().contains(testC));
    }
}
