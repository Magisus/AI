package neural;

import java.awt.Color;
import edu.princeton.cs.introcs.*;
import static edu.princeton.cs.introcs.StdDraw.*;
import static java.lang.Math.*;

/** Handwritten digit recognizer with graphic user interface. */
public class DigitRecognizer {

	/** Half width of each square in the grid. */
	public static final double SQUARE_RADIUS = 0.5 / 32;

	public static void main(String[] args) {
		new DigitRecognizer().run();
	}

	/** The neural network. */
	private Network net;

	/** The input values. */
	private double[] pixels;

	public DigitRecognizer() {
		System.out.println("Reading data file...");
		In input = new In("semeion.data");
		// The "magic numbers" below correspond to the size of the raw data
		double[][] inputs = new double[1593][256];
		double[][] correct = new double[1593][10];
		int i = 0;
		while (input.hasNextLine()) {
			String[] values = input.readLine().split(" ");
			for (int j = 0; j < 256; j++) {
				inputs[i][j] = Double.parseDouble(values[j]);
			}
			for (int j = 0; j < 10; j++) {
				correct[i][j] = Double.parseDouble(values[j + 256]);
			}
			i++;
		}
		System.out.println("Training neural network...");
		net = new Network(256, 50, 10);
		net.train(inputs, correct, 500);
		pixels = new double[256];
		net.run(pixels);
	}

	/** Draws the two buttons on the screen. */
	void drawControls() {
		text(0.25, 0.95, "Draw a digit in the grid at left,");
		text(0.25, 0.9, "then click on Classify.");
		rectangle(0.2, 0.1, 0.1, 0.1);
		text(0.2, 0.1, "Clear");
		rectangle(0.5, 0.1, 0.1, 0.1);
		text(0.5, 0.1, "Classify");
	}

	/** Draws the grid where the user draws a digit. */
	void drawGrid() {
		for (int r = 0; r < 16; r++) {
			for (int c = 0; c < 16; c++) {
				if (pixels[r * 16 + c] > 0.5) {
					filledRectangle(c / 32.0, 0.75 - r / 32.0, SQUARE_RADIUS,
							SQUARE_RADIUS);
				} else {
					rectangle(c / 32.0, 0.75 - r / 32.0, SQUARE_RADIUS,
							SQUARE_RADIUS);
				}
			}
		}
	}

	/** Draws shaded circles indicating the network's output. */
	void drawOutput() {
		for (int i = 0; i < 10; i++) {
			text(0.85, 0.05 + 0.1 * i, "" + i);
			int brightness = (int) (256 * (1.0 - net.getNeuron(2, i)
					.getOutput()));
			setPenColor(new Color(brightness, brightness, brightness));
			filledCircle(0.95, 0.05 + 0.1 * i, 0.05);
			setPenColor();
			circle(0.95, 0.05 + 0.1 * i, 0.05);
		}
	}

	/** Respond to mouse actions. */
	void handleMouse() {
		if (mousePressed()) {
			double x = mouseX();
			double y = mouseY();
			if (0.1 < x && x < 0.3 && 0.0 < y && y < 0.2) {
				// Click on Clear
				pixels = new double[pixels.length];
				while (mousePressed()) {
					// Wait for mouse release
				}
			} else if (0.4 < x && x < 0.6 && 0.0 < y && y < 0.2) {
				// Click on Classify
				net.run(pixels);
				while (mousePressed()) {
					// Wait for mouse release
				}
			} else if (0.0 - SQUARE_RADIUS < x && x < 15.0 / 32 + SQUARE_RADIUS
					&& 0.75 - 15.0 / 32 - SQUARE_RADIUS < y
					&& y < 0.75 + SQUARE_RADIUS) {
				// Mouse down in grid
				int r = (int) ((0.75 - (y - SQUARE_RADIUS)) * 32);
				r = min((max(r, 0)), 15);
				int c = (int) ((x + SQUARE_RADIUS) * 32);
				c = min((max(c, 0)), 15);
				pixels[r * 16 + c] = 1.0;
			}
		}
	}

	/** Main interactive loop. */
	public void run() {
		show(0);
		while (true) {
			clear();
			drawGrid();
			drawOutput();
			drawControls();
			handleMouse();
			show(0);
		}
	}

}