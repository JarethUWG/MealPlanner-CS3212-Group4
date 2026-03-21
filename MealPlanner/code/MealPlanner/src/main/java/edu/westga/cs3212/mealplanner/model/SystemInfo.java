package edu.westga.cs3212.mealplanner.model;

/**
 * Holds information that needs to be accessed across the system
 * Note: this class will likely be replaced by the server in future
 * implementations.
 *
 * @author Jareth Batty
 * @version Spring 2026
 */
public class SystemInfo {
    private static User loggedInUser;
    private static AuthenticatedUsers authenticatedUsers;
    private static Planner currentPlanner;

    /**
     * Gets the currently logged in user.
     *
     * @return The currently logged in user
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Gets the authenticated users.
     *
     * @return The authenticated users
     */
    public static AuthenticatedUsers getAuthenticatedUsers() {
        return authenticatedUsers;
    }

    /**
     * Sets the currently logged in user.
     *
     * @param loggedInUser The currently logged in user
     */
    public static void setLoggedInUser(User loggedInUser) {
        SystemInfo.loggedInUser = loggedInUser;
    }

    /**
     * Sets the authenticated users.
     *
     * @param users The authenticated users
     */
    public static void setAuthenticatedUsers(AuthenticatedUsers users) {
        SystemInfo.authenticatedUsers = users;
    }
}
