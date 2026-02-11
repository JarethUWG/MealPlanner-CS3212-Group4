package edu.westga.cs3212.mealplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application { // The class name can be HelloFX or App etc.

    private static final String LOGIN_FXML = "view/LoginView.fxml";
    private static final String WINDOW_TITLE = "Meal Planner";

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = this.loadGui();
            Scene scene = new Scene(root);


            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private Pane loadGui() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println(getClass());
        System.out.println("FXML resource: " + getClass().getResource(LOGIN_FXML));
        loader.setLocation(getClass().getResource(LOGIN_FXML));
        return loader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}