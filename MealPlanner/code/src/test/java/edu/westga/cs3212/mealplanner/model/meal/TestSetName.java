package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetName {

    @Test
    void testDefaultNameOnChangingToNullName() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "a very normal meal", "");
        test.setName(null);
        String name = "Meal";
        assertEquals(name, test.getName());
    }

    @Test
    void testDefaultNameOnChangingToWhitespaceName() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "a frankly unremarkable meal", "");
        test.setName("");
        String name = "Meal";
        assertEquals(name, test.getName());
    }

    @Test
    void testChangeToValidName() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "a meal?", "");
        test.setName("a fascinatingly intriguing meal!");
        String name = "a fascinatingly intriguing meal!";
        assertEquals(name, test.getName());
    }
}
