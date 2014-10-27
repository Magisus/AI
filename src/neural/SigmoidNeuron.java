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
		setWeights(weights, StdRandom.uniform());
	}
	
	double getDelta(){
		return delta;
	}
	
	void setDelta(double delta){
		this.delta = delta;
	}

	@Override
	double squash(double sum) {
		return 1.0 / (1 + Math.pow(Math.E, -sum));
	}
	
	public void updateWeights(){
		double[] weights = getWeights();
		for(int i = 0; i < weights.length; i++){
			weights[i] = weights[i] - LEARNING_RATE * getInput(i).getOutput() * delta;
		}
		increaseBias(LEARNING_RATE * -1 * delta);
	}
	
}
