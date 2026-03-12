package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRemoveIngredient {
    //fail to remove from a list of three
    //only removes one of numerous identical items

    @Test
    void testDoesNotRemoveFromAnEmptyMeal() {
        Meal test = new Meal(null, "empty", "");
        Ingredient ing = new Ingredient("this isn't in the list", 1.0);
        assertFalse(test.removeIngredient(ing));
    }

    @Test
    void testRemovesFromOneIngredientMeal() {
        Meal test = new Meal(null, "just one", "");
        Ingredient ing = new Ingredient("plain ingredient", 7);
        test.addIngredient(ing);
        assertTrue(test.removeIngredient(ing));
    }

    @Test
    void testRemovesFirstIngredientFromMeal() {
        Meal test = new Meal(null, "get the first!", "");
        Ingredient ingA = new Ingredient("ing A", 99);
        Ingredient ingB = new Ingredient("ing B", 22);
        Ingredient ingC = new Ingredient("ing C", 222222222);
        test.addIngredient(ingA);
        test.addIngredient(ingB);
        test.addIngredient(ingC);
        test.removeIngredient(ingA);
        assertFalse(test.getIngredients().contains(ingA));
    }

    @Test
    void testRemovesSecondIngredientFromMeal() {
        Meal test = new Meal(null, "get the second!", "");
        Ingredient ingD = new Ingredient("ing D", 413);
        Ingredient ingE = new Ingredient("ing E", 1.134);
        Ingredient ingF = new Ingredient("ing F", 8.87);
        test.addIngredient(ingD);
        test.addIngredient(ingE);
        test.addIngredient(ingF);
        test.removeIngredient(ingE);
        assertFalse(test.getIngredients().contains(ingE));
    }

    @Test
    void testRemovesLastIngredientFromMeal() {
        Meal test = new Meal(null, "get the last!", "");
        Ingredient ingG = new Ingredient("ing G", 0);
        Ingredient ingH = new Ingredient("ing H", 3212.2);
        Ingredient ingI = new Ingredient("ing I", 55.4);
        test.addIngredient(ingG);
        test.addIngredient(ingH);
        test.addIngredient(ingI);
        test.removeIngredient(ingI);
        assertFalse(test.getIngredients().contains(ingI));
    }

    @Test
    void testDoesNotRemoveIngredientNotPresent() {
        Meal test = new Meal(null, "get the first!", "");
        Ingredient ingA = new Ingredient("ing A", 66);
        Ingredient ingB = new Ingredient("ing B", 765.4);
        Ingredient ingC = new Ingredient("ing C", 100000);
        Ingredient ingD = new Ingredient("ing D", 13.13);
        test.addIngredient(ingA);
        test.addIngredient(ingB);
        test.addIngredient(ingD);
        assertFalse(test.removeIngredient(ingC));
    }

    @Test
    void testOnlyRemovesFirstOfIdenticalIngredients() {
        Meal test = new Meal(null, "get the first!", "");
        List<Ingredient> comparisonList = new ArrayList<>();
        Ingredient ingA = new Ingredient("ing A", 33.3);
        Ingredient ingB = new Ingredient("ing B", 1.8907);
        Ingredient ingC = new Ingredient("ing C", 500);
        test.addIngredient(ingA);
        test.addIngredient(ingB);
        test.addIngredient(ingA);
        test.addIngredient(ingC);
        comparisonList.add(ingB);
        comparisonList.add(ingA);
        comparisonList.add(ingC);
        test.removeIngredient(ingA);
        assertEquals(comparisonList, test.getIngredients());
    }
}
