package edu.westga.cs3212.mealplanner.model;

/**
 * Defines a user.
 *
 * @author Jareth Batty
 * @version Spring 2026
 */
public class User {
    private String username;
    private String password;
    private Planner userPlanner;
    // private List<Meal> customMeals; should go here when implemented

    /**
     * Initializes a new user.
     *
     * @precondition username != null && !username.isEmpty,
     *               password != null && !password.isEmpty,
     *
     * @postcondition this.username == username
     *                this.password == password
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public User(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Please input valid username");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Please input valid password");
        }
        this.userPlanner = new Planner();
        this.username = username;
        this.password = password;
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

    /**
     * Gets the current user's planner.
     *
     * @return The current planner
     */
    public Planner getUserPlanner() {
        return this.userPlanner;
    }
}