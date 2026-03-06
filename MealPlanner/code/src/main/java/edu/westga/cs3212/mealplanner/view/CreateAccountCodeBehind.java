package edu.westga.cs3212.mealplanner.view;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Instantiates a new create account code behind.
 *
 * @precondition none
 * @precondition none
 */
public class CreateAccountCodeBehind {

    @FXML
    private Button createAccountButton;

    @FXML
    private AnchorPane createAccountPane;

    @FXML
    private Label passwordReminderText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label usernameReminderText;

    @FXML
    private TextField usernameTextField;

    private CreateAccountViewModel viewModel;

    /**
     * Instantiates a new create account code behind.
     *
     * @precondition none
     * @precondition none
     */
    public CreateAccountCodeBehind() {
        this.viewModel = new CreateAccountViewModel();
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
        this.createAccountButton.disableProperty().bindBidirectional(this.viewModel.createAccountDisabledProperty());
        this.usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.viewModel.respondToCredentialInput();
        });
        this.passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.viewModel.respondToCredentialInput();
        });
    }

    @FXML
    void handleLoginReturn(ActionEvent event) {
        Main.getMainStage().setTitle(Main.LOGIN_TITLE);
        new SwitchScene(this.createAccountPane, Main.LOGIN_FXML);
    }

    @FXML
    void handleAccountCreation(ActionEvent event) {
        if (!this.viewModel.attemptCreateAccount()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username already in use. Choose a new username.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account successfully created. Redirecting to login.");
            alert.showAndWait();
            Main.getMainStage().setTitle(Main.LOGIN_TITLE);
            new SwitchScene(this.createAccountPane, Main.LOGIN_FXML);
        }
    }

}

