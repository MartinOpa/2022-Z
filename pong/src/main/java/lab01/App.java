package lab01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;



public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private Canvas canvas;
	private Scene menu;
	private Scene singleScene;
	private Scene multiScene;
	private Scene controls;
	private Scene scene;
	private Scene nameSetScene;
	private Scene hiscores;
	private TextField name1;
	private TextField name2;
	
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
			Text singleText = new Text(10, 40, "Current gamemode: SINGLEPLAYER");
			Text multiText = new Text(10, 40, "Current gamemode: MULTIPLAYER");
			Text nameText = new Text(10, 40, "Use buttons to set player names");
			Text tempHiscoresText = new Text(10, 40, getData());
	        text.setFont(new Font(40));
	        
	        Label name1Label = new Label("Player 1 name: ");
	        name1 = new TextField("Player 1");
	        HBox name1Hbox = new HBox();
	        
	        name1Hbox.getChildren().addAll(name1Label, name1);
	        
	        Label name2Label = new Label("Player 2 name: ");
	        name2 = new TextField("Player 2");
	        HBox name2Hbox = new HBox();
	        
	        name2Hbox.getChildren().addAll(name2Label, name2);
	        
			Button play = new Button("Play");
			Button singleplayer = new Button("Choose between SP and MP");
			Button viewControls = new Button("View controls");
			Button backToMenu = new Button ("Return to main menu");
			Button backToMenu2 = new Button ("Return to main menu");
			Button backToMenu3 = new Button ("Return to main menu");
			Button backToMenu4 = new Button ("Submit and return to main menu");
			Button backToMenu5 = new Button ("Return to main menu");
			Button nameSet = new Button("Change player names");
			Button single1 = new Button("Change to multiplayer mode");
			Button single2 = new Button("Change to singleplayer mode");
			Button showHiscores = new Button ("High scores");
			
			play.setOnAction(e -> {
			    Score.resetScore();
			    Ball.resetBallPos();
			    primaryStage.setScene(scene);
			});
			singleplayer.setOnAction(e -> {
			    if (World.singleplayer) {
			        primaryStage.setScene(singleScene);
			    } else primaryStage.setScene(multiScene);
			});
			
			viewControls.setOnAction(e -> primaryStage.setScene(controls));
			backToMenu.setOnAction(e -> primaryStage.setScene(menu));
			backToMenu2.setOnAction(e -> primaryStage.setScene(menu));
	        backToMenu3.setOnAction(e -> primaryStage.setScene(menu));
	        backToMenu5.setOnAction(e -> primaryStage.setScene(menu));
	        nameSet.setOnAction(e -> primaryStage.setScene(nameSetScene));
	       
	        backToMenu4.setOnAction(e -> {
	            Score.setPlayerName1(name1.getText());
	            Score.setPlayerName2(name2.getText());
	            primaryStage.setScene(menu);
	        });
	        
			single1.setOnAction(e -> {
			    World.singleplayer = false;
                primaryStage.setScene(multiScene);
            });
			
			single2.setOnAction(e -> {
			    World.singleplayer = true;
			    primaryStage.setScene(singleScene);
			});
			
			TilePane m = new TilePane(Orientation.VERTICAL, 0, 5);
			m.setAlignment(Pos.CENTER);
			m.getChildren().addAll(text, play, singleplayer, nameSet, viewControls, showHiscores);
			
			TilePane c = new TilePane(Orientation.VERTICAL, 0, 5);
			c.setAlignment(Pos.CENTER);
			c.getChildren().addAll(controlsText1, controlsText2, controlsText3, controlsText4, backToMenu);
			
			TilePane s = new TilePane(Orientation.VERTICAL, 0, 5);
			s.setAlignment(Pos.CENTER);
			s.getChildren().addAll(singleText, single1, backToMenu2);
			
			TilePane s2 = new TilePane(Orientation.VERTICAL, 0, 5);
            s2.setAlignment(Pos.CENTER);
            s2.getChildren().addAll(multiText, single2, backToMenu3);
            
            TilePane n = new TilePane(Orientation.VERTICAL, 0, 5);
            n.setAlignment(Pos.CENTER);
            n.getChildren().addAll(nameText, name1Hbox, name2Hbox, backToMenu4);
            
            TilePane h = new TilePane(Orientation.VERTICAL, 0, 5);
            h.setAlignment(Pos.CENTER);
            h.getChildren().addAll(tempHiscoresText, backToMenu5);
            
            showHiscores.setOnAction(e -> {
                h.getChildren().clear();
                Text hiscoresText = new Text(10, 40, getData());
                h.getChildren().addAll(hiscoresText, backToMenu5);
                primaryStage.setScene(hiscores);
            });
			
			menu = new Scene(m, 1200, 700);
			controls = new Scene(c, 1200, 700);
			singleScene = new Scene(s, 1200, 700);
	        multiScene = new Scene(s2, 1200, 700);
	        nameSetScene = new Scene(n, 1200, 700);
	        hiscores = new Scene(h, 1200, 700);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
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
	
	private String getData() {
	    Path p = Paths.get("./hiscores.txt");
	    String result = "";
        
	    try (InputStream in = Files.newInputStream(p);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            result = result+line+"\n";
	        }
	        in.close();
	    } catch (IOException x) {
	        System.err.println(x);
	    }
	    
	    return result;
	}
	
	private void drawScene() {
		new Scenery().draw(canvas);
	}
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}