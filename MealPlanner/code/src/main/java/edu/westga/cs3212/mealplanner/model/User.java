package edu.westga.cs3212.mealplanner.model;

/**
 * Defines a user.
 *
 * @author Jareth Batty
 * @version Spring 2025
 */
public class User {
    private String name;
    private String username;
    private String password;
    // private Planner userPlanner; should go here when implemented.

    /**
     * Initializes a new user.
     *
     * @precondition name != null && !name.isEmpty,
     *        		 username != null && !username.isEmpty,
     *        		 password != null && !password.isEmpty,
     *
     * @postcondition this.name == name
     *                this.username == username
     *                this.password == password
     *
     * @param name the name of the user.
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public User(String name, String username, String password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Please input valid name");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Please input valid username");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Please input valid password");
        }
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the current value of name.
     *
     * @return The current value of name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the current value of username.
     *
     * @return The current value of username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the current value of password.
     *
     * @return The current value of password.
     */
    public String getPassword() {
        return this.password;
    }
}