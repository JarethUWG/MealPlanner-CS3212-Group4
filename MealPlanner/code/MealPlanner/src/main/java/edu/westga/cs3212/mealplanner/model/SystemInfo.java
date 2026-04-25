package edu.westga.cs3212.mealplanner.model;

import java.time.LocalDateTime;

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
    private static LocalDateTime selectedCalendarDate;

    /**
     * Gets the current session id.
     *
     * @return The id associated with the current user session.
     */
    public static LocalDateTime getSelectedCalendarDate() {
        return SystemInfo.selectedCalendarDate;
    }

    /**
     * Sets the selected calendar date for the session.
     * @param calendarDate The new calendar date
     */
    public static void setSelectedCalendarDate(LocalDateTime calendarDate) {
        if (calendarDate == null) {
            throw new IllegalArgumentException("calendarDate can't be null");
        }
        SystemInfo.selectedCalendarDate = calendarDate;
    }

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
