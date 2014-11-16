package geneticAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PopulationTest {

	private Population population;

	private FitnessFunction problem;

	@Before
	public void setUp() throws Exception {
		problem = new NumberOfOnes();
		population = new Population(problem, 10, 10);
		population.setIndividual(0, "0000000000");
		population.setIndividual(1, "0000000001");
		population.setIndividual(2, "0000000011");
		population.setIndividual(3, "0000000111");
		population.setIndividual(4, "0000001111");
		population.setIndividual(5, "0000011111");
		population.setIndividual(6, "0000111111");
		population.setIndividual(7, "0001111111");
		population.setIndividual(8, "0011111111");
		population.setIndividual(9, "0111111111");
	}

	@Test
	public void testAverageFitness() {
		assertEquals(4.5, population.averageFitness(), 0.001);
	}

	@Test
	public void testBestFitness() {
		assertEquals(9, population.bestFitness());
	}

	@Test
	public void testChoose() {
		int fit = 0;
		for (int i = 0; i < 100; i++) {
			String string = population.choose();
			if(problem.fitness(string) > 5){
				fit++;
			}
		}
		assertTrue(95 < fit);
	}

	@Test
	public void testEvolve() {
		double initialFitness = population.averageFitness();
		for (int i = 0; i < 15; i++) {
			population.evolve();
		}
		assertTrue(initialFitness < population.averageFitness());
	}

}
