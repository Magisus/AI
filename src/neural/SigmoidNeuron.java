package neural;

import edu.princeton.cs.introcs.StdRandom;

public class SigmoidNeuron extends AbstractNeuron{
	
	public static final double LEARNING_RATE = 0.1;
	
	private double delta;

	public SigmoidNeuron(AbstractNeuron[] input){
		super(input, null, -1);
		double[] weights = new double[input.length];
		for(int i = 0; i < weights.length; i++){
			weights[i] = StdRandom.uniform();
		}
		setWeights(weights, -1);
	}
	
	double getDelta(){
		return delta;
	}
	
	void setDelta(double delta){
		this.delta = delta;
	}

	@Override
	double squash(double sum) {
		return Math.pow(Math.E, sum) / (1 + Math.pow(Math.E, sum));
	}
	
	public void updateWeights(){
		double[] weights = new double[getWeights().length];
		for(int i = 0; i < getWeights().length; i++){
			weights[i] = getWeights()[i] - LEARNING_RATE * getInput(i).getOutput() * delta;
		}
		setWeights(weights, -1);
	}
	
}
