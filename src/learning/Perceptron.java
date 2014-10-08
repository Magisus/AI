package learning;

import static edu.princeton.cs.introcs.StdDraw.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.MissingFormatArgumentException;

import edu.princeton.cs.introcs.StdRandom;

public class Perceptron {

	double[] weights;

	public Perceptron(double[] weights) {
		this.weights = weights;
	}

	public Perceptron(int dimensionality) {
		weights = new double[dimensionality + 1];
	}

	public double[] getWeights() {
		return weights;
	}

	public double error(List<Point> data) {
		double misclassified = 0;
		for (Point point : data) {
			if (classify(point) != point.getClassification()) {
				misclassified++;
			}
		}
		return misclassified / data.size();
	}

	public void update(Point p) {
		weights = LinearAlgebra.sum(weights, LinearAlgebra.scale(p.getClassification(), p.getAttributes()));
	}

	public void update(List<Point> data) {
		Collections.shuffle(data);
		List<Point> misclassified = new ArrayList<>();
		for (Point point : data) {
			if (point.getClassification() != classify(point)) {
				misclassified.add(point);
			}
		}
		if (!misclassified.isEmpty()) {
			update(misclassified.get(StdRandom.uniform(misclassified.size())));
			return;
		}

	}

	public int classify(Point point) {
		double sum = LinearAlgebra.dotProduct(weights, point.getAttributes());
		return sum >= 0 ? 1 : -1;
	}

	public void train(List<Point> data) {
		while (error(data) > 0) {
			update(data);
			System.out.println(error(data));
		}

	}

	public void train(List<Point> data, int maxUpdates) {
		for (int i = 0; i < maxUpdates; i++) {
			update(data);
			System.out.println(error(data));
		}
	}

	public void trainAnimated(List<Point> data) {
		System.out.println("initial error: " + error(data));
		while (error(data) > 0) {
			update(data);
			System.out.println(error(data));
			show(0);
			clear();
			Gui.draw(weights);
			Gui.draw(data);
			show(100);
		}
	}

	public static void main(String[] args) {
		List<Point> data = new ArrayList<>();
		double[] weights = new double[3];
		weights[1] = Math.random() - 0.5;
		weights[2] = Math.random() - 0.5;
		weights[0] = -(0.5 * weights[1] + 0.5 * weights[2]);
		for (int i = 0; i < 100; i++) {
			data.add(new Point(weights));
		}

		Perceptron perc = new Perceptron(2);
		perc.trainAnimated(data);

	}

}
