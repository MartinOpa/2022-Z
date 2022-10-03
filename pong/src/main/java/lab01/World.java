package lab01;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class World {
	private static final Image BACKGROUND;
	private double width;
	private double height;
	private Ball ball;
	private Bat bat1;
	private Bat bat2;
	
	static{ 
		BACKGROUND = new Image(World.class.getResourceAsStream("Background.png"));
	}
	
	public World(double width, double height) {
		this.width = width;
		this.height = height;
		ball = new Ball(this, new Point2D(500, 350), new Point2D(10, 10), 25);
		bat1 = new Bat(this, new Point2D(25, 420), new Point2D(0, 7), 500, 150);
		bat2 = new Bat(this, new Point2D(1170, 420), new Point2D(0, 7), 500, 150);
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}
	
	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(BACKGROUND, 0, 0, width, height);
		ball.draw(gc);
		bat1.draw(gc);
		bat2.draw(gc);
	}
	
	public void move(int timeDelta) {
		ball.move(timeDelta);
		bat2.move(ball);
		bat1.move(ball);
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
