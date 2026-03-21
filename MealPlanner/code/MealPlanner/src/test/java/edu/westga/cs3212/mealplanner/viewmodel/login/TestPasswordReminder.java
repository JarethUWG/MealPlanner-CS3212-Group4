package edu.westga.cs3212.mealplanner.viewmodel.login;

import edu.westga.cs3212.mealplanner.viewmodel.LoginViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPasswordReminder {
    @Test
    void testCredentialsAreBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("");
        passwordProperty.set("");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("", actual);
    }

    @Test
    void testUsernameIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("");
        passwordProperty.set("Test");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("", actual);
    }

    @Test
    void testPasswordIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("Must input password", actual);
    }

    @Test
    void testNoneAreBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        LoginViewModel testViewModel = new LoginViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("Test");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("", actual);
    }
}
