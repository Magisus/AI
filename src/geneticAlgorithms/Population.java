package geneticAlgorithms;

import edu.princeton.cs.introcs.StdRandom;

public class Population {

	/**
	 * The expected number of mutations per individual.
	 */
	public static final double MUTATION_RATE = 1.0;

	/**
	 * In choose(), this many BitStrings are chosen at random and the best one
	 * is returned.
	 */
	public static final int POOL_SIZE = 10;

	/** The problem being solved. */
	private FitnessFunction problem;

	private String[] individuals;

	/**
	 * @param problem
	 *            The problem being solved.
	 * @param size
	 *            The number of individuals per generation.
	 * @param length
	 *            The length of each individual.
	 */
	public Population(FitnessFunction problem, int size, int length) {
		this.problem = problem;
		individuals = new String[size];
		for (int i = 0; i < size; i++) {
			individuals[i] = Individuals.random(length);
		}
	}

	/** Returns the average fitness of the population. */
	public double averageFitness() {
		double total = 0.0;
		for (int i = 0; i < individuals.length; i++) {
			total += problem.fitness(individuals[i]);
		}
		return total / individuals.length;
	}

	/** Returns the fitness of the most fit individual in the population. */
	public int bestFitness() {
		String best = individuals[0];
		for (int i = 1; i < individuals.length; i++) {
			if (problem.fitness(best) < problem.fitness(individuals[i])) {
				best = individuals[i];
			}
		}
		return problem.fitness(best);
	}

	/**
	 * Returns a fit individual. Specifically, chooses several individuals at
	 * random and returns the fittest one.
	 */
	public String choose() {
		int best = 0;
		int index = StdRandom.uniform(individuals.length);
		String bestIndiv = individuals[index];
		for (int i = 0; i < POOL_SIZE - 1; i++) {
			index = StdRandom.uniform(individuals.length);
			String individual = individuals[index];
			if (problem.fitness(individual) > best) {
				best = problem.fitness(individual);
				bestIndiv = individual;
			}
		}
		return bestIndiv;
	}

	/** Performs one generation of evolution, modifying this population. */
	public void evolve() {
		String[] newPop = new String[individuals.length];
		for (int i = 0; i < individuals.length; i++) {
			String parent1 = choose();
			String parent2 = choose();
			String child = Individuals.cross(parent1, parent2);
			child = Individuals.mutate(child, MUTATION_RATE);
			newPop[i] = child;
		}
		individuals = newPop;
	}

	/** Puts individual in this population at index i. For testing only. */
	void setIndividual(int i, String individual) {
		individuals[i] = individual;
	}

}
