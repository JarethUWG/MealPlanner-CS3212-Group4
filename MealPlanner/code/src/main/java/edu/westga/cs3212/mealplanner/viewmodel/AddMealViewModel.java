package edu.westga.cs3212.mealplanner.viewmodel;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import edu.westga.cs3212.mealplanner.model.SystemInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddMealViewModel {
    private LocalDate currDate;
    private List<Ingredient> plannedIngredients = new ArrayList<>();

    public void setDate(LocalDate date) {
        this.currDate = date;
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

    public boolean addMeal(String name, String desc) {
        if (this.plannedIngredients.isEmpty()) {
            return false;
        } else {
            SystemInfo.getCurrentPlanner().addMeal(this.currDate, new Meal(this.plannedIngredients, name, desc));
            return true;
        }
    }

    public LocalDate getDate() {
        return this.currDate;
    }

    public List<Ingredient> getPlannedIngredients() {
        return this.plannedIngredients;
    }
}
