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
    public ListView<Meal> listview0;
    /**
     * The listview for 1am (1:00).
     */
    public ListView<Meal> listview1;
    /**
     * The listview for 2am (2:00).
     */
    public ListView<Meal> listview2;
    /**
     * The listview for 3am (3:00).
     */
    public ListView<Meal> listview3;
    /**
     * The listview for 4am (4:00).
     */
    public ListView<Meal> listview4;
    /**
     * The listview for 5am (5:00).
     */
    public ListView<Meal> listview5;
    /**
     * The listview for 6am (6:00).
     */
    public ListView<Meal> listview6;
    /**
     * The listview for 7am (7:00).
     */
    public ListView<Meal> listview7;
    /**
     * The listview for 8am (8:00).
     */
    public ListView<Meal> listview8;
    /**
     * The listview for 9am (9:00).
     */
    public ListView<Meal> listview9;
    /**
     * The listview for 10am (10:00).
     */
    public ListView<Meal> listview10;
    /**
     * The listview for 11am (11:00).
     */
    public ListView<Meal> listview11;
    /**
     * The listview for 12pm (12:00).
     */
    public ListView<Meal> listview12;
    /**
     * The listview for 1pm (13:00).
     */
    public ListView<Meal> listview13;
    /**
     * The listview for 2pm (14:00).
     */
    public ListView<Meal> listview14;
    /**
     * The listview for 3pm (15:00).
     */
    public ListView<Meal> listview15;
    /**
     * The listview for 4pm (16:00).
     */
    public ListView<Meal> listview16;
    /**
     * The listview for 5pm (17:00).
     */
    public ListView<Meal> listview17;
    /**
     * The listview for 6pm (18:00).
     */
    public ListView<Meal> listview18;
    /**
     * The listview for 7pm (19:00).
     */
    public ListView<Meal> listview19;
    /**
     * The listview for 8pm (20:00).
     */
    public ListView<Meal> listview20;
    /**
     * The listview for 9pm (21:00).
     */
    public ListView<Meal> listview21;
    /**
     * The listview for 10pm (22:00).
     */
    public ListView<Meal> listview22;
    /**
     * The listview for 11pm (23:00).
     */
    public ListView<Meal> listview23;

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
        this.listview0.itemsProperty().bind(this.viewModel.PlannedMeals0Property());
    }

    /**
     * Returns the view to the planner page.
     */
    @FXML
    public void returnToPlanner() {
        new SwitchScene(this.plannedDatePane, Main.PLANNER_FXML);
    }

    @FXML
    public void showMealAddingPage() {
        new SwitchScene(this.plannedDatePane, Main.ADD_MEAL_FXML, Main.ADD_MEAL_TITLE);
    }
}
