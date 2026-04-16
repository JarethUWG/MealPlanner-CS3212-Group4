package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class GroceryList stores a collection of food objects.
 */
public class GroceryList {

    /**
     * The shopping list
     */
    List<String> shoppingList = new ArrayList<>();

    /**
     * Takes in a day parameter and returns all ingredients of the meals from that day.
     * @param map
     *          the hashmap of meals
     * @param day
     *          the selected day
     * @return
     *          a list of Strings of ingredient names
     */
    public List<String> getNamesForDay(HashMap<String, Object> map, int day) {


        //TODO: Plug in meal object when implemented
//        for (Object currentMeal : map.values()) {
//            if (currentMeal.getDay() == day) {
//                result.add(currentMeal.getIngredient());
//            }
//        }

        return shoppingList;
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
