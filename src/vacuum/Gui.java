package vacuum;

import static edu.princeton.cs.introcs.StdDraw.*;

/** Graphic view of the vacuum world. */
public class Gui {

	/** Draws the current state of  world. */
	public static void draw(World world) {
		clear();
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getWidth(); y++) {
				Square s = world.getSquare(x, y);
				if (s.isObstacle()) {
					// Draw obstacle
					setPenColor(BLACK);
					filledRectangle(x + 0.5, y + 0.5, 0.5, 0.5);
				} else if (s.isDirty()) {
					// Draw dirt
					setPenColor(ORANGE);
					filledRectangle(x + 0.5, y + 0.5, 0.5, 0.5);
				}
				// Draw outline of square
				setPenColor(BLACK);
				rectangle(x + 0.5, y + 0.5, 0.5, 0.5);
			}
		}
		// Draw agent
		setPenColor(BLUE);
		filledCircle(world.getAgentX() + 0.5, world.getAgentY() + 0.5, 0.5);
		// Display everything and pause briefly
		show(10);
	}
	
	/** Creates the world and agent, then animates them forever. */
	public static void main(String[] args) {
		World world = new World(25);
		AbstractAgent agent = new StateAgent();
		setScale(0, world.getWidth());
		show(0); // Wait until everything is drawn before first display
		draw(world);
		while (true) {
			world.step(agent);
			draw(world);
		}
	}
	
}