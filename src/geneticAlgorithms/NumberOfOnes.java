package geneticAlgorithms;

public class NumberOfOnes implements FitnessFunction {
	
	/** Fitness is the number of '1's in the individual. */
	@Override
	public int fitness(String individual) {
		int numberOfOnes = 0;
		for(int i = 0; i < individual.length(); i++){
			if(individual.charAt(i) == '1'){
				numberOfOnes++;
			}
		}
		return numberOfOnes;
	}
	
	@Override
	public boolean isOptimal(String individual) {
		return !individual.contains("0");
	}


}
