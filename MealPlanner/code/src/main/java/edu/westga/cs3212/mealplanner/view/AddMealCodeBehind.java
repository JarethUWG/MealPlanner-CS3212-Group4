package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class AddMealCodeBehind {
    @FXML
    private AnchorPane addMealPane;

    private AddMealViewModel viewModel;

    public AddMealCodeBehind() {
        this.viewModel = new AddMealViewModel();
    }

    public void setDateAndPlanner(LocalDate date, Planner planner) {
        this.viewModel.setDateAndPlanner(date, planner);
    }

    @FXML
    void addMealToPlanner(ActionEvent actionEvent) {
    }

    @FXML
    void addIngredientToMeal(ActionEvent actionEvent) {
    }

    @FXML
    void resetIngredients(ActionEvent actionEvent) {
    }

    @FXML
    void handlePlannerReturn(ActionEvent actionEvent) {
        new SwitchScene(this.addMealPane, "view/plannerPage.fxml");
    }
}
