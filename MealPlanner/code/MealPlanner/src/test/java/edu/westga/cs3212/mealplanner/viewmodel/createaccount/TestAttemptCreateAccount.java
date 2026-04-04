package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAttemptCreateAccount {
    @BeforeEach
    void setUp() {
        SystemInfo.setLoggedInUser(null);
    }

    @Test
    void testNoUsername() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("");
        passwordProperty.set("Test");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptCreateAccount);
    }

    @Test
    void testNoPassword() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test");
        passwordProperty.set("");

        assertThrows(IllegalArgumentException.class, testViewModel::attemptCreateAccount);
    }

    @Test
    void testCredentialsDontMatch() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Test Name");
        passwordProperty.set("Test Password");

        assertTrue(testViewModel.attemptCreateAccount());
    }

    @Test
    void testCredentialsMatch() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        usernameProperty.set("Username");
        passwordProperty.set("Password");

        assertFalse(testViewModel.attemptCreateAccount());
    }
}
