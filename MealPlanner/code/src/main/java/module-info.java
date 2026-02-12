module edu.westga.cs3211.piratestorage {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;

    opens edu.westga.cs3211.mealplanner.view to javafx.fxml;
    exports edu.westga.cs3211.mealplanner;
}
