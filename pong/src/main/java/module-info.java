module lab01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
	requires transitive java.desktop;
    requires java.sql;
    requires javafx.base;
    requires java.base;
    requires javafx.graphics;
    opens lab01 to javafx.fxml;
    exports lab01;
}