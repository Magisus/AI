package learning;

import java.util.List;

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
		for(Point point : data){
			if(classify(point) != point.getClassification()){
				misclassified++;
			}
		}
		return misclassified / data.size();
	}

	public void update(Point p) {
		for(int i = 0; i < weights.length; i++){
			weights[i] = weights[i] + p.getClassification() * p.getAttributes()[i];
		}
		
	}
	
	public void update(List<Point> data) {
		for(Point point : data){
			if(point.getClassification() != classify(point)){
				update(point);
				return;
			}
		}
		
	}

	public int classify(Point point) {
		double sum = 0;
		for(int i = 0; i < weights.length; i++){
			sum += weights[i] * point.getAttributes()[i];
		}
		return sum >= 0 ? 1 : -1;
	}

	public void train(List<Point> data) {
		// TODO Auto-generated method stub
		
	}

	public void train(List<Point> data, int maxUpdates) {
		// TODO Auto-generated method stub
		
	}
	
	public void trainAnimated(List<Point> data) {
		
	}

}
