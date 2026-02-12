package edu.westga.cs3211.mealplanner.model;

public class Ingredient {
    private String name;
    private int calories = 0;
    private String desc;

    public Ingredient (String name, int calories, String desc) {
        this.name = name;
        this.calories = calories;
        this.desc = desc;
        if (name == null || name.isEmpty()) {
            this.name = "Unnamed Ingredient";
        }
        if (calories < 0) {
            this.calories = 0;
        }
        if (desc == null || desc.isEmpty()) {
            this.desc = "";
        }
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setName(String newName) {
        if (newName == null || newName.isEmpty()) {
            this.name = "Unnamed Ingredient";
            return;
        }
        this.name = newName;
    }

    public void setCalories(int newCalories) {
        if (newCalories < 0) {
            this.calories = 0;
            return;
        }
        this.calories = newCalories;
    }

    public void setDesc(String newDesc) {
        if (newDesc == null || newDesc.isEmpty()) {
            this.desc = "";
            return;
        }
        this.desc = newDesc;
    }

}
