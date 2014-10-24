package neural;

public class SigmoidNeuron extends AbstractNeuron{
	
	private double delta;

	public SigmoidNeuron(InputNeuron[] neurons){
		super(neurons, null, 0);
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
		//use delta
	}
	
}
