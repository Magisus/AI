package geneticAlgorithms;

public class Population extends java.lang.Object {

	/**
	 * The expected number of mutations per individual.
	 * 
	 * @see Constant Field Values
	 */
	// TODO make @see actually link to Constant Field Values, not to Field Values.
	public static final double MUTATION_RATE = 0;

	/**
	 * In choose(), this many BitStrings are chosen at random and the best one
	 * is returned.
	 */
	public static final int POOL_SIZE = 0;

	/** The problem being solved. */
	private FitnessFunction problem;

	/**
	 * @param problem
	 *            The problem being solved.
	 * @param size
	 *            The number of individuals per generation.
	 * @param length
	 *            The length of each individual.
	 */
	public Population(FitnessFunction problem, int size, int length) {

	}
	
	/** Returns the average fitness of the population. */
	public double averageFitness() {
		return -1;
	}
	
	/** Returns the fitness of the most fit individual in the population. */
	public int bestFitness() {
		return -1;
	}
	
	/** Returns a fit individual. Specifically, chooses several individuals at random and returns the fittest one. */
	public java.lang.String choose() {
		return null;
	}
	
	/** Performs one generation of evolution, modifying this population. */
	public void evolve() {
		
	}

	/** Puts individual in this population at index i. For testing only. */
	void setIndividual(int i, java.lang.String individual) {
		
	}

}
