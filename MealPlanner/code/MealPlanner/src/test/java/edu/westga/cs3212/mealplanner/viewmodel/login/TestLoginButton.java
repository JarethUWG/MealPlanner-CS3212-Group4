package edu.westga.cs3212.mealplanner.viewmodel.login;

import edu.westga.cs3212.mealplanner.viewmodel.LoginViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestLoginButton {
    @Test
    void testPropertiesAreValid() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty loginDisabledProperty = new SimpleBooleanProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        loginDisabledProperty.bindBidirectional(testViewModel.loginDisabledProperty());
        usernameProperty.set("Test");
        passwordProperty.set("Test");

        assertFalse(testViewModel.loginDisabledProperty().get());
    }

    @Test
    void testPasswordAndUsernameIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty loginDisabledProperty = new SimpleBooleanProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        loginDisabledProperty.bindBidirectional(testViewModel.loginDisabledProperty());
        usernameProperty.set("");
        passwordProperty.set("");

        assertTrue(testViewModel.loginDisabledProperty().get());
    }

    @Test
    void testUsernameIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty loginDisabledProperty = new SimpleBooleanProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        loginDisabledProperty.bindBidirectional(testViewModel.loginDisabledProperty());
        usernameProperty.set("");
        passwordProperty.set("Test");

        assertTrue(testViewModel.loginDisabledProperty().get());
    }

    @Test
    void testPasswordIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty loginDisabledProperty = new SimpleBooleanProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        loginDisabledProperty.bindBidirectional(testViewModel.loginDisabledProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        assertTrue(testViewModel.loginDisabledProperty().get());
    }
}
