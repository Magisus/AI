package learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.introcs.StdRandom;

public class PocketPerceptron {
	
	double[] weights;
	
	double[] bestWeightsSoFar;
	
	double leastError;

	public PocketPerceptron(double[] weights) {
		this.weights = weights;
		leastError = Double.MAX_VALUE;
	}

	public PocketPerceptron(int dimensionality) {
		weights = new double[dimensionality + 1];
		leastError = Double.MAX_VALUE;
	}

	public double[] getWeights() {
		return weights;
	}

	public double error(List<Point> data) {
		double misclassified = 0;
		for (Point point : data) {
			if (classify(point, weights) != point.getClassification()) {
				misclassified++;
			}
		}
		double error = misclassified / data.size();
		//Check if we have found new best weights
		if(error < leastError){
			leastError = error;
			bestWeightsSoFar = weights;
		}
		return error;
	}
	
	/** Like error, but uses the best weights seen so far. */
	public double errorWithBestWeights(List<Point> data){
		return -1;
	}

	public void update(Point p) {
		weights = LinearAlgebra.sum(weights, LinearAlgebra.scale(p.getClassification(), p.getAttributes()));
	}

	public void update(List<Point> data) {
		Collections.shuffle(data);
		List<Point> misclassified = new ArrayList<>();
		for (Point point : data) {
			if (point.getClassification() != classify(point, weights)) {
				misclassified.add(point);
			}
		}
		if (!misclassified.isEmpty()) {
			update(misclassified.get(StdRandom.uniform(misclassified.size())));
			return;
		}
		Collections.shuffle(data);

	}

	public int classify(Point point, double[] weightsToUse) {
		double sum = LinearAlgebra.dotProduct(weightsToUse, point.getAttributes());
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

}
