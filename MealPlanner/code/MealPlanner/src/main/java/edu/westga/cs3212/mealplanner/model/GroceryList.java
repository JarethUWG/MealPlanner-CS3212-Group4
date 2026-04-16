package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class GroceryList stores a collection of food objects.
 */
public class GroceryList {

    /**
     * The shopping list.
     */
    List<String> shoppingList = new ArrayList<>();

    /**
     * Takes in a day parameter and returns all ingredients of the meals from that day.
     * @param allMeals
     *          an iterable list of all meals
     *
     * @return
     *          a list of Strings of ingredient names
     */
    public List<String> getIngredientNames(Iterable<Meal> allMeals) {

        ArrayList<Ingredient> result = new ArrayList<>();

        for (Meal currentMeal : allMeals) {
            result.addAll(currentMeal.getIngredients());
        }

        ArrayList<String> ingredientNames = new ArrayList<>();
        for (Ingredient currIngredient : result) {
            ingredientNames.add(currIngredient.getName());
        }
        return ingredientNames;

    }

}
