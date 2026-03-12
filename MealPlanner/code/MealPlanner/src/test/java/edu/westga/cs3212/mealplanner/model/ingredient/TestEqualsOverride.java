package edu.westga.cs3212.mealplanner.model.ingredient;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestEqualsOverride {
    @Test
    void testNonIngredientIsNotEqual() {
        Ingredient test = new Ingredient("test", 84);
        String wrongType = "Not an ingredient";
        assertFalse(test.equals(wrongType));
    }

    @Test
    void testNullIsNotEqual() {
        Ingredient test = new Ingredient("test", 1101);
        assertFalse(test.equals(null));
    }
    @Test
    void testIngredientIsEqualToSelf() {
        Ingredient test = new Ingredient("same ingredient", 11);
        assertTrue(test.equals(test));
    }

    @Test
    void testIngredientOfDifferentNamesNotEqual() {
        Ingredient testA = new Ingredient("same ingredient", 11);
        Ingredient testB = new Ingredient("different ingredient", 11);
        assertFalse(testA.equals(testB));
    }

    @Test
    void testIngredientOfDifferentCaloriesNotEqual() {
        Ingredient testA = new Ingredient("not same ingredient", 51);
        Ingredient testB = new Ingredient("not same ingredient", 15);
        assertFalse(testA.equals(testB));
    }

    @Test
    void testIngredientOfSameParamsAreEqual() {
        Ingredient testA = new Ingredient("same", 99.9);
        Ingredient testB = new Ingredient("same", 99.9);
        assertTrue(testA.equals(testB));
    }
}
