package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Basic placeholder database of ingredients.
 * Ingredients are organized in alphabetical order.
 *
 * @author Connor Baesman
 */
public class Database {
    private static List<Ingredient> data;

    static {
        data = new ArrayList<>();
        data.add(new Ingredient("Bread", 100));
        data.add(new Ingredient("Cheese", 50));
        data.add(new Ingredient("Potato", 120));
        data.add(new Ingredient("Milk", 40));
        data.add(new Ingredient("Water", 0));
        data.add(new Ingredient("Broccoli", 25));
        data.add(new Ingredient("Beef", 250));
        data.add(new Ingredient("Pork", 240));
        data.add(new Ingredient("Tomato", 60));
        data.add(new Ingredient("Kale", 10));
        data.sort(Comparator.comparing(Ingredient::getName));
    }

    /**
     * Returns the database of ingredients.
     * @return List of all pre-made ingredients
     */
    public static List<Ingredient> getDatabase() {
        return data;
    }
}
