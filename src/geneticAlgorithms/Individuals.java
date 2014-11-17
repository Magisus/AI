package geneticAlgorithms;

import edu.princeton.cs.introcs.StdRandom;

public class Individuals {

	/** Returns a new individual combining a and b by genetic crossover. */
	static String cross(String a, String b) {
		int split = StdRandom.uniform(a.length());
		String child = a.substring(0, split);
		child += b.substring(split);
		return child;
	}

	/**
	 * Returns a new individual: a copy of this one, possibly with some
	 * mutations.
	 * 
	 * @param rate
	 *            The expected number of mutations. This is divided by the
	 *            length of the individual to get the probability of a mutation
	 *            at each position.
	 */
	static String mutate(String individual, double rate) {
		char[] chars = individual.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (StdRandom.uniform() < (rate / individual.length())) {
				chars[i] = chars[i] == '1' ? '0' : '1';
			}
		}
		return new String(chars);
	}

	/** Returns a random individual of the specified length */
	static String random(int length) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < length; i++) {
			string.append(StdRandom.uniform(2));
		}
		return string.toString();
	}

}
