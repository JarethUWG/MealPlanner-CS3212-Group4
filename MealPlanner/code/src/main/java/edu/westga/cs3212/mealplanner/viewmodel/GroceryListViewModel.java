package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The grocery list view model.
 */
public class GroceryListViewModel {

    private GroceryList groceryList;
    private ObservableList<String> ingredientNames;
    private Planner planner;
    private Iterable<Meal> allMeals;

    /**
     * Constructs the viewmodel for the grocery list.
     */
    public GroceryListViewModel() {
        this.groceryList = new GroceryList();
        this.ingredientNames = FXCollections.observableArrayList();
        User user = SystemInfo.getLoggedInUser();
        this.planner = user.getUserPlanner();
        this.allMeals = new ArrayList<>();
    }

    /**
     * The List property of the ingredient names.
     * @return
     *      ingredientNames
     */
    public ObservableList<String> ingredientNamesProperty() {
        return this.ingredientNames;
    }

    /**
     * Gets all the meals from the planner object.
     */
    public void loadMeals() {
        this.allMeals = this.planner.getPlannedMeals();
    }

    /**
     * Gets all the ingredients from allMeals.
     */
    public void loadIngredients() {
        var nameList = this.groceryList.getIngredientNames(this.allMeals);
        this.ingredientNames.setAll(nameList);
    }

}
