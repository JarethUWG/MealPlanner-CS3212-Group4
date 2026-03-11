package edu.westga.cs3212.mealplanner.model.planner;

import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TestConstructor {

    @Test
    void testParameterlessConstructor() {
        var newPlanner = new Planner();

        var expected = new ArrayList<Meal>();
        var actual = newPlanner.getPlannedMeals();

        assertEquals(expected, actual);
    }
}
