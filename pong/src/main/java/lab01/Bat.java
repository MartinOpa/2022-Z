package lab01;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bat extends Rectangle2D {
	
	private Rectangle2D rect;
	private Point2D position;
	private Point2D start;
	private Point2D speed;
	private double height;
	private double width;
	private int topBoundary = 675;
	private int botBoundary = 190;
	private Point2D up = new Point2D (0,6);
	private Point2D down = new Point2D (0,-6);
	private Point2D zero = new Point2D (0,0);
	
	private Image image;
	private World world;

	public Bat(World world) {
		this(world, new Point2D(0, 0), new Point2D(0, 0), 10, 20);
	}

	public Bat(World world, Point2D start, Point2D speed, double height, double width) {
		super(start.getX(), start.getY(), width, height);
		this.rect = new Rectangle2D(start.getX(), start.getY(), width, height);
		this.height = height;
		this.width = width;
		this.start = start;
		this.position = this.start;
		this.speed = speed;
		this.world = world;
		image = new Image(getClass().getResourceAsStream("Paddle.png"), height, width,
				true, true);
		
	}

	public void draw(GraphicsContext gc) {
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}
	
	public void move(Ball ball) {
		position = position.add(speed);
		rect = new Rectangle2D (position.getX()+5,position.getY()-100,5,150);
		if (rect.intersects(ball.rect)) {
			ball.speedSet();
		}
		if (position.getY() < ball.rect.getMaxY()) {
			speed = up;
		}
		if (position.getY() > ball.rect.getMaxY()) {
			speed = down;
		}
	}
	
	public void player(Ball ball) {
		position = position.add(speed);
		rect = new Rectangle2D (position.getX()+5,position.getY()-80,1,150);
		if (rect.intersects(ball.rect)) {
			ball.speedSet();
		}
		if (position.getY() >= topBoundary) {
			speed = down;
		}
		if (position.getY() <= botBoundary) {
			speed = up;
		}
	}
	/*
	public void player(int input) {
		if (input == 1) {
			speed = down;
		}
		
		if (input == 2) {
			speed = up;
		}
		
		if (input == 3) {
			speed = zero;
		}
	}*/
}
