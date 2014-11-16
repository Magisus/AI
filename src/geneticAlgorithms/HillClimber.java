package geneticAlgorithms;

public class HillClimber extends java.lang.Object {

	/** The current best individual. */
	private java.lang.String best;

	/** The problem being solved. */
	private FitnessFunction problem;

	public HillClimber(FitnessFunction problem, int length) {
		this.problem = problem;
		best = Individuals.random(length);
	}

	/** Returns the current best individual. */
	public String getBest() {
		return best;
	}

	/**
	 * Generates a mutated version of the current best individual. If the mutant
	 * is better, replaces the best individual with the mutant.
	 */
	public void step() {
		String mutation = Individuals.mutate(best, 1);
		if(problem.fitness(mutation) > problem.fitness(best)){
			best = mutation;
		}
	}

}
