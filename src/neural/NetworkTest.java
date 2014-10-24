package neural;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NetworkTest {

	private Network net;

	private double[][] inputs;
	
	private double[][] correct;
	
	@Before
	public void setUp() throws Exception {
		net = new Network(2, 2, 1);
		inputs = new double[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
		correct = new double[][] {{0}, {1}, {1}, {0}};
	}

	@Test
	public void testManualXor() {
		net.getNeuron(1, 0).setWeights(new double[] { 10.0, 10.0 }, 5.0);
		net.getNeuron(1, 1).setWeights(new double[] { -10.0, -10.0 }, -15.0);
		net.getNeuron(2, 0).setWeights(new double[] { 10.0, 10.0 }, 15.0);		
		for (int i = 0; i < inputs.length; i++) {
			assertArrayEquals(correct[i], net.run(inputs[i]), 0.1);
		}
	}
	
	@Test
	public void testLearnedXor() {
		net.train(inputs, correct, 100000);
		for (int i = 0; i < inputs.length; i++) {
			assertArrayEquals(correct[i], net.run(inputs[i]), 0.1);
		}
	}

}