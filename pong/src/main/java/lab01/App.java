package lab01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private Canvas canvas;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			canvas = new Canvas(1200, 700);
			root.getChildren().add(canvas);
			Scene scene = new Scene(root, 1200, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Pong");
			primaryStage.show();
			primaryStage.setOnCloseRequest(this::exitProgram);
			new Thread(this::drawScene).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawScene() {
		new Scenery().draw(canvas);
	}
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}