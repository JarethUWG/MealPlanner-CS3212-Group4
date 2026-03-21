package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestConstructor {

    @Test
    void testDefaultMealConstructor() {
        Meal test = new Meal();
        String name = "New Meal";
        String desc = "No further description";
        assertEquals(name, test.getName());
        assertTrue(test.getIngredients().isEmpty());
        assertEquals(desc, test.getDescription());
    }

    @Test
    void testDefaultListOnNullIngredients() {
        Meal test = new Meal(null, "a meal of sorts", "this one has a null ingredient list");
        assertTrue(test.getIngredients().isEmpty());
    }

    @Test
    void testDefaultNameOnNullName() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, null, "this one has no name");
        String name = "New Meal";
        assertEquals(name, test.getName());
    }

    @Test
    void testDefaultNameOnWhitespaceName() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "", "this one also has no name");
        String name = "New Meal";
        assertEquals(name, test.getName());
    }

    @Test
    void testDefaultDescriptionOnNoDescription() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "this one has no desc", null);
        String desc = "No further description";
        assertEquals(desc, test.getDescription());
    }

    @Test
    void testDefaultDescriptionOnWhitespaceDescription() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "this one also has no desc", "");
        String desc = "No further description";
        assertEquals(desc, test.getDescription());
    }

    @Test
    void testValidConstructorCall() {
        List<Ingredient> placeholderList = new ArrayList<>();
        placeholderList.add(new Ingredient("normal ingredient", 999));
        Meal test = new Meal(placeholderList, "a basic meal", "with a basic description");
        String name = "a basic meal";
        String desc = "with a basic description";
        assertEquals(name, test.getName());
        assertEquals(placeholderList, test.getIngredients());
        assertEquals(desc, test.getDescription());
    }
}
