package lab01;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Scenery {

	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		World world = new World(canvas.getWidth(), canvas.getHeight());
		Score score = new Score(canvas.getWidth(), canvas.getHeight());
		while (!Routines.isEndOfThreadRequestedByJavaVM()) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			world.draw(canvas);
			score.draw(canvas);
			Routines.sleep(25);
			world.move(25);
		}
	}
}
