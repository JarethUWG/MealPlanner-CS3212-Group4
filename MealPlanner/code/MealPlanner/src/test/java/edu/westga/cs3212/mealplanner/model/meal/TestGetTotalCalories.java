package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetTotalCalories {
    //no ingredients
    //one ingredient
    //three ingredients
    //fuckton of ingredients

    @Test
    void testCaloriesOfNoIngredients() {
        Meal test = new Meal(null, "empty meal", "");
        assertEquals(0, test.getTotalCalories());
    }

    @Test
    void testCaloriesOfOneIngredient() {
        Meal test = new Meal(null, "simple meal", "");
        test.addIngredient(new Ingredient("normal ingredient", 18.1));
        assertEquals(18.1, test.getTotalCalories());
    }

    @Test
    void testCaloriesOfThreeIngredients() {
        Meal test = new Meal(null, "complex meal", "");
        test.addIngredient(new Ingredient("normal ingredient A", 72));
        test.addIngredient(new Ingredient("normal ingredient B", 188.99));
        test.addIngredient(new Ingredient("normal ingredient C", 9.63));
        assertEquals(270.62, test.getTotalCalories());
    }

    @Test
    void testCaloriesOfTenIngredients() {
        Meal test = new Meal(null, "hopelessly complex meal", "");
        test.addIngredient(new Ingredient("normal ingredient A", 9));
        test.addIngredient(new Ingredient("normal ingredient B", 11.55));
        test.addIngredient(new Ingredient("normal ingredient C", 341));
        test.addIngredient(new Ingredient("normal ingredient D", 0));
        test.addIngredient(new Ingredient("normal ingredient E", 99.87));
        test.addIngredient(new Ingredient("normal ingredient F", 32.4));
        test.addIngredient(new Ingredient("normal ingredient G", 29.1));
        test.addIngredient(new Ingredient("normal ingredient H", 76.33));
        test.addIngredient(new Ingredient("normal ingredient I", 81));
        test.addIngredient(new Ingredient("normal ingredient J", 0.0001));
        assertEquals(680.2501, test.getTotalCalories());
    }
}
