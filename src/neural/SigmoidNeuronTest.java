package neural;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SigmoidNeuronTest {

	private SigmoidNeuron neuron;
	
	@Before
	public void setUp() throws Exception {
		neuron = new SigmoidNeuron(new InputNeuron[0]);
	}

	@Test
	public void testSquash() {
		assertEquals(0.007, neuron.squash(-5.0), 0.001);
		assertEquals(0.269, neuron.squash(-1.0), 0.001);
		assertEquals(0.500, neuron.squash(0.0), 0.001);
		assertEquals(0.731, neuron.squash(1.0), 0.001);
		assertEquals(0.993, neuron.squash(5.0), 0.001);
	}

}
