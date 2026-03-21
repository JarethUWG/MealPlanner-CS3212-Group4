package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestEmptyIngredients {

    @Test
    void testRemoveAllIngredientsFromEmptyMeal() {
        Meal test = new Meal(null, "still empty", "");
        test.emptyIngredients();
        assertTrue(test.getIngredients().isEmpty());
    }

    @Test
    void testRemoveOneIngredientFromMeal() {
        Meal test = new Meal(null, "one ing", "");
        Ingredient ing = new Ingredient("", 10.0);
        test.addIngredient(ing);
        test.emptyIngredients();
        assertTrue(test.getIngredients().isEmpty());
    }

    @Test
    void testRemoveThreeIngredientsFromMeal() {
        Meal test = new Meal(null, "many ings", "");
        Ingredient ingA = new Ingredient("ing A", 10.0);
        Ingredient ingB = new Ingredient("ing B", 766);
        Ingredient ingC = new Ingredient("ing C", 0.1);
        test.addIngredient(ingA);
        test.addIngredient(ingB);
        test.addIngredient(ingC);
        test.emptyIngredients();
        assertTrue(test.getIngredients().isEmpty());
    }
}
