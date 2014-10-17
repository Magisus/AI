package learning;

import java.util.List;

public class PocketPerceptron extends Perceptron{
	
	double[] bestWeightsSoFar;
	
	double leastError;

	public PocketPerceptron(double[] weights) {
		super(weights);
		this.bestWeightsSoFar = weights;
		leastError = Double.MAX_VALUE;
	}

	public PocketPerceptron(int dimensionality) {
		super(dimensionality);
		bestWeightsSoFar = new double[dimensionality + 1];
		leastError = Double.MAX_VALUE;
	}

	public int classify(Point point, double[] weightsToUse) {
		double sum = LinearAlgebra.dotProduct(weightsToUse, point.getAttributes());
		return sum >= 0 ? 1 : -1;
	}

	@Override
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
	
	public double errorWithBestWeights(List<Point> data){
		double misclassified = 0;
		for (Point point : data) {
			if (classify(point, bestWeightsSoFar) != point.getClassification()) {
				misclassified++;
			}
		}
		return misclassified / data.size();
	}

	public void train(List<Point> data, List<Point> testData, int maxUpdates) {
		for (int i = 0; i < maxUpdates; i++) {
			update(data);
			error(data);
			System.out.println(errorWithBestWeights(data) + "," + errorWithBestWeights(testData));
		}
	}

}
