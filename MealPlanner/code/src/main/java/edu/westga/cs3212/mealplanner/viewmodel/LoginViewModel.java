package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.Main;
import edu.westga.cs3212.mealplanner.model.AuthenticatedUsers;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * The Login ViewModel.
 *
 * @author Jareth Batty
 * @version Spring 2025
 */
public class LoginViewModel {
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty usernameReminderProperty;
    private StringProperty passwordReminderProperty;
    private BooleanProperty loginDisabledProperty;
    private AuthenticatedUsers authenticatedUsers;

    /**
     * Instantiates a new login view model.
     */
    public LoginViewModel() {
        this.passwordProperty = new SimpleStringProperty("");
        this.usernameProperty = new SimpleStringProperty("");
        this.usernameReminderProperty = new SimpleStringProperty("");
        this.passwordReminderProperty = new SimpleStringProperty("");
        this.loginDisabledProperty = new SimpleBooleanProperty(true);
        this.setCredentialChangeListeners();
        if (SystemInfo.getAuthenticatedUsers() == null) {
            SystemInfo.setAuthenticatedUsers(new AuthenticatedUsers());
        }
    }

    /**
     * Gets the username property.
     *
     * @return the usernameProperty
     */
    public StringProperty usernameProperty() {
        return this.usernameProperty;
    }

    /**
     * Gets the username reminder property.
     *
     * @return the usernameReminderProperty
     */
    public StringProperty usernameReminderProperty() {
        return this.usernameReminderProperty;
    }

    /**
     * Gets the password property.
     *
     * @return the passwordProperty
     */
    public StringProperty passwordProperty() {
        return this.passwordProperty;
    }

    /**
     * Gets the password reminder property.
     *
     * @return the passwordReminderProperty
     */
    public StringProperty passwordReminderProperty() {
        return this.passwordReminderProperty;
    }

    /**
     * Gets the login disabled property.
     *
     * @return the loginDisabledProperty
     */
    public BooleanProperty loginDisabledProperty() {
        return this.loginDisabledProperty;
    }

    /**
     * Attempt to login the user
     *
     * @precondition: username != null && !username.isBlank()
     * 				  password != null && !password.isBlank()
     * @postcondition: user will be checked against AuthenticatedUsers
     * 				   if the username and password match one of the AthenticatedUsers
     * 				   then loggedInUser will be set to that AthenticatedUser
     * 			 	   else nothing will happen.
     * @return true if login was successful else false
     */
    public boolean attemptLogin() {
        if (this.usernameProperty == null || this.usernameProperty.get() == null || this.usernameProperty.get().isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (this.passwordProperty == null || this.passwordProperty.get() == null || this.passwordProperty.get().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        for (User user : SystemInfo.getAuthenticatedUsers().getUsers()) {
            if (user.getUsername().equals(this.usernameProperty.get()) && user.getPassword().equals(this.passwordProperty.get())) {
                SystemInfo.setLoggedInUser(user);
                return true;
            }
        }
        this.loginDisabledProperty.set(true);
        this.usernameProperty.set("");
        this.passwordProperty.set("");
        return false;
    }

    private void setCredentialChangeListeners() {
        this.usernameProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue.isBlank() && !newValue.isBlank()) {
                    LoginViewModel.this.respondToCredentialInput();
                }
            }
        });
        this.passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue.isBlank() && !newValue.isBlank()) {
                    LoginViewModel.this.respondToCredentialInput();
                }
            }
        });
    }

    /**
     * Shows reminder text for credentials if they aren't filled
     * and turns on the login button if both credentials are filled.
     *
     * @precondition !this.passwordProperty.get().isBlank()
     *               !this.usernameProperty.get().isBlank()
     * @postcondition this.loginDisabledProperty.get == false;
     */
    public void respondToCredentialInput() {
        if (this.usernameProperty.get().isBlank() && !this.passwordProperty.get().isBlank()) {
            this.usernameReminderProperty.set("Must input username");
        } else {
            this.usernameReminderProperty.set("");
        }
        if (this.passwordProperty.get().isBlank() && !this.usernameProperty.get().isBlank()) {
            this.passwordReminderProperty.set("Must input password");
        } else {
            this.passwordReminderProperty.set("");
        }
        if (!this.usernameProperty.get().isBlank() && !this.passwordProperty.get().isBlank()) {
            this.loginDisabledProperty.set(false);
        } else {
            this.loginDisabledProperty.set(true);
        }
    }

}
