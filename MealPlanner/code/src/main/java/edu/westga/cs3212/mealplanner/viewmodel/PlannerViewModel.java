package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Planner;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

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
    private Planner planner;

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
        this.planner = new Planner();
        this.updateHeader();
    }

    /**
     * Displays the previous month.
     *
     * @postcondition this.CalendarHeaderProperty.get() == previous "Month Year"
     */
    public void DisplayPreviousMonth() {
        this.displayedMonth = this.displayedMonth.minusMonths(1).withDayOfMonth(1);
        this.updateHeader();
    }

    /**
     * Displays the next month.
     *
     * @postcondition this.CalendarHeaderProperty.get() == next "Month Year"
     */
    public void DisplayNextMonth() {
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
     * Returns the current month/year being displayed.
     *
     * @return The local date of the current month.
     */
    public LocalDate getMonth() {
        return this.displayedMonth;
    }

    /**
     * Returns the current map of planned meals.
     *
     * @return Planner associated with this view
     */
    public Planner getPlanner() {
        return this.planner;
    }

    /**
     * Returns the day of the month associated with column and row or -1 if a day is not associated.
     *
     * @param column The column position
     * @param row The row position
     * @return The day of the month or -1 if day is not in month
     */
    public int GetDayThisMonth(int column, int row) {
        int day = this.gridPositionToDay(column, row);
        boolean dayIsInMonth = day > 0 && day <= this.displayedMonth.lengthOfMonth();

        return dayIsInMonth ? day : -1;
    }

    private int gridPositionToDay(int column, int row) {
        int columnOffset = this.displayedMonth.getDayOfWeek().getValue();
        columnOffset = columnOffset == DAYS_IN_WEEK ? 0 : columnOffset;
        column++;

        return (row * DAYS_IN_WEEK) + column - columnOffset;
    }
}
