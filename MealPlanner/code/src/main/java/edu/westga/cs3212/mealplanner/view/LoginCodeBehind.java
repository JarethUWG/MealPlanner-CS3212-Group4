package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Instantiates a new login code behind.
 *
 * @precondition none
 * @precondition none
 */
public class LoginCodeBehind {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Label passwordReminderText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label usernameReminderText;

    @FXML
    private TextField usernameTextField;

    private LoginViewModel viewModel;

    /**
     * Instantiates a new login code behind.
     *
     * @precondition none
     * @precondition none
     */
    public LoginCodeBehind() {
        this.viewModel = new LoginViewModel();
    }

    @FXML
    void initialize() {
        this.bindComponentsToViewModel();
    }

    private void bindComponentsToViewModel() {
        this.usernameReminderText.textProperty().bindBidirectional(this.viewModel.usernameReminderProperty());
        this.passwordReminderText.textProperty().bindBidirectional(this.viewModel.passwordReminderProperty());
        this.usernameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
        this.passwordTextField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
        this.loginButton.disableProperty().bindBidirectional(this.viewModel.loginDisabledProperty());
        this.viewModel.usernameFocusProperty().bind(this.usernameTextField.focusedProperty());
        this.viewModel.passwordFocusProperty().bind(this.passwordTextField.focusedProperty());
    }

    @FXML
    void createAccount(ActionEvent event) {
        Main.getMainStage().setTitle(Main.CREATE_ACCOUNT_TITLE);
        new SwitchScene(this.loginPane, Main.CREATE_ACCOUNT_FXML);
    }

    @FXML
    void handleLogin(ActionEvent event) {
        if (!this.viewModel.attemptLogin()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials, please try again.");
            alert.showAndWait();
        } else {
            Main.getMainStage().setTitle(Main.LANDING_TITLE);
            new SwitchScene(this.loginPane, Main.LANDING_FXML);
        }
    }

}

