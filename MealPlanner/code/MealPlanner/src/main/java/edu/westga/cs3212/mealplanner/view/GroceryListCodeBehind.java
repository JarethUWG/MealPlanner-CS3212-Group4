package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.viewmodel.GroceryListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * Instantiates a new GroceryList code behind.
 */
public class GroceryListCodeBehind {

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane groceryPane;

    @FXML
    private ListView<String> groceryListView;

    private GroceryListViewModel viewModel;

    /**
     * initializes the grocerylist codebehind.
     */
    public void initialize() {
        this.viewModel = new GroceryListViewModel();
        this.groceryListView.setItems(this.viewModel.ingredientNamesProperty());

        this.viewModel.loadMeals();
        this.loadIngredientList();
    }

    /**
     * Gets the list of ingredients from the viewmodel.
     */
    public void loadIngredientList() {
        this.viewModel.loadIngredients();
    }

    /**
     * Returns the view to the landing page.
     */
    @FXML
    public void returnToLandingPage() {
        new SwitchScene(this.groceryPane, Main.LANDING_FXML, Main.LANDING_TITLE);
    }

}
