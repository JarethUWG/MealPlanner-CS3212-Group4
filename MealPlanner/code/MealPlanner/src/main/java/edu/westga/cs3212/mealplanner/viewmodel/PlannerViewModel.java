package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Messenger;
import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.model.SystemInfo;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Planner viewmodel.
 *
 * @author Kirya
 * @version Spring 2025
 */
public class PlannerViewModel {

    private static int DAYS_IN_WEEK = 7;
    private LocalDate displayedMonth;
    private SimpleStringProperty calendarHeaderProperty;

    /**
     * Calendar header property.
     * @return The calendar header property
     */
    public SimpleStringProperty CalendarHeaderProperty() {
        return this.calendarHeaderProperty;
    }

    /**
     * Instantiates a new planner viewmodel.
     */
    public PlannerViewModel() {
        this.displayedMonth = LocalDate.now().withDayOfMonth(1);
        this.calendarHeaderProperty = new SimpleStringProperty();
        this.updateHeader();
    }

    /**
     * Displays the previous month.
     *
     * @postcondition this.CalendarHeaderProperty.get() == previous "Month Year"
     */
    public void displayPreviousMonth() {
        this.displayedMonth = this.displayedMonth.minusMonths(1).withDayOfMonth(1);
        this.updateHeader();
    }

    /**
     * Displays the next month.
     *
     * @postcondition this.CalendarHeaderProperty.get() == next "Month Year"
     */
    public void displayNextMonth() {
        this.displayedMonth = this.displayedMonth.plusMonths(1).withDayOfMonth(1);
        this.updateHeader();
    }

    private void updateHeader() {
        String month = this.displayedMonth.getMonth().toString();
        int year = this.displayedMonth.getYear();
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

        String newHeader = month + " " + year;

        this.calendarHeaderProperty.set(newHeader);
    }

    /**
     * Returns the day of the month associated with column and row or -1 if a day is not associated.
     *
     * @param column The column position
     * @param row The row position
     * @return The day of the month or -1 if day is not in month
     */
    public int getDayThisMonth(int column, int row) {
        int day = this.gridPositionToDay(column, row);
        boolean dayIsInMonth = day > 0 && day <= this.displayedMonth.lengthOfMonth();

        return dayIsInMonth ? day : -1;
    }

    /**
     * Updates the selected date of the planner.
     * @param column The column the date is in.
     * @param row The row the date is in.
     *
     * @postcondition if selected date is valid SystemInfo.getLogginUser().getUserPlanner() == selected date
     */
    public void selectDate(int column, int row) {
        int dayThisMonth = this.getDayThisMonth(column, row);
        if (dayThisMonth != -1) {
            LocalDate newSelectedDate = this.displayedMonth.withDayOfMonth(dayThisMonth);
            var userPlanner = this.getUserPlanner();

            userPlanner.setSelectedDate(newSelectedDate.atStartOfDay());
        }
    }

    private int gridPositionToDay(int column, int row) {
        int columnOffset = this.displayedMonth.getDayOfWeek().getValue();
        columnOffset = columnOffset == DAYS_IN_WEEK ? 0 : columnOffset;
        column++;

        return (row * DAYS_IN_WEEK) + column - columnOffset;
    }

    private Planner getUserPlanner() {
        var activeUserID = SystemInfo.getLoggedInUserId();
        var request = new HashMap<String, Object>();
        request.put("id", activeUserID);
        request.put("reqtype", "GET PLANNER");
        var response = Messenger.request(request);
        var plannerInfo = (Map<Long, Object>) response.get("planner");
        return Planner.deserialize(plannerInfo);
    }
}
