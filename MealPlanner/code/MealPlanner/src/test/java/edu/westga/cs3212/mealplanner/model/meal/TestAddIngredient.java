package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAddIngredient {

    @Test
    void testDoesNotAddNullIngredient() {
        Meal test = new Meal(null, "a normal meal", "");
        test.addIngredient(null);
        assertFalse(test.getIngredients().contains(null));
    }

    @Test
    void testAddsValidIngredient() {
        Meal test = new Meal(null, "a normal meal", "");
        Ingredient testIng = new Ingredient("normal ingredient       ", 81.76);
        test.addIngredient(testIng);
        assertTrue(test.getIngredients().contains(testIng));
    }

    @Test
    void testAddsMultipleValidIngredients() {
        Meal test = new Meal(null, "a slightly abnormal meal", "");
        Ingredient testIng1 = new Ingredient("normal ingredient 1", 99.899);
        Ingredient testIng2 = new Ingredient("normal ingredient 2", 0.31);
        Ingredient testIng3 = new Ingredient("normal ingredient 3", 100000);
        test.addIngredient(testIng1);
        test.addIngredient(testIng2);
        test.addIngredient(testIng3);
        assertTrue(test.getIngredients().contains(testIng1));
        assertTrue(test.getIngredients().contains(testIng2));
        assertTrue(test.getIngredients().contains(testIng3));
    }
}
