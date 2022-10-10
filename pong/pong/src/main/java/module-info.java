module lab01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
	requires transitive java.desktop;
    opens lab01 to javafx.fxml;
    exports lab01;
}