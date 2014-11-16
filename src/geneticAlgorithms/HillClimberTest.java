package geneticAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HillClimberTest extends java.lang.Object {

	private FitnessFunction problem;
	private HillClimber climber;

	@Before
	public void setUp() throws Exception {
		problem = new NumberOfOnes();
		climber = new HillClimber(problem, 100);
	}

	@Test
	public void testStep() {
		String initial = climber.getBest();
		for(int i = 0; i < 15; i++){
			climber.step();
		}
		assertFalse(initial.equals(climber.getBest()));
		assertTrue(problem.fitness(initial) < problem.fitness(climber.getBest()));
	}

}
