package neural;

public class Network {
	
	private AbstractNeuron[] in;
	private AbstractNeuron[] hid;
	private AbstractNeuron[] out;

	public Network(int in, int hid, int out) {
		this.in = new AbstractNeuron[in];
		this.hid = new AbstractNeuron[hid];
		this.out = new AbstractNeuron[out];
	}

	public void train(double[][] inputs, double[][] correct){
		
	}
	
	public void train(double[][] inputs, double[][] correct, int epochs) {
		for(int i = 0; i < epochs; i++){
			train(inputs, correct);
		}
		
	}

	public double[] run(double[] pixels) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public AbstractNeuron getNeuron(int layer, int index) {
		if(layer == 0){
			return in[index];
		} else if(layer == 1){
			return hid[index];
		} else{
			return out[index];
		}
	}

}
