package edu.westga.cs3212.mealplanner.viewmodel.login;

import edu.westga.cs3212.mealplanner.viewmodel.LoginViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAttemptLogin {

    @Test
    void testAttemptLoginNoUsername() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("");
        passwordProperty.set("Test");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptLogin);
    }

    @Test
    void testAttemptLoginNoPassword() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptLogin);
    }

    @Test
    void testAttemptLoginDoesntMatch() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test Name");
        passwordProperty.set("Test Password");

        assertFalse(testViewModel.attemptLogin());
    }

    @Test
    void testAttemptLoginMatches() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Username");
        passwordProperty.set("Password");

        assertTrue(testViewModel.attemptLogin());
    }
}
