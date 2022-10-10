package lab01;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private Canvas canvas;
	private Scene menu;
	private Scene setPlayerNames;
	private Scene controls;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			canvas = new Canvas(1200, 700);
			root.getChildren().add(canvas);
			scene = new Scene(root, 1200, 700);
			
			Text text = new Text(10, 40, "PONG");
			Text controlsText1 = new Text(10, 40, "P = PAUSE / START");
			Text controlsText2 = new Text(10, 40, "Player 1: A = DOWN, D = UP");
			Text controlsText3 = new Text(10, 40, "Player 2: LEFT ARROW = DOWN, RIGHT ARROW = UP");
			Text controlsText4 = new Text(10, 40, "O = RETURN TO MAIN MENU");
	        text.setFont(new Font(40));
			Button play = new Button("Play");
			Button nameSet = new Button("Change player names / choose between SP and MP");
			Button viewControls = new Button("View controls");
			Button backToMenu = new Button ("Return to main menu");
			Button nameSet1 = new Button("Set the name for player 1");
			Button nameSet2 = new Button("Set the name for player 2");
			Button single = new Button("Toggle between 1 or 2 players");
			play.setOnAction(e -> primaryStage.setScene(scene));
			nameSet.setOnAction(e -> primaryStage.setScene(setPlayerNames)); 
			viewControls.setOnAction(e -> primaryStage.setScene(controls));
			backToMenu.setOnAction(e -> primaryStage.setScene(menu));
			//nameset1.setOnAction(e -> okno);
			//nameset2.setOnAction(e -> okno);
			single.setOnAction(e -> World.singleplayer = !World.singleplayer);
			TilePane m = new TilePane(Orientation.VERTICAL, 0, 5);
			m.setAlignment(Pos.CENTER);
			m.getChildren().addAll(text, play, nameSet, viewControls);
			TilePane c = new TilePane(Orientation.VERTICAL, 0, 5);
			c.setAlignment(Pos.CENTER);
			c.getChildren().addAll(controlsText1, controlsText2, controlsText3, controlsText4, backToMenu);
			TilePane s = new TilePane(Orientation.VERTICAL, 0, 5);
			s.setAlignment(Pos.CENTER);
			s.getChildren().addAll(nameSet1, nameSet2, single, backToMenu);
			menu = new Scene(m, 1200, 700);
			controls = new Scene(c, 1200, 700);
			setPlayerNames = new Scene(s, 1200, 700);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(menu);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Pong");
			primaryStage.show();
			primaryStage.setOnCloseRequest(this::exitProgram);
			new Thread(this::drawScene).start();
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        @Override
		        public void handle(KeyEvent event) {
		            switch (event.getCode()) {
		                case D:  World.rightP1 = true; break;
		                case A:  World.leftP1 = true; break;
		                case RIGHT:  World.rightP2  = true; break;
		                case LEFT: World.leftP2  = true; break;
		                case O: World.pause = true; primaryStage.setScene(menu); break;
		                case P: World.pause = !World.pause; break;
		            }
		        }
		    });

		    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		        @Override
		        public void handle(KeyEvent event) {
		            switch (event.getCode()) {
		            case D:	World.rightP1 = false; break;
		            case A: World.leftP1 = false; break;
		            case RIGHT:  World.rightP2 = false; break;
		            case LEFT: World.leftP2 = false; break;
		            }
		        }
		    });
			
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