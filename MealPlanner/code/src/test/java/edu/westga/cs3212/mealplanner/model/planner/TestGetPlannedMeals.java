package edu.westga.cs3212.mealplanner.model.planner;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.mealplanner.model.Planner;

class TestGetPlannedMeals {
	
	private Planner planner;

	@BeforeEach
	void setUp() throws Exception {
		this.planner = new Planner();
	}

	@Test
	void testParameterlessWhenNoPlannedMeals() {
		var expected = new ArrayList<String>();
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testParameterlessWhenOnePlannedMeal() {
		var onlyMeal = "Only meal";
		this.planner.addMeal(LocalDate.now(), onlyMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(onlyMeal));
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testParameterlessWhenMultiplePlannedMeals() {
		var firstMeal = "First meal";
		var middleMeal = "Middle meal";
		var lastMeal = "Last meal";
		this.planner.addMeal(LocalDate.now(), firstMeal);
		this.planner.addMeal(LocalDate.now(), middleMeal);
		this.planner.addMeal(LocalDate.now(), lastMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(firstMeal, middleMeal, lastMeal));
		var actual = this.planner.getPlannedMeals();
		
		assertEquals(expected, actual);
	}

	@Test
	void throwsWhenFromDateIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {this.planner.getPlannedMeals(null, LocalDate.now());});
	}
	
	@Test
	void throwsWhenToDateIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {this.planner.getPlannedMeals(LocalDate.now(), null);});
	}
	
	@Test
	void testWhenNoPlannedMealsInDateRange() {
		var plannedMeal = "Only planned meal";
		var plannedDate = LocalDate.now();
		this.planner.addMeal(plannedDate, plannedMeal);
		
		var expected = new ArrayList<String>();
		var actual = this.planner.getPlannedMeals(plannedDate.plusDays(1), plannedDate.plusDays(2));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testWhenOnePlannedMealInDateRange() {
		var firstDate = LocalDate.now();
		var firstMeal = "first planned meal";
		var secondDate = firstDate.plusDays(5);
		var secondMeal = "second planned meal";
		this.planner.addMeal(firstDate, firstMeal);
		this.planner.addMeal(secondDate, secondMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(secondMeal));
		var actual = this.planner.getPlannedMeals(secondDate.minusDays(1), secondDate.plusDays(1));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testWhenMultiplePlannedMealsInDateRange() {
		var firstDate = LocalDate.now();
		var firstMeal = "first planned meal";
		var secondDate = firstDate.plusDays(1);
		var secondMeal = "second planned meal";
		var thirdDate = firstDate.plusDays(2);
		var thirdMeal = "third planned meal";
		var fourthDate = firstDate.plusDays(3);
		var fourthMeal = "fourth planned meal";
		var fifthDate = firstDate.plusDays(4);
		var fifthMeal = "fifth planned meal";
		this.planner.addMeal(firstDate, firstMeal);
		this.planner.addMeal(secondDate, secondMeal);
		this.planner.addMeal(thirdDate, thirdMeal);
		this.planner.addMeal(fourthDate, fourthMeal);
		this.planner.addMeal(fifthDate, fifthMeal);
		
		var expected = new ArrayList<String>(Arrays.asList(secondMeal, thirdMeal, fourthMeal));
		var actual = this.planner.getPlannedMeals(firstDate.plusDays(1), fifthDate.minusDays(1));
		
		assertEquals(expected, actual);
	}

}
