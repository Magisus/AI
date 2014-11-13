package geneticAlgorithms;

public interface FitnessFunction {

	/**
	 * Returns the fitness of individual. The result is a non-negative number.
	 * Higher numbers indicate more fit individuals.
	 */
	int fitness(java.lang.String individual);

	/**
	 * Returns true if individual has the highest possible fitness. This is for
	 * efficient early stopping; algorithms will still work (albeit more slowly)
	 * if this method always returns false.
	 */
	boolean isOptimal(java.lang.String individual);

}
