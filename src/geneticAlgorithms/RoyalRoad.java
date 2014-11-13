package geneticAlgorithms;

public class RoyalRoad extends java.lang.Object implements FitnessFunction {

	private final int chunkSize = 0;

	/**
	 * @param chunkSize
	 *            Length of each chunk.
	 */
	public RoyalRoad(int chunkSize) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Fitness is the number of chunks that are all ones. For example, if chunk
	 * size is 3, one point of fitness is granted if all of the first three bits
	 * are ones, one if all of the next three bits are ones, etc.
	 */
	@Override
	public int fitness(String individual) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOptimal(String individual) {
		// TODO Auto-generated method stub
		return false;
	}

}
