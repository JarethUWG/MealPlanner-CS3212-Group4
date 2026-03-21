package edu.westga.cs3212.mealplanner.viewmodel.planner;

import edu.westga.cs3212.mealplanner.viewmodel.PlannerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDisplayPreviousMonth {

    private PlannerViewModel viewModel;
    private LocalDate currentDate;

    @BeforeEach
    void setup() {
        this.viewModel = new PlannerViewModel();
        this.currentDate = LocalDate.now();
    }

    @Test
    void testWhenCalled() {
        LocalDate previousDate = this.currentDate.minusMonths(1);
        String month = previousDate.getMonth().toString();
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        this.viewModel.displayPreviousMonth();

        String expected = month + " " + previousDate.getYear();
        String actual = this.viewModel.CalendarHeaderProperty().get();

        assertEquals(expected, actual);
    }
}
