package neural;

/** A "neuron" that does no computation, for specifying inputs. Its output can be manually set. */
public class InputNeuron extends AbstractNeuron {

	public InputNeuron() {
		super(new AbstractNeuron[0], null, 0.0);
	}
	
	@Override
	public void setOutput(double output) {
		super.setOutput(output);
	}

	@Override
	public double squash(double sum) {
		// Irrelevant
		return -1;
	}

	@Override
	public void update() {
		// Does nothing
	}

}