package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.Database;
import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import javafx.beans.Observable;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * Instantiates a new meal adder code behind.
 */
public class AddMealCodeBehind {

    @FXML
    private Button mealButton;

    @FXML
    private Button ingredientButton;

    @FXML
    private AnchorPane addMealPane;

    @FXML
    private ComboBox<Ingredient> ingredientDisplay;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descField;

    @FXML
    private Label dateReminder;

    @FXML
    private Label mealStatus;

    private AddMealViewModel viewModel;

    /**
     * Instantiates a new meal adder code behind.
     *
     * @pre none
     * @post this.viewModel = new AddMealViewModel()
     */
    public AddMealCodeBehind() {
        this.viewModel = new AddMealViewModel();
    }

    @FXML
    void initialize() {
        this.ingredientDisplay.setItems(FXCollections.observableList(Database.getDatabase()));
        this.ingredientButton.disableProperty().bind(this.ingredientDisplay.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * Indicates what date and planner meals should be added to.
     * @param date The date the meal should be added to.
     * @param planner The planner the meal should be added to.
     */
    public void setDateAndPlanner(LocalDate date, Planner planner) {
        this.viewModel.setDateAndPlanner(date, planner);
        this.dateReminder.textProperty().set("Adding to " + date);
    }

    @FXML
    void addMealToPlanner(ActionEvent actionEvent) {

    }

    @FXML
    void addIngredientToMeal(ActionEvent actionEvent) {
        if(this.viewModel.addIngredient(this.ingredientDisplay.getSelectionModel().getSelectedItem())) {
            this.mealStatus.textProperty().set("Added ingredient " + this.ingredientDisplay.getSelectionModel().getSelectedItem().getName() + " to meal!");
            this.mealStatus.setTextFill(Color.LIGHTGREEN);
        } else {
            this.mealStatus.textProperty().set("Failed to add ingredient.");
            this.mealStatus.setTextFill(Color.RED);
        }
    }

    @FXML
    void resetIngredients(ActionEvent actionEvent) {
        this.viewModel.resetIngredients();
        this.mealStatus.textProperty().set("Cleared prepared ingredients.");
        this.mealStatus.setTextFill(Color.DARKGREEN);
    }

    @FXML
    void handlePlannerReturn(ActionEvent actionEvent) {
        Main.getMainStage().setTitle("Planner");
        new SwitchScene(this.addMealPane, "view/plannerPage.fxml");
    }
}
