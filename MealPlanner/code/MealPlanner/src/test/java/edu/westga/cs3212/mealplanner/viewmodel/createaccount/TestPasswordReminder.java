package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPasswordReminder {
    @BeforeAll
    static void setUp() {
        SystemInfo.setLoggedInUser(null);
    }

    @Test
    void testCredentialsAreBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
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

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
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

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("Must Password must be at least 5 characters", actual);
    }

    @Test
    void testPasswordTooShort() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("Test");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("Must Password must be at least 5 characters", actual);
    }

    @Test
    void testValidCredentials() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("TestUser");
        passwordProperty.set("TestPass");

        String actual = testViewModel.passwordReminderProperty().get();

        assertEquals("", actual);
    }
}
