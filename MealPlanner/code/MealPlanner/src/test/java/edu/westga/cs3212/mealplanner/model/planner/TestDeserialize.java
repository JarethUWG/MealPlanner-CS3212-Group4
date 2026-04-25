package edu.westga.cs3212.mealplanner.model.planner;

import edu.westga.cs3212.mealplanner.model.Meal;
import edu.westga.cs3212.mealplanner.model.Planner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDeserialize {

    @Test
    void throwsWhenInformationIsMissing() {
        var plannerInfo = new HashMap<String, Object>();
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", "Valid Name");
        mealInfo.put("description", "Valid Description");
        var onlyDate = "2";
        plannerInfo.put(onlyDate, mealInfo);

        assertThrows(IllegalArgumentException.class, () -> {
            Planner.deserialize(plannerInfo);
        });
    }

    @Test
    void testWhenNoEntries() {
        var plannerInfo = new HashMap<String, Object>();

        var newPlanner = Planner.deserialize(plannerInfo);

        var expectedPlannedDates = new ArrayList<Meal>();
        var actualPlannedDates = newPlanner.getPlannedMeals();

        assertEquals(expectedPlannedDates, actualPlannedDates);
    }

    @Test
    void testWhenOneEntry() {
        var plannerInfo = new HashMap<String, Object>();
        var mealInfo = new HashMap<String, Object>();
        mealInfo.put("name", "Valid Name");
        mealInfo.put("ingredients", new ArrayList<Object>());
        mealInfo.put("description", "Valid Description");
        var onlyDate = "2";
        plannerInfo.put(onlyDate, mealInfo);

        var newPlanner = Planner.deserialize(plannerInfo);

        var expectedPlannedDatesCount = 1;
        var actualPlannedDatesCount = ((ArrayList<Meal>) newPlanner.getPlannedMeals()).size();

        assertEquals(expectedPlannedDatesCount, actualPlannedDatesCount);
    }
}
