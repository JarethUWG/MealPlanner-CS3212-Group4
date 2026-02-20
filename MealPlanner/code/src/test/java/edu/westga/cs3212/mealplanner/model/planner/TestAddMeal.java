package edu.westga.cs3212.mealplanner.model.planner;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.mealplanner.model.Planner;

class TestAddMeal {

	private Planner planner;

	@BeforeEach
	void setUp() throws Exception {
		this.planner = new Planner();
	}

	@Test
	void throwsWhenNullDate() {
		assertThrows(IllegalArgumentException.class, () -> {this.planner.addMeal(null, "Valid Meal");});
	}

	@Test
	void throwsWhenNullMeal() {
		assertThrows(IllegalArgumentException.class, () -> {this.planner.addMeal(LocalDate.now(), null);});
	}
	
	@Test
	void testWhenNoMealsPlannedOnDate() {
		var firstMeal = "First Meal!";
		this.planner.addMeal(LocalDate.now(), firstMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(firstMeal));
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testWhenOneMealAlreadyPlannedOnDate() {
		var firstMeal = "First Meal!";
		var secondMeal = "Second Meal!";
		this.planner.addMeal(LocalDate.now(), firstMeal);
		this.planner.addMeal(LocalDate.now(), secondMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(firstMeal, secondMeal));
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testWhenMultipleMealsAlreadyPlannedOnDate() {
		var firstMeal = "First Meal!";
		var secondMeal = "Second Meal!";
		var thirdMeal = "Third Meal!";
		this.planner.addMeal(LocalDate.now(), firstMeal);
		this.planner.addMeal(LocalDate.now(), secondMeal);
		this.planner.addMeal(LocalDate.now(), thirdMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(firstMeal, secondMeal, thirdMeal));
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}
}
