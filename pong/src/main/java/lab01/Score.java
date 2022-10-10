package lab01;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
	static int scoreP1;
	static int scoreP2;
	static String playerName1 = "Milk";
	static String playerName2 = "Mocha";
	private double width;
	private double height;

	public Score() {
		this.scoreP1 = 0;
		this.scoreP2 = 0;
	}
	public Score(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(new Font("Comic Sans MS", 80));
		gc.setStroke(Color.WHITE);
		gc.strokeText(Integer.toString(scoreP1), (width/2)-120, (height/2)-80);
		gc.strokeText(Integer.toString(scoreP2), (width/2)+80, (height/2)-80);
		gc.setStroke(Color.BLACK);
		gc.setFont(new Font("Bold Comic Sans MS", 25));
		gc.strokeText(playerName1, width-100, height-8);
		gc.strokeText(playerName2, 20, height-8);
	}
	
	public static void incrementP1() {
		scoreP1 += 1;
	}
	
	public static void incrementP2() {
		scoreP2 += 1;
	}
	
	public static void setPlayerName1(String input) {
		playerName1 = input;
	}
	
	public static void setPlayerName2(String input) {
		playerName2 = input;
	}
}
