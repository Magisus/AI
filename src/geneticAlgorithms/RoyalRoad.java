package geneticAlgorithms;

public class RoyalRoad implements FitnessFunction {

	private final int chunkSize;

	/**
	 * @param chunkSize
	 *            Length of each chunk.
	 */
	public RoyalRoad(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	/**
	 * Fitness is the number of chunks that are all ones. For example, if chunk
	 * size is 3, one point of fitness is granted if all of the first three bits
	 * are ones, one if all of the next three bits are ones, etc.
	 */
	@Override
	public int fitness(String individual) {
		int chunkCount = (int)Math.floor((double)individual.length() / chunkSize);
		int fitness = 0;
		for(int i = 0; i < chunkCount; i++){
			String chunk = individual.substring(i * chunkSize, (i + 1) * chunkSize);
			if(!chunk.contains("0")){
				fitness++;
			}
		}
		return fitness;
	}

	@Override
	public boolean isOptimal(String individual) {
		return fitness(individual) == (int)Math.floor((double)individual.length() / chunkSize);
	}

}
