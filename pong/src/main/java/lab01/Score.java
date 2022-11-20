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
	private int Score1;
    private int Score2;
    private String Name1 = "Player 1";
    private String Name2 = "Player 2";
    private String MatchRes = "Remíza";
	private double width;
	private double height;
	static gameOver game;
	private int gameEnd = 10;
	
	public Score() {
		this.scoreP1 = 0;
		this.scoreP2 = 0;
	}
	
	public Score(int sp1, int sp2, String pn1, String pn2, String MatchRes) {
        this.Score1 = sp1;
        this.Score2 = sp2;
        this.Name1 = pn1;
        this.Name2 = pn2;
        if (sp1 > sp2) {
            this.MatchRes = "Vítěz: "+pn1;
        }
        if (sp1 < sp2) {
            this.MatchRes = "Vítěz: "+pn2;
        }
        if (sp1 == sp2) {
            this.MatchRes = "Remíza";
        }
    }
	
	public Score(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public Score(int sp1, String pn1, int sp2, String pn2) {
        this.scoreP1 = sp1;
        this.playerName1 = pn1;
        this.scoreP2 = sp2;
        this.playerName2 = pn2;
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
	
    public int getScoreP1() {
        return this.scoreP1;
    }
    
    public String getNameP1() {
        return this.playerName1;
    }
    
    public int getScoreP2() {
        return this.scoreP2;
    }
    
    public String getNameP2() {
        return this.playerName2;
    }
    
    public int getScore1() {
        return this.Score1;
    }
    
    public String getName1() {
        return this.Name1;
    }
    
    public int getScore2() {
        return this.Score2;
    }
    
    public String getName2() {
        return this.Name2;
    }
    
    public String getMatchRes() {
        return this.MatchRes;
    }
    
	public class gameOver {
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

	}
}
