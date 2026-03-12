package edu.westga.cs3212.mealplanner.model.ingredient;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestConstructor {

    @Test
    void testDefaultNameOnNullName() {
        Ingredient test = new Ingredient(null, 133.7);
        String defaultName = "Unnamed Ingredient";
        assertEquals(defaultName, test.getName());
    }

    @Test
    void testDefaultNameOnNoName() {
        Ingredient test = new Ingredient("", 133.7);
        String defaultName = "Unnamed Ingredient";
        assertEquals(defaultName, test.getName());
    }

    @Test
    void testDefaultNameOnWhitespaceName() {
        Ingredient test = new Ingredient("       ", 133.7);
        String defaultName = "Unnamed Ingredient";
        assertEquals(defaultName, test.getName());
    }

    @Test
    void testSetsMinimumCaloriesOnNegativeAmount() {
        Ingredient test = new Ingredient("nonspecific test product", -1);
        int minCalories = 0;
        assertEquals(minCalories, test.getCalories());
    }

    @Test
    void testValidConstructorCall() {
        Ingredient test = new Ingredient("nonspecific test product", 133.7);
        String name = "nonspecific test product";
        double calories = 133.7;
        assertEquals(calories, test.getCalories());
        assertEquals(name, test.getName());
    }
}
