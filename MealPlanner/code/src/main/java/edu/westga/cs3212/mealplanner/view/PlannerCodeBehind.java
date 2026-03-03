package edu.westga.cs3212.mealplanner.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Planner view code-behind.
 *
 * @author Kirya
 */
public class PlannerCodeBehind {

    /**
     * Grid containing date buttons.
     */
    public GridPane datesGrid;
    /**
     * Calendar header label.
     */
    public Label calendarHeader;

    /**
     * Initialize a new planner code-behind.
     */
    public PlannerCodeBehind() {
    }

    @FXML
    void initialize() {
    }

    /**
     * Shows calendar information for the previous month.
     */
    @FXML
    public void showPreviousMonth() {
    }

    /**
     * Shows calendar information for the next month.
     */
    @FXML
    public void showNextMonth() {
    }

    /**
     * Selects the clicked date.
     *
     * @param event The fired event
     */
    @FXML
    public void selectDate(ActionEvent event) {
        Button pressedDateButton = (Button) event.getSource();

        Point2D columnAndRow = this.getColumnAndRow(pressedDateButton);
    }

    private Point2D getColumnAndRow(Node node) {
        int column = GridPane.getColumnIndex(node) != null ? GridPane.getColumnIndex(node) : 0;
        int row = GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0;

        return new Point2D(column, row);
    }
}
