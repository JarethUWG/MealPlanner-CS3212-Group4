package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestToStringOverride {

    @Test
    void testMealToStringWithNoIngredients() {
        Meal test = new Meal(null, "This Is A Meal", "It has no items");
        String toStringExpected = """
                This Is A Meal: It has no items
                Ingredients:
                - No ingredients
                """;
        assertEquals(toStringExpected, test.toString());
    }

    @Test
    void testMealToStringWithOneIngredient() {
        Meal test = new Meal(null, "My second meal", "Making a meal out of a piece of cheese seems a concern of mental health.");
        test.addIngredient(new Ingredient("String Cheese", 80));
        String toStringExpected = """
                My second meal: Making a meal out of a piece of cheese seems a concern of mental health.
                Ingredients:
                - String Cheese, 80.0 calories
                """;
        assertEquals(toStringExpected, test.toString());
    }

    @Test
    void testMealToStringWithMultipleIngredients() {
        Meal test = new Meal(null, "generic name", "a true delicacy.");
        test.addIngredient(new Ingredient("Grape Juice", 45));
        test.addIngredient(new Ingredient("Cosmic Brownie", 290.1));
        test.addIngredient(new Ingredient("Focaccia Roll", 120.99));
        String toStringExpected = """
                generic name: a true delicacy.
                Ingredients:
                - Grape Juice, 45.0 calories
                - Cosmic Brownie, 290.1 calories
                - Focaccia Roll, 120.99 calories
                """;
        assertEquals(toStringExpected, test.toString());
    }
}
