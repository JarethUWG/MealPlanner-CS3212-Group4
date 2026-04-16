package edu.westga.cs3212.mealplanner.viewmodel;
import edu.westga.cs3212.mealplanner.model.Messenger;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;
import java.util.Map;

/**
 * The CreateAccount ViewModel.
 *
 * @author Jareth Batty
 * @version Spring 2026
 */
public class CreateAccountViewModel {
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty usernameReminderProperty;
    private StringProperty passwordReminderProperty;
    private BooleanProperty createAccountDisabledProperty;

    /**
     * Instantiates a new login view model.
     */
    public CreateAccountViewModel() {
        this.passwordProperty = new SimpleStringProperty("");
        this.usernameProperty = new SimpleStringProperty("");
        this.usernameReminderProperty = new SimpleStringProperty("");
        this.passwordReminderProperty = new SimpleStringProperty("");
        this.createAccountDisabledProperty = new SimpleBooleanProperty(true);
        this.setCredentialChangeListeners();
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
     * Gets the create account disabled property.
     *
     * @return the createAccountDisabledProperty
     */
    public BooleanProperty createAccountDisabledProperty() {
        return this.createAccountDisabledProperty;
    }

    /**
     * Attempt to create a new user account
     *
     * @precondition: username != null && !username.isBlank()
     * 				  password != null && !password.isBlank()
     * 				  The username is not present in Username
     * @postcondition: username will be checked against AuthenticatedUsers
     * 				   if the username matches one of the AthenticatedUsers
     * 				   then the account will not be added.
     * 			 	   else the account will be added.
     * @return true if creation was successful else false
     */
    public boolean attemptCreateAccount() {
        if (this.usernameProperty == null || this.usernameProperty.get() == null || this.usernameProperty.get().isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (this.passwordProperty == null || this.passwordProperty.get() == null || this.passwordProperty.get().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        HashMap<String, Object> request = new HashMap<>();
        request.put("username", this.usernameProperty.get());
        request.put("password", this.passwordProperty.get());
        request.put("reqtype", "CREATE ACCOUNT");
        Map<String, Object> response = Messenger.request(request);
        return response.get("restype").equals("VALID");
    }

    private void setCredentialChangeListeners() {
        this.usernameProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                CreateAccountViewModel.this.respondToCredentialInput();
            }
        });
        this.passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                CreateAccountViewModel.this.respondToCredentialInput();
            }
        });
    }

    /**
     * Shows reminder text for credentials if they aren't filled or if password is too short
     * and turns on the create account button if both credentials are filled and valid.
     *
     * @precondition !this.usernameProperty.get().isBlank()
     *               !this.passwordProperty.get().isBlank()
     *               !this.passwordProperty.get().length() < 5
     * @postcondition this.createAccountDisabledProperty.get == true
     */
    public void respondToCredentialInput() {
        if (this.usernameProperty.get().isBlank() && !(this.passwordProperty.get().isBlank() && this.passwordProperty.get().length() < 5)) {
            this.usernameReminderProperty.set("Must input username");
        } else {
            this.usernameReminderProperty.set("");
        }
        if ((this.passwordProperty.get().isBlank() || this.passwordProperty.get().length() < 5) && !this.usernameProperty.get().isBlank()) {
            this.passwordReminderProperty.set("Must Password must be at least 5 characters");
        } else {
            this.passwordReminderProperty.set("");
        }
        if (!this.usernameProperty.get().isBlank() && !(this.passwordProperty.get().isBlank() || this.passwordProperty.get().length() < 5)) {
            this.createAccountDisabledProperty.set(false);
        } else {
            this.createAccountDisabledProperty.set(true);
        }
    }

}
