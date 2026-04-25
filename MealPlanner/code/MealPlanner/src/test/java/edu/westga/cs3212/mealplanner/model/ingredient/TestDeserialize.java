package edu.westga.cs3212.mealplanner.model.ingredient;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TestDeserialize {

    @Test
    void throwsWhenNameIsNotAString() {
        var ingredientInfo = new HashMap<String, Object>();
        ingredientInfo.put("name", 20);
        ingredientInfo.put("calories", 30.2);

        assertThrows(IllegalArgumentException.class, () -> {
            Ingredient.deserialize(ingredientInfo);
        });
    }

    @Test
    void throwsWhenCaloriesIsNotANumber() {
        var ingredientInfo = new HashMap<String, Object>();
        ingredientInfo.put("name", "Valid Name");
        ingredientInfo.put("calories", "This is not a number");

        assertThrows(IllegalArgumentException.class, () -> {
            Ingredient.deserialize(ingredientInfo);
        });
    }

    @Test
    void testWhenCaloriesIsAnInteger() {
        var ingredientInfo = new HashMap<String, Object>();
        ingredientInfo.put("name", "Valid Name");
        ingredientInfo.put("calories", 30);

        var newIngredient = Ingredient.deserialize(ingredientInfo);
        var expectedName = "Valid Name";
        var actualName = newIngredient.getName();
        double expectedCalories = 30;
        var actualCalories = newIngredient.getCalories();

        assertEquals(expectedName, actualName);
        assertEquals(expectedCalories, actualCalories);
    }

    @Test
    void testWhenCaloriesIsADouble() {
        var ingredientInfo = new HashMap<String, Object>();
        ingredientInfo.put("name", "Valid Name");
        ingredientInfo.put("calories", 30.2);

        var newIngredient = Ingredient.deserialize(ingredientInfo);
        var expectedName = "Valid Name";
        var actualName = newIngredient.getName();
        double expectedCalories = 30.2;
        var actualCalories = newIngredient.getCalories();

        assertEquals(expectedName, actualName);
        assertEquals(expectedCalories, actualCalories);
    }
}
