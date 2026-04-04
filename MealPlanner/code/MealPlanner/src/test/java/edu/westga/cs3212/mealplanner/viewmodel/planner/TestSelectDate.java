package edu.westga.cs3212.mealplanner.viewmodel.planner;

import edu.westga.cs3212.mealplanner.model.SystemInfo;
import edu.westga.cs3212.mealplanner.model.User;
import edu.westga.cs3212.mealplanner.viewmodel.PlannerViewModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestSelectDate {

    private PlannerViewModel viewModel;

    @BeforeEach
    void setup() {
        SystemInfo.setLoggedInUser(new User("username", "password"));
        this.viewModel = new PlannerViewModel();
    }

    @AfterEach
    void teardown() {
        SystemInfo.setLoggedInUser(null);
    }

    @Test
    void testWhenDateIsInvalid() {
        this.viewModel.selectDate(-1, -1);

        assertNull(SystemInfo.getLoggedInUser().getUserPlanner().getSelectedDate());
    }

    @Test
    void testWhenDateIsValid() {
        this.viewModel.selectDate(3, 2);

        assertNotNull(SystemInfo.getLoggedInUser().getUserPlanner().getSelectedDate());
    }
}
