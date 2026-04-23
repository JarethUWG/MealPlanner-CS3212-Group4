package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Instantiates a new landing page code behind.
 *
 * @precondition none
 * @precondition none
 */
public class LandingPageCodeBehind {

    @FXML
    private AnchorPane landingPane;

    @FXML
    void handleLogOut(ActionEvent event) {
        SystemInfo.setLoggedInUser(null);
        SystemInfo.setId(-1);
        Main.getMainStage().setTitle(Main.LOGIN_TITLE);
        new SwitchScene(this.landingPane, Main.LOGIN_FXML);
    }

    @FXML
    void handleGoToPlanner(ActionEvent event) {
        new SwitchScene(this.landingPane, Main.PLANNER_FXML);
        Main.getMainStage().setTitle(Main.PLANNER_TITLE);
    }

    @FXML
    void handleGenerateGrocery(ActionEvent event) {
        Main.getMainStage().setTitle(Main.GROCERY_LIST_TITLE);
        new SwitchScene(this.landingPane, Main.GROCERY_LIST_FXML);
    }
}
