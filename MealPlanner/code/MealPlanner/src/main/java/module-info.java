module edu.westga.cs3212.mealplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.sql;

    opens edu.westga.cs3212.mealplanner.view to javafx.fxml;
    exports edu.westga.cs3212.mealplanner;
}