package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Planner;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddMealViewModel {
    private LocalDate currDate;
    private Planner currPlanner;
    private List<Ingredient> plannedIngredients = new ArrayList<>();

    public void setDateAndPlanner(LocalDate date, Planner planner) {
        this.currDate = date;
        this.currPlanner = planner;
    }

    public boolean addIngredient(Ingredient toAdd) {
        if (toAdd != null) {
            this.plannedIngredients.add(toAdd);
            return true;
        } else {
            return false;
        }
    }

    public void resetIngredients() {
        this.plannedIngredients.clear();
    }

    public LocalDate getDate() {
        return this.currDate;
    }

    public List<Ingredient> getPlannedIngredients() {
        return this.plannedIngredients;
    }
}
