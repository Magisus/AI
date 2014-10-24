package neural;

import edu.princeton.cs.introcs.StdRandom;

public class Network {

	private AbstractNeuron[] input;
	private SigmoidNeuron[] hidden;
	private SigmoidNeuron[] output;

	public Network(int in, int hid, int out) {
		this.input = new InputNeuron[in];
		for (int i = 0; i < in; i++) {
			input[i] = new InputNeuron();
		}
		this.hidden = new SigmoidNeuron[hid];
		for (int i = 0; i < hid; i++) {
			hidden[i] = new SigmoidNeuron(input);
		}
		this.output = new SigmoidNeuron[out];
		for (int i = 0; i < out; i++) {
			output[i] = new SigmoidNeuron(hidden);
		}
	}

	public void train(double[][] inputs, double[][] correct) {
		//Calculate all x's
		int index = StdRandom.uniform(inputs.length);
		double[] instance = inputs[index];
		double[] correctInst = correct[index];
		for (int j = 0; j < input.length; j++) {
			input[j].setOutput(instance[j]);
		}
		for (SigmoidNeuron neuron : hidden) {
			neuron.update();
		}
		for(SigmoidNeuron outNeuron : output){
			outNeuron.update();
		}
		//Calculate all deltas
		for(int i = 0; i < output.length; i++){
			double out = output[i].getOutput();
			double delta = (out - correctInst[i]) * (1 - out) * output[i].squash(out);
			output[i].setDelta(delta);
		}
		for(int i = 0; i < hidden.length; i++){
			double sum = 0;
			for(SigmoidNeuron out : output){
				sum += out.getDelta() * out.getWeights()[i];
			}
			double delta = sum * (1 - hidden[i].getOutput()) * hidden[i].squash(hidden[i].getOutput());
			hidden[i].setDelta(delta);
		}
		//Adjust weights
		for(SigmoidNeuron neuron : hidden){
			neuron.updateWeights();
		}
		for(SigmoidNeuron out : output){
			out.updateWeights();
		}
	}

	public void train(double[][] inputs, double[][] correct, int epochs) {
		for (int i = 0; i < epochs; i++) {
			train(inputs, correct);
		}

	}

	public double[] run(double[] pixels) {
		// TODO Auto-generated method stub
		return null;

	}

	public AbstractNeuron getNeuron(int layer, int index) {
		if (layer == 0) {
			return input[index];
		} else if (layer == 1) {
			return hidden[index];
		} else {
			return output[index];
		}
	}

}
