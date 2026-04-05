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
    private static int loggedInUserId;
    private static Planner currentPlanner;

    /**
     * Gets the currently logged in user's id.
     *
     * @return The currently logged in user's id.
     */
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    /**
     * Sets the currently logged in user's id.
     *
     * @param loggedInUserId The currently logged in user's id.
     */
    public static void setLoggedInUserId(int loggedInUserId) {
        SystemInfo.loggedInUserId = loggedInUserId;
    }
}
