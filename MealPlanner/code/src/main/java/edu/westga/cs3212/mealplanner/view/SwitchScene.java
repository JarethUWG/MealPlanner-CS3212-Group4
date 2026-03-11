package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

/**
 * A utility class for changing scenes.
 *
 * @author Jareth Batty
 * @version Fall 2025
 */
public class SwitchScene {

    /**
     * Changes scenes by removing the children of a pane and replacing
     * them with those of a new pane.
     *
     * @param currentPane The anchor pane of the current scene.
     * @param nextScene The anchor pane of next scene.
     */
    public SwitchScene(AnchorPane currentPane, String nextScene) {
        try {
            AnchorPane nextPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(nextScene)));
            currentPane.getChildren().clear();
            currentPane.getChildren().addAll(nextPane);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes scenes by removing the children of a pane and replacing
     * them with those of a new pane and updates the window title.
     *
     * @param currentPane The anchor pane of the current scene.
     * @param nextScene The anchor pane of next scene.
     * @param nextTitle The title of the next scene.
     */
    public SwitchScene(AnchorPane currentPane, String nextScene, String nextTitle) {
        new SwitchScene(currentPane, nextScene);
        Main.getMainStage().setTitle(nextTitle);
    }

}
