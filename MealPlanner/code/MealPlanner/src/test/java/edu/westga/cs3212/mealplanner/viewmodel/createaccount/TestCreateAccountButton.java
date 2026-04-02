package edu.westga.cs3212.mealplanner.viewmodel.createaccount;

import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.viewmodel.CreateAccountViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCreateAccountButton {
    @BeforeEach
    void setUp() {
        SystemInfo.setLoggedInUser(null);
    }

    @Test
    void testPropertiesAreValid() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty createAccountDisabledProperty = new SimpleBooleanProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        createAccountDisabledProperty.bindBidirectional(testViewModel.createAccountDisabledProperty());
        usernameProperty.set("TestUser");
        passwordProperty.set("TestPass");

        assertFalse(testViewModel.createAccountDisabledProperty().get());
    }

    @Test
    void testPasswordTooShort() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty createAccountDisabledProperty = new SimpleBooleanProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        createAccountDisabledProperty.bindBidirectional(testViewModel.createAccountDisabledProperty());
        usernameProperty.set("TestUser");
        passwordProperty.set("Test");

        assertTrue(testViewModel.createAccountDisabledProperty().get());
    }

    @Test
    void testPasswordAndUsernameIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty createAccountDisabledProperty = new SimpleBooleanProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        createAccountDisabledProperty.bindBidirectional(testViewModel.createAccountDisabledProperty());
        usernameProperty.set("");
        passwordProperty.set("");

        assertTrue(testViewModel.createAccountDisabledProperty().get());
    }

    @Test
    void testUsernameIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty createAccountDisabledProperty = new SimpleBooleanProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        createAccountDisabledProperty.bindBidirectional(testViewModel.createAccountDisabledProperty());
        usernameProperty.set("");
        passwordProperty.set("TestPass");

        assertTrue(testViewModel.createAccountDisabledProperty().get());
    }

    @Test
    void testPasswordIsBlank() {
        StringProperty usernameProperty = new SimpleStringProperty();
        StringProperty passwordProperty = new SimpleStringProperty();
        BooleanProperty createAccountDisabledProperty = new SimpleBooleanProperty();

        CreateAccountViewModel testViewModel = new CreateAccountViewModel();
        usernameProperty.bindBidirectional(testViewModel.usernameProperty());
        passwordProperty.bindBidirectional(testViewModel.passwordProperty());
        createAccountDisabledProperty.bindBidirectional(testViewModel.createAccountDisabledProperty());
        usernameProperty.set("TestUser");
        passwordProperty.set("");

        assertTrue(testViewModel.createAccountDisabledProperty().get());
    }
}
