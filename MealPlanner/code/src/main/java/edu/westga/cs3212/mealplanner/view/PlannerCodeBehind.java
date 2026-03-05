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

    private PlannerViewModel viewModel;

    /**
     * Initialize a new planner code-behind.
     */
    public PlannerCodeBehind() {
        this.viewModel = new PlannerViewModel();
    }

    @FXML
    void initialize() {
        this.updateEnabledButtons();
        this.calendarHeader.textProperty().bind(this.viewModel.CalendarHeaderProperty());
    }

    /**
     * Shows calendar information for the previous month.
     */
    @FXML
    public void showPreviousMonth() {
        this.viewModel.DisplayPreviousMonth();
        this.updateEnabledButtons();
    }

    /**
     * Shows calendar information for the next month.
     */
    @FXML
    public void showNextMonth() {
        this.viewModel.DisplayNextMonth();
        this.updateEnabledButtons();
    }

    private void updateEnabledButtons() {
        for (Node childNode : this.datesGrid.getChildren()) {
            Button buttonNode = (Button) childNode;
            Point2D point = this.getColumnAndRow(childNode);
            int dayThisMonth = this.viewModel.GetDayThisMonth((int) point.getX(), (int) point.getY());
            boolean dayIsInMonth = dayThisMonth != -1;

            childNode.setDisable(!dayIsInMonth);

            if (dayIsInMonth) {
                buttonNode.setText(String.valueOf(dayThisMonth));
            } else {
                buttonNode.setText("");
            }
        }
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
