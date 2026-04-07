package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.Database;
import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * Instantiates a new meal adder code behind.
 */
public class AddMealCodeBehind {

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button removeIngredientButton;

    @FXML
    private ListView<Ingredient> currentIngredients;

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
        this.addIngredientButton.disableProperty().bind(this.ingredientDisplay.getSelectionModel().selectedItemProperty().isNull());
        this.removeIngredientButton.disableProperty().bind(this.currentIngredients.focusedProperty().not());
        this.currentIngredients.setItems(this.viewModel.getPlannedIngredients());
        this.setDate(SystemInfo.getLoggedInUserId().getUserPlanner().getSelectedDate().toLocalDate());
    }

    private void setDate(LocalDate date) {
        this.viewModel.setDate(date);
        this.dateReminder.textProperty().set("Adding to " + date);
    }

    @FXML
    void addMealToPlanner() {
        if (this.viewModel.addMeal(this.nameField.getText(), this.descField.getText()) != null) {
            this.mealStatus.textProperty().set("Successfully added meal " + this.nameField.getText() + " to planner!");
            this.mealStatus.setTextFill(Color.GREEN);
            this.resetInputFields();
        } else {
            this.mealStatus.textProperty().set("Failed to add meal with no ingredients.");
            this.mealStatus.setTextFill(Color.RED);
        }
    }

    @FXML
    void addIngredientToMeal() {
        if (this.viewModel.addIngredient(this.ingredientDisplay.getSelectionModel().getSelectedItem())) {
            this.mealStatus.textProperty().set("Added ingredient " + this.ingredientDisplay.getSelectionModel().getSelectedItem().getName() + " to meal!");
            this.mealStatus.setTextFill(Color.GREEN);
        } else {
            this.mealStatus.textProperty().set("Failed to add ingredient.");
            this.mealStatus.setTextFill(Color.RED);
        }
    }

    @FXML
    void removeIngredientFromMeal() {
        String nameOfRemoved = "";
        if (this.currentIngredients.getSelectionModel().getSelectedItem() != null) {
            nameOfRemoved = this.currentIngredients.getSelectionModel().getSelectedItem().getName();
        }
        if (this.viewModel.removeIngredient(this.currentIngredients.getSelectionModel().getSelectedItem())) {
            this.mealStatus.textProperty().set("Removed ingredient " + nameOfRemoved + " from meal.");
            this.mealStatus.setTextFill(Color.DARKOLIVEGREEN);
        } else {
            this.mealStatus.textProperty().set("Could not find ingredient.");
            this.mealStatus.setTextFill(Color.RED);
        }
    }

    @FXML
    void resetIngredients() {
        this.viewModel.resetIngredients();
        this.mealStatus.textProperty().set("Cleared prepared ingredients.");
        this.mealStatus.setTextFill(Color.DARKOLIVEGREEN);
    }

    @FXML
    void handlePlannerReturn() {
        new SwitchScene(this.addMealPane, Main.PLANNED_DATE_FXML, Main.PLANNED_DATE_TITLE);
    }

    private void resetInputFields() {
        this.nameField.setText("");
        this.descField.setText("");
    }
}
