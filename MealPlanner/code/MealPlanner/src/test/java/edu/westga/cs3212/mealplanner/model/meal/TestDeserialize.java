package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDeserialize {

    @Test
    void throwsWhenNameIsNotAString() {
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", 20);
        mealInfo.put("ingredients", new ArrayList<Object>());
        mealInfo.put("description", "Valid Description");

        assertThrows(IllegalArgumentException.class, () -> {
            Meal.deserialize(mealInfo);
        });
    }

    @Test
    void throwsWhenIngredientsIsNotAList() {
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", "Valid Name");
        mealInfo.put("ingredients", "Invalid Ingredients");
        mealInfo.put("description", "Valid Description");

        assertThrows(IllegalArgumentException.class, () -> {
            Meal.deserialize(mealInfo);
        });
    }

    @Test
    void throwsWhenDescriptionIsNotAString() {
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", "Valid Name");
        mealInfo.put("ingredients", new ArrayList<Object>());
        mealInfo.put("description", false);

        assertThrows(IllegalArgumentException.class, () -> {
            Meal.deserialize(mealInfo);
        });
    }

    @Test
    void testWhenEmptyIngredients() {
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", "Valid Name");
        mealInfo.put("ingredients", new ArrayList<Object>());
        mealInfo.put("description", "Valid Description");

        var newMeal = Meal.deserialize(mealInfo);
        var expectedName = "Valid Name";
        var actualName = newMeal.getName();
        var expectedIngredients = new ArrayList<Ingredient>();
        var actualIngredients = newMeal.getIngredients();
        var expectedDescription = newMeal.getDescription();
        var actualDescription = "Valid Description";

        assertEquals(expectedName, actualName);
        assertEquals(expectedIngredients, actualIngredients);
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void testWhenMultipleIngredients() {
        var mealInfo = new HashMap<String, Object>();
        var ingredientsInfo = new ArrayList<Map<String, Object>>();
        var firstIngredientInfo = new HashMap<String, Object>();
        var secondIngredientInfo = new HashMap<String, Object>();
        firstIngredientInfo.put("name", "First Ingredient");
        firstIngredientInfo.put("calories", 25);
        secondIngredientInfo.put("name", "Second Ingredient");
        secondIngredientInfo.put("calories", 50);
        ingredientsInfo.add(firstIngredientInfo);
        ingredientsInfo.add(secondIngredientInfo);
        mealInfo.put("name", "Valid Name");
        mealInfo.put("ingredients", ingredientsInfo);
        mealInfo.put("description", "Valid Description");

        var newMeal = Meal.deserialize(mealInfo);
        var expectedName = "Valid Name";
        var actualName = newMeal.getName();
        var expectedIngredientsCount = 2;
        var actualIngredientsCount = newMeal.getIngredients().size();
        var expectedDescription = "Valid Description";
        var actualDescription = newMeal.getDescription();

        assertEquals(expectedName, actualName);
        assertEquals(expectedIngredientsCount, actualIngredientsCount);
        assertEquals(expectedDescription, actualDescription);
    }
}
