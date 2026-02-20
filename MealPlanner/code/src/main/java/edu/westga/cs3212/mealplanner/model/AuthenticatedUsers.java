package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads and save out a list of users.
 * NOTE: save and load functionality will be implemented
 * with create account functionality for now the class provides a base user for
 * to utilize login functionality.
 *
 * @author Jareth Batty
 * @version Fall 2025
 */
public class AuthenticatedUsers {
    private List<User> users;

    /**
     * Creates a list of authenticated users.
     */
    public AuthenticatedUsers() {
        this.users = new ArrayList<User>();
        User matt = new User("User", "Username", "Password");
        this.users.add(matt);
    }

    /**
     * Gets the current value of users.
     *
     * @return The current value of users.
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Adds a user to the list of users.
     *
     * @precondition user != null && !this.users.contains(user)
     * @postcondition this.users.contains(user)
     * @param user the user to be added.
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Added user cannot be null.");
        }
        if (this.users.contains(user)) {
            throw new IllegalArgumentException("Added user already exists in list.");
        }
        this.users.add(user);
    }
}
