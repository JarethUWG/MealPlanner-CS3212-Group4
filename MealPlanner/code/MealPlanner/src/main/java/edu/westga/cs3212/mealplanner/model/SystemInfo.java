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
    private static Planner currentPlanner;
    private static int id;

    /**
     * Gets the currently logged in user.
     *
     * @return The currently logged in user
     */
    public static User getLoggedInUser() {
        return loggedInUser;
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
     * Gets the current session id.
     *
     * @return The id associated with the current user session.
     */
    public static int getId() {
        return id;
    }

    /**
     * Sets the current session id.
     *
     * @param id id to associate the current session with.
     */
    public static void setId(int id) {
        SystemInfo.id = id;
    }
}
