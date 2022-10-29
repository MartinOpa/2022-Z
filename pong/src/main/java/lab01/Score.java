package lab01;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.BufferedOutputStream;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class Score {
	static int scoreP1;
	static int scoreP2;
	static String playerName1 = "Player 1";
	static String playerName2 = "Player 2";
	private double width;
	private double height;
	static gameOver game;
	private int gameEnd = 10;

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
		gc.strokeText(playerName2, width-100, height-8);
		gc.strokeText(playerName1, 20, height-8);
		if (scoreP1+scoreP2 >= gameEnd) {
		    drawGameOver(canvas);
		}
	}
	
	public void drawGameOver(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(new Font("Comic Sans MS", 80));
        gc.setStroke(Color.WHITE);
        gc.strokeText("GAME  OVER", (width/2)-260, (height/2)+50);
    }
	
	public static void incrementP1() {
		scoreP1 += 1;
	}
	
	public static void incrementP2() {
		scoreP2 += 1;
	}
	
	public static void resetScore() {
	    scoreP1 = 0;
	    scoreP2 = 0;
	}
	
	public static void setPlayerName1(String input) {
		playerName1 = input;
	}
	
	public static void setPlayerName2(String input) {
		playerName2 = input;
	}
	
	class gameOver {
        boolean over;
        int P1;
        int P2;
        String P1Name;
        String P2Name;
        
        public gameOver(int P1, int P2, String P1Name, String P2Name) {
           this.P1 = P1;
           this.P2 = P2;
           this.P1Name = P1Name;
           this.P2Name = P2Name;
        }
        
        public void writeResult() {
        String Winner = "";
        if (this.P1 > this.P2) {
            Winner = this.P1Name;
        } else {
            Winner = this.P2Name;
        }
        String text = "Skóre hráče "+this.P1Name+": "+this.P1+", skóre hráče "+this.P2Name+": "+this.P2+"\n";
        if (this.P1 == this.P2) {
            text = text+"Remíza\n\n";
        } else text = text+"Vítěž: "+Winner+"\n\n";
        
        byte data[] = text.getBytes();
        Path p = Paths.get("./hiscores.txt");
        
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
                out.write(data, 0, data.length);
                out.close();
              } catch (IOException x) {
                System.err.println(x);
              }
        }
	}
}
