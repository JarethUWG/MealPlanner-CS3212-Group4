package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestUsernameReminder {
    @BeforeAll
    static void setUp() {
        SystemInfo.setLoggedInUserId(null);
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

        String actual = testViewModel.usernameReminderProperty().get();

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

        String actual = testViewModel.usernameReminderProperty().get();

        assertEquals("Must input username", actual);
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

        String actual = testViewModel.usernameReminderProperty().get();

        assertEquals("", actual);
    }

    @Test
    void testNoneAreBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("Test");

        String actual = testViewModel.usernameReminderProperty().get();

        assertEquals("", actual);
    }
}

