package edu.westga.cs3212.mealplanner.viewmodel.planner;

import edu.westga.cs3212.mealplanner.viewmodel.PlannerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestGetDayThisMonth {

    private PlannerViewModel viewModel;

    @BeforeEach
    void setup() {
        this.viewModel = new PlannerViewModel();
    }

    @Test
    void testWhenDayIsInMonth() {
        int unexpected = -1;
        int actual = this.viewModel.getDayThisMonth(3, 2);

        assertNotEquals(unexpected, actual);
    }

    @Test
    void testWhenDayIsBeforeMonth() {
        int expected = -1;
        int actual = this.viewModel.getDayThisMonth(-30, 2);

        assertEquals(expected, actual);
    }

    @Test
    void testWhenDayIsAfterMonth() {
        int expected = -1;
        int actual = this.viewModel.getDayThisMonth(20, 20);

        assertEquals(expected, actual);
    }
}
