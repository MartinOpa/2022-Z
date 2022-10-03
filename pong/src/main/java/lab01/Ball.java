package lab01;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class Ball extends Rectangle2D {

	Rectangle2D rect;
	private Point2D position;
	private Point2D start;
	private Point2D speed;
	private Point2D initialSpeed;
	private double size;
	private int worldTop = 675;
	private int worldBot = 85;
	private int worldRight = 1400;
	private int worldLeft = -200;
	public Point2D collision;
	private Point2D leftBat = new Point2D (10, 10);
	private Point2D leftBat2 = new Point2D (10, -10);
	private Point2D rightBat = new Point2D (-10, 10);
	private Point2D rightBat2 = new Point2D (-10, -10);
	
	private Image image;
	private World world;

	public Ball(World world) {
		this(world, new Point2D(0, 0), new Point2D(0, 0), 10);
	}

	public Ball(World world, Point2D start, Point2D speed, double size) {
		super(start.getX(), start.getY(), size, size);
		this.rect = new Rectangle2D(start.getX(), start.getY(), 5, 5);
		this.start = start;
		this.position = this.start;
		this.speed = speed;
		this.initialSpeed = speed;
		this.size = size;
		this.world = world;
		this.leftBat = speed;
		this.rightBat = leftBat.subtract(20, 20);
		image = new Image(getClass().getResourceAsStream("Ball.png"), size, size,
				true, true);
	}

	public void draw(GraphicsContext gc) {
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}
	
	public void move(int timeStep) {
		position = position.add(speed);
		rect = new Rectangle2D (position.getX(),position.getY(),40,40);
		if (position.getY() <= worldBot) {
			speed = speed.subtract(0,-20);
		}
		if (position.getY() >= worldTop) {
			speed = speed.subtract(0,20);
		}
		
		if (position.getX() >= worldRight) {
			position = new Point2D(rand(400, 800), rand(100, 600));
			if (position.getX() < 600) {
				speed = initialSpeed;
				if (rand(10, 20) >= 15) {
					speed.subtract(0,20);
				}
			} else {
				speed = initialSpeed.subtract(0,20);
				if (rand(10, 20) >= 15) {
					speed.subtract(0,20);
				}
			}
		}
		if (position.getX() <= worldLeft) {
			position = new Point2D(rand(400, 800), rand(100, 600));
			if (position.getX() < 600) {
				speed = initialSpeed;
				if (rand(10, 20) >= 15) {
					speed.subtract(0,20);
				}
			} else {
				speed = initialSpeed.subtract(20,0);
				if (rand(10, 20) >= 15) {
					speed.subtract(0,20);
				}
			}
		}
	}
	
	public void speedSet() {
		if (position.getX() < 600) {
			if (speed.getY() < 0) {
				speed = leftBat2;
			} else speed = leftBat;
		}
		if (position.getX() > 600) {
			if (speed.getY() < 0) {
				speed = rightBat2;
			} else speed = rightBat;
		}
	}
	
	public int rand(int min, int max) {
	    Random random = new Random();
	    return random.ints(min, max).findFirst().getAsInt();
	}
}
