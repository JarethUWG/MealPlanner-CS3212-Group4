package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Ingredient testIng = new Ingredient("normal ingredient 5", 81.76);
        test.addIngredient(testIng);
        assertTrue(test.getIngredients().contains(testIng));
    }
}
