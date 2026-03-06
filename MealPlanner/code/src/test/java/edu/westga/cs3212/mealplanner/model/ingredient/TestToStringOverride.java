package edu.westga.cs3212.mealplanner.model.ingredient;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestToStringOverride {

    @Test
    void testIngredientToString() {
        Ingredient test = new Ingredient("My ingredient", 15.5);
        String toStringExpected = "My ingredient, 15.5 calories";
        assertEquals(toStringExpected, test.toString());
    }
}
