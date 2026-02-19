package edu.westga.cs3211.mealplanner.model;

import java.util.ArrayList;
import java.util.Objects;

public class GroceryList {

    Objects[] shoppingList;

    public GroceryList (ArrayList<Objects> items) {
        this.createGroceryList(items);
    }

    /**
     * Takes an arraylist of ingredients/food items and creates an array for a shopping list
     *
     * @param items arraylist of food items
     */
    public void createGroceryList(ArrayList<Objects> items) {
        this.shoppingList = items.toArray(shoppingList);
    }

    /**
     * Builds a string builder format for the shopping list.
     * @return
     *          a string builder object of the shoppingList
     */
    public StringBuilder outputShoppingList() {
        StringBuilder builder = new StringBuilder();
        for (Object foodItem: this.shoppingList) {
            builder.append(foodItem);
            builder.append("\n");
        }
        return  builder;

    }

}
