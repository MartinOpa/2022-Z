package lab01;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bat extends Rectangle2D {
	
	Rectangle rect;
	private Rectangle2D rect2D;
	private Point2D position;
	private Point2D start;
	private Point2D speed;
	private double height;
	private double width;
	private int botBoundary = 670;
	private int topBoundary = 30;
	private Point2D up = new Point2D (0,7);
	private Point2D down = new Point2D (0,-7);
	private Point2D zero = new Point2D (0,0);
	boolean right = false;
	boolean left = false;
	
	private Image image;
	private World world;

	public Bat(World world) {
		this(world, new Point2D(0, 0), new Point2D(0, 0), 10, 20);
	}

	public Bat(World world, Point2D start, Point2D speed, double height, double width) {
		super(start.getX(), start.getY(), width, height);
		this.rect = new Rectangle(start.getX(), start.getY(), width, height);
		this.rect2D = new Rectangle2D(start.getX(), start.getY(), width, height);
		this.height = height;
		this.width = width;
		this.start = start;
		this.position = this.start;
		this.speed = speed;
		this.world = world;
		this.right = false;
		this.left = false;
		image = new Image(getClass().getResourceAsStream("Paddle.png"), height, width,
				true, true);
		
	}

	public void draw(GraphicsContext gc, Rectangle rect) {
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.setFill(Color.WHITE);
		gc.fillRect(rect.getX(), rect.getY(), 10, 150);
		//gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}
	
	public void move(Ball ball) {
		if (!World.pause) {
		    if (speed == up && position.getY() < botBoundary-height) {
                position = position.add(speed);
            }
            if (speed == down && position.getY() > topBoundary) {
                position = position.add(speed);
            }
            rect = new Rectangle (position.getX(),position.getY(),10,150);
            rect2D = new Rectangle2D (position.getX(),position.getY(),10,150);
            if (rect2D.intersects(ball.rect2D)) {
                ball.speedSet();
            }
            if (position.getY()+(height/2) < ball.rect2D.getMaxY()) {
                speed = up;
            }
            if (position.getY()+(height/2) > ball.rect2D.getMaxY()) {
                speed = down;
            }
		    }
	}
	
	public void moveP1(Ball ball) {
		if (!World.pause) {
		    if (speed == up && position.getY() < botBoundary-height) {
		        position = position.add(speed);
		    }
		    if (speed == down && position.getY() > topBoundary) {
		        position = position.add(speed);
		    }
		    rect = new Rectangle (position.getX(),position.getY(),10,150);
		    rect2D = new Rectangle2D (position.getX(),position.getY(),10,150);
		    if (rect2D.intersects(ball.rect2D)) {
		        ball.speedSet();
		    }
		    if (right) {
		        speed = up;
		    }
		    if (left) {
			    speed = down;
		    }
		    if (!right && !left) {
		        speed = zero;
		    }
		}
	}
}
