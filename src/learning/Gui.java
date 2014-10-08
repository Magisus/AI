package learning;

import java.awt.Color;
import java.util.*;

import static edu.princeton.cs.introcs.StdDraw.*;

/** Graphic user interface for linear classification in 2-dimensional space. */
public class Gui {

	/**
	 * Draws points. Points with classification 1 are drawn as blue +s, others
	 * as red -s.
	 */
	public static void draw(Collection<Point> points) {
		for (Point p : points) {
			double[] attributes = p.getAttributes();
			if (p.getClassification() == 1) {
				setPenColor(BLUE);
				text(attributes[1], attributes[2], "+");
			} else {
				setPenColor(RED);
				text(attributes[1], attributes[2], "-");
			}
		}
	}

	/**
	 * Draws pale polygons showing the decision region of a perceptron with
	 * weights.
	 */
	public static void draw(double[] weights) {
		double a = weights[0];
		double b = weights[1];
		double c = weights[2];
		double y0 = -a / c;
		double y1 = -(a + b) / c;
		if (c > 0) {
			setPenColor(new Color(191, 191, 255));
			filledPolygon(new double[] { 0, 1, 1, 0 }, new double[] { y0, y1,
					1, 1 });
			setPenColor(new Color(255, 191, 191));
			filledPolygon(new double[] { 0, 1, 1, 0 }, new double[] { y0, y1,
					0, 0 });
		} else {
			setPenColor(new Color(191, 191, 255));
			filledPolygon(new double[] { 0, 1, 1, 0 }, new double[] { y0, y1,
					0, 0 });
			setPenColor(new Color(255, 191, 191));
			filledPolygon(new double[] { 0, 1, 1, 0 }, new double[] { y0, y1,
					1, 1 });
		}
		// Some of the polygons we've drawn have strange "bowtie" shapes. This
		// is only a problem outside of the region of interest. The following
		// rectangles cover this irrelevant shading.
		setPenColor(WHITE);
		filledRectangle(0.5, 3, 2, 2);
		filledRectangle(0.5, -2, 2, 2);
	}

	/**
	 * Generates a random decision line and a bunch of points classified by that
	 * line. Displays the points and the correct line.
	 */
	public static void main(String[] args) {
		List<Point> data = new ArrayList<>();
		double[] weights = new double[3];
		weights[1] = Math.random() - 0.5;
		weights[2] = Math.random() - 0.5;
		weights[0] = -(0.5 * weights[1] + 0.5 * weights[2]);
		for (int i = 0; i < 100; i++) {
			data.add(new Point(weights));
		}
		
//		Perceptron perc = new Perceptron(2);
//		perc.train(data);
		show(0);
		clear();
		draw(weights);
		draw(data);
		show(0);
	}

}
