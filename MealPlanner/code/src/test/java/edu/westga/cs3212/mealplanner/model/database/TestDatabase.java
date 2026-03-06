package edu.westga.cs3212.mealplanner.model.database;

import edu.westga.cs3212.mealplanner.model.Database;
import edu.westga.cs3212.mealplanner.model.Ingredient;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDatabase {

    @Test
    void testDataIsGeneratedCorrectly() {
        List<Ingredient> test = new ArrayList<Ingredient>();
        test.add(new Ingredient("Milk", 40));
        test.add(new Ingredient("Water", 0));
        test.add(new Ingredient("Cheese", 50));
        test.add(new Ingredient("Beef", 250));
        test.add(new Ingredient("Pork", 240));
        test.add(new Ingredient("Bread", 100));
        test.add(new Ingredient("Tomato", 60));
        test.add(new Ingredient("Kale", 10));
        test.add(new Ingredient("Broccoli", 25));
        test.add(new Ingredient("Potato", 120));
        test.sort(Comparator.comparing(Ingredient::getName));
        assertEquals(test, Database.getDatabase());
    }
}
