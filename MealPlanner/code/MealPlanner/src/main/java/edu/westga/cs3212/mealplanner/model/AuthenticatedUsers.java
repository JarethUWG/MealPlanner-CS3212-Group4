package edu.westga.cs3212.mealplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of authenticated users for the system.
 *
 * @author Jareth Batty
 * @version Spring 2026
 */
public class AuthenticatedUsers {
    private List<User> users;

    /**
     * Instantiates a list of authenticated users.
     */
    public AuthenticatedUsers() {
        this.users = new ArrayList<User>();
        User matt = new User("Username", "Password");
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
