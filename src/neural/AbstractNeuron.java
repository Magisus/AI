package neural;

/** An artificial neuron. */
public abstract class AbstractNeuron {

	/** Bias weight, effectively associated with a constant input of -1. */
	private double bias;

	/** The other neurons giving input to this one. */
	private AbstractNeuron[] inputs;

	/** Activation of this neuron, in [0.0, 1.0]. */
	private double output;

	/** Weights associated with the inputs. */
	private double[] weights;

	/**
	 * @param inputs
	 *            Neurons giving input to this one.
	 * @param weights
	 *            Weights associated with the inputs.
	 * @param bias
	 *            Bias weight, effectively associated with a constant input of
	 *            -1.
	 */
	public AbstractNeuron(AbstractNeuron[] inputs, double[] weights, double bias) {
		this.inputs = inputs;
		this.weights = weights;
		this.bias = bias;
	}

	/** Returns the ith neuron giving input to this one. */
	AbstractNeuron getInput(int i) {
		return inputs[i];
	}

	/** Returns the output (activation) of this neuron. */
	public double getOutput() {
		return output;
	}

	/** Returns the weight vector for this neuron. */
	double[] getWeights() {
		return weights;
	}

	/** Increases this neuron's bias by the specified amount. */
	void increaseBias(double amount) {
		bias += amount;
	}

	/** Sets the output of this neuron. Used by InputNeuron. */
	void setOutput(double output) {
		this.output = output;
	}

	/** Sets the weights and bias of this neuron. */
	void setWeights(double[] weights, double bias) {
		this.weights = weights;
		this.bias = bias;
	}

	/** Function that maps real numbers to values in the range [0.0, 1.0]. */
	abstract double squash(double sum);

	@Override
	public String toString() {
		return java.util.Arrays.toString(weights) + " " + bias;
	}

	/** Updates the output of this neuron. */
	public void update() {
		double sum = -bias;
		for (int i = 0; i < inputs.length; i++) {
			sum += weights[i] * inputs[i].getOutput();
		}
		output = squash(sum);
	}

}