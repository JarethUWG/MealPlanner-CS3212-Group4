package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    void initialize() {

    }

    /**
     * Returns the view to the planner page.
     */
    @FXML
    public void returnToPlanner() {
        new SwitchScene(this.plannedDatePane, Main.PLANNER_FXML);
    }
}
