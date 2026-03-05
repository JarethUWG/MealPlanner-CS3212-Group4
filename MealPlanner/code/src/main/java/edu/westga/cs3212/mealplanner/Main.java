package edu.westga.cs3212.mealplanner;

import java.io.IOException;

import edu.westga.cs3212.mealplanner.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Main Application class.
 * @author CS 3211
 * @version Fall 2025
 */
public class Main extends Application {
    public static final String LOGIN_TITLE = "Login";
    public static final String LOGIN_FXML = "view/login.fxml";
    public static final String CREATE_ACCOUNT_TITLE = "Create account";
    public static final String CREATE_ACCOUNT_FXML = "view/createAccount.fxml";
    public static final String LANDING_TITLE = "Landing Page";
    public static final String LANDING_FXML = "view/landingPage.fxml";
    public static final String ADD_MEAL_TITLE = "Add meal";
    public static final String ADD_MEAL_FXML = "addMeal.fxml";
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        try {
            Pane root = this.loadGui();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle(LOGIN_TITLE);
            primaryStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private Pane loadGui() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(LOGIN_FXML));
        return loader.load();
    }

    /**
     * Gets the main stage of the application.
     *
     * @return The main stage of the application.
     */
    public static Stage getMainStage() {
        return mainStage;
    }

    /**
     * Entry point for the application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
