package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.GroceryList;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
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
        this.planner = new Planner();
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

    public void loadMeals() {
        this.allMeals = this.planner.getPlannedMeals();
    }

    public void loadIngredients() {
        var nameList = this.groceryList.getIngredientNames(this.allMeals);
        this.ingredientNames.setAll(nameList);
    }

}
