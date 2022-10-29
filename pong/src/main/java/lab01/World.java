package lab01;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World extends App {
	//private static final Image BACKGROUND;
	private double width;
	private double height;
	private Ball ball;
	private Bat bat1;
	private Bat bat2;
	static boolean pause = true;
	static boolean singleplayer = true;
	static boolean leftP1 = false;
	static boolean rightP1 = false;
	static boolean leftP2 = false;
	static boolean rightP2 = false;
	
	public World(double width, double height) {
		this.width = width;
		this.height = height;
		ball = new Ball(this, new Point2D(500, 350), new Point2D(10, 10), 25);
		bat1 = new Bat(this, new Point2D(10, (height/2)-75), new Point2D(0, 7), 150, 5);
		bat2 = new Bat(this, new Point2D(1180, (height/2)-75), new Point2D(0, 7), 150, 5);
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}
	
	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.LIGHTGREY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 30, canvas.getWidth(), canvas.getHeight()-60);
		gc.setFill(Color.LIGHTGREY);
		gc.fillRect((canvas.getWidth()/2)-15, 30, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 110, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 190, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 270, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 350, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 430, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 510, 30, 40);
		gc.fillRect((canvas.getWidth()/2)-15, 590, 30, 40);
			ball.draw(gc, ball.rect);
			bat1.draw(gc, bat1.rect);
			bat2.draw(gc, bat2.rect);
		
	}
	
	public void move(int timeDelta) {
		if (!pause) {
			ball.move(timeDelta);
			if (singleplayer) {
				bat2.move(ball);
			} else {
				bat2.left = leftP2;
				bat2.right = rightP2;
				bat2.moveP1(ball);
			}
			bat1.left = leftP1;
			bat1.right = rightP1;
			bat1.moveP1(ball);
		}
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
