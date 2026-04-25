package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.viewmodel.PlannedDateViewmodel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * Planned Date Page code-behind.
 *
 * @author Kirya
 * @version Spring 2025
 */
public class PlannedDateCodeBehind {

    /**
     * The main plane of the view.
     */
    public AnchorPane plannedDatePane;
    /**
     * The header label to display the date.
     */
    public Label dateHeader;
    /**
     * The listview for 12am (0:00).
     */
    public ListView<Meal> breakfastListview;
    /**
     * The listview for 1am (1:00).
     */
    public ListView<Meal> lunchListview;
    /**
     * The listview for 2am (2:00).
     */
    public ListView<Meal> dinnerListview;

    private PlannedDateViewmodel viewModel;

    /**
     * Initializes a new Planned Date Code behind
     */
    public PlannedDateCodeBehind() {
        this.viewModel = new PlannedDateViewmodel();
    }

    @FXML
    void initialize() {
        this.bindToViewmodel();
    }

    private void bindToViewmodel() {
        this.dateHeader.textProperty().bind(this.viewModel.DateProperty());
        this.breakfastListview.itemsProperty().bind(this.viewModel.PlannedBreakfastsProperty());
        this.lunchListview.itemsProperty().bind(this.viewModel.PlannedLunchesProperty());
        this.dinnerListview.itemsProperty().bind(this.viewModel.PlannedDinnersProperty());
    }

    /**
     * Returns the view to the planner page.
     */
    @FXML
    public void returnToPlanner() {
        new SwitchScene(this.plannedDatePane, Main.PLANNER_FXML);
    }

    /**
     * Shows the meal adding page.
     */
    @FXML
    public void showMealAddingPage() {
        new SwitchScene(this.plannedDatePane, Main.ADD_MEAL_FXML, Main.ADD_MEAL_TITLE);
    }
}
