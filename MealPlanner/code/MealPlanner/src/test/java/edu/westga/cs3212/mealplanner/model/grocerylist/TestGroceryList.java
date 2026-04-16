package edu.westga.cs3212.mealplanner.model.grocerylist;

import edu.westga.cs3212.mealplanner.model.GroceryList;
import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the grocery list class.
 */
public class TestGroceryList {

    @Test
    void testGetIngredientNamesEmptyList() {
        Iterable<Meal> meals = new ArrayList<Meal>();
        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetIngredientNamesOneMeal() {

        Meal testMeal = new Meal(new ArrayList<>(), "test meal", "description");
        Ingredient ing = new Ingredient("test ingredient", 1.0);
        testMeal.addIngredient(ing);
        List<Meal> meals = new ArrayList<>();
        meals.add(testMeal);
        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertEquals(List.of("test ingredient"), result);
    }

    @Test
    void testGetIngredientNamesThreeMeals() {
        Meal testMeal1 = new Meal(new ArrayList<>(), "test meal1", "description");
        Meal testMeal2 = new Meal(new ArrayList<>(), "test meal2", "description");
        Meal testMeal3 = new Meal(new ArrayList<>(), "test meal3", "description");
        Ingredient ing1 = new Ingredient("test ingredient1", 1.0);
        Ingredient ing2 = new Ingredient("test ingredient2", 10.0);
        Ingredient ing3 = new Ingredient("test ingredient3", 100.0);
        testMeal1.addIngredient(ing1);
        testMeal2.addIngredient(ing2);
        testMeal3.addIngredient(ing3);
        List<Meal> meals = new ArrayList<>();
        meals.add(testMeal1);
        meals.add(testMeal2);
        meals.add(testMeal3);
        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertEquals(List.of("test ingredient1", "test ingredient2", "test ingredient3"), result);
    }

    @Test
    void testGetIngredientNamesOneMealAndThreeIngredient() {
        Meal testMeal1 = new Meal(new ArrayList<>(), "test meal1", "description");
        Ingredient ing1 = new Ingredient("test ingredient1", 1.0);
        Ingredient ing2 = new Ingredient("test ingredient2", 10.0);
        Ingredient ing3 = new Ingredient("test ingredient3", 100.0);
        testMeal1.addIngredient(ing1);
        testMeal1.addIngredient(ing2);
        testMeal1.addIngredient(ing3);
        List<Meal> meals = new ArrayList<>();
        meals.add(testMeal1);
        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertEquals(List.of("test ingredient1", "test ingredient2", "test ingredient3"), result);
    }

    @Test
    void testGetIngredientNamesZeroIngredientMeal() {
        Meal testMeal1 = new Meal(new ArrayList<>(), "test meal1", "description");
        Meal testMeal2 = new Meal(new ArrayList<>(), "test meal2", "description");
        Ingredient ing1 = new Ingredient("test ingredient1", 1.0);
        testMeal1.addIngredient(ing1);
        List<Meal> meals = new ArrayList<>();
        meals.add(testMeal1);
        meals.add(testMeal2);

        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertEquals(List.of("test ingredient1"), result);
    }

    @Test
    void testGetIngredientNamesDuplicateMeals() {
        Meal testMeal1 = new Meal(new ArrayList<>(), "test meal", "description");
        Meal testMeal2 = new Meal(new ArrayList<>(), "test meal", "description");
        Ingredient ing1 = new Ingredient("test ingredient", 10.0);
        Ingredient ing2 = new Ingredient("test ingredient", 10.0);
        testMeal1.addIngredient(ing1);
        testMeal2.addIngredient(ing2);

        List<Meal> meals = new ArrayList<>();
        meals.add(testMeal1);
        meals.add(testMeal2);

        GroceryList list = new GroceryList();
        var result = list.getIngredientNames(meals);
        assertEquals(List.of("test ingredient", "test ingredient"), result);
    }
}
