package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Planner;

import java.time.LocalDate;

public class AddMealViewModel {
    private LocalDate currDate;
    private Planner currPlanner;

    public void setDateAndPlanner(LocalDate date, Planner planner) {
        this.currDate = date;
        this.currPlanner = planner;
    }
}
