package edu.westga.cs3212.mealplanner.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines a planner object.
 * 
 * This class heavily relies on the not-yet implemented
 * class Meal so until it is implemented any and all instances
 * where its typing would be used are replaced with String.
 *
 * @author Kirya Duncan II
 * @version Spring 2025
 */
public class Planner {
	
	private HashMap<Long, ArrayList<String>> plannedMeals;
	
	/**
	 * Initializes a new Planner
	 */
	public Planner() {
		this.plannedMeals = new HashMap<>();
	}
	
	/**
	 * @precondition date != null && newMeal != null
	 * @postcondition getPlannedMeals() return will include newMeal if date is in the given range
	 * @param date
	 * @param newMeal
	 */
	public void addMeal(LocalDate date, String newMeal) {
		if (date == null) {
			throw new IllegalArgumentException("Date can't be null");
		}
		if (newMeal == null) {
			throw new IllegalArgumentException("Meal can't be null");
		}
		
		var epochDay = date.toEpochDay();
		
		if (this.plannedMeals.containsKey(epochDay)) {
			var alreadyPlannedMeals = this.plannedMeals.get(epochDay);
			alreadyPlannedMeals.add(newMeal);
		} else {
			var newPlannedMeals = new ArrayList<String>(Arrays.asList(newMeal));
			this.plannedMeals.put(epochDay, newPlannedMeals);
		}
	}
	
	/**
	 * Returns an iterable over all the currently planned meals.
	 * @return An iterable over all planned meals
	 */
	public Iterable<String> getPlannedMeals() {
		var allPlannedMeals = new ArrayList<String>();
		
		for (var plannedMeals : this.plannedMeals.values()) {
			allPlannedMeals.addAll(plannedMeals);
		}
		
		return allPlannedMeals;
	}
	
	/**
	 * Returns an iterable over all the currently planned meals
	 * in the (inclusive) date range from-to.
	 * 
	 * @precondition from != null && to != null
     *
	 * @param from The start of the date range
	 * @param to The end of the date range
	 * @return An iterable over the meals planned between from and to
	 */
	public Iterable<String> getPlannedMeals(LocalDate from, LocalDate to) {
		if (from == null) {
			throw new IllegalArgumentException("Date can't be null");
		}
		if (to == null) {
			throw new IllegalArgumentException("Meal can't be null");
		}
		
		var mealsInPlannedRange = new ArrayList<String>();
		
		for (Long dateEpoch = from.toEpochDay(); dateEpoch <= to.toEpochDay(); dateEpoch++) {
			if (this.plannedMeals.containsKey(dateEpoch)) {
				var plannedMeals = this.plannedMeals.get(dateEpoch);
				mealsInPlannedRange.addAll(plannedMeals);
			}
		}
		
		return mealsInPlannedRange;
	}
}
