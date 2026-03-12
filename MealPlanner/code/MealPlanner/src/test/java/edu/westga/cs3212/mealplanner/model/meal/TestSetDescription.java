package edu.westga.cs3212.mealplanner.model.meal;

import edu.westga.cs3212.mealplanner.model.Ingredient;
import edu.westga.cs3212.mealplanner.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetDescription {
    //sets default name on null description
    //sets default name on blank description
    //normal use

    @Test
    void testDefaultDescOnChangingToNullDesc() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "", "normal description");
        test.setDescription(null);
        String desc = "No further description";
        assertEquals(desc, test.getDescription());
    }

    @Test
    void testDefaultDescOnChangingToWhitespaceDesc() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "", "also rather normal description");
        test.setDescription("");
        String desc = "No further description";
        assertEquals(desc, test.getDescription());
    }

    @Test
    void testChangeToValidDescription() {
        List<Ingredient> placeholderList = new ArrayList<>();
        Meal test = new Meal(placeholderList, "", "just a description");
        test.setDescription("far more than just a description");
        String desc = "far more than just a description";
        assertEquals(desc, test.getDescription());
    }
}
