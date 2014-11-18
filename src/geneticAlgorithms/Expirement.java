package geneticAlgorithms;

public class Expirement {

	public static void main(java.lang.String[] args) {
		RoyalRoad road = new RoyalRoad(5);
		NumberOfOnes ones = new NumberOfOnes();
		int bestRoad = 0;
		int bestOnes = 0;
		int i = 0;
		for (i = 0; i < 500000; i++) {
			String individual = Individuals.random(500);
			bestRoad = road.fitness(individual) > bestRoad ? road
					.fitness(individual) : bestRoad;
			bestOnes = ones.fitness(individual) > bestOnes ? ones
					.fitness(individual) : bestOnes;
			// Both fitness functions have the same optimal string, so only
			// check one.
			if (ones.isOptimal(individual)) {
				break;
			}
		}
		System.out.println("Independent Generations:");
		System.out.println("	Individuals generated: " + i);
		System.out.println("	Best Royal Road fitness: " + bestRoad);
		System.out.println("	Best Number of Ones fitness: " + bestOnes);

		HillClimber hillRoad = new HillClimber(road, 500);
		HillClimber hillOnes = new HillClimber(ones, 500);
		bestRoad = 0;
		bestOnes = 0;
		int onesGenerated = 0;
		int roadGenerated = 0;
		for (i = 1; i < 500000; i++) {
			if (!ones.isOptimal(hillOnes.getBest())) {
				hillOnes.step();
				bestOnes = ones.fitness(hillOnes.getBest()) > bestOnes ? ones
						.fitness(hillOnes.getBest()) : bestOnes;
				onesGenerated = i + 1;
			}
			if (!road.isOptimal(hillRoad.getBest())) {
				hillRoad.step();
				bestRoad = road.fitness(hillRoad.getBest()) > bestRoad ? road
						.fitness(hillRoad.getBest()) : bestRoad;
				roadGenerated = i + 1;
			}
		}
		System.out.println("Hill Climbing:");
		System.out.println("	Ones individuals generated: " + onesGenerated);
		System.out.println("	Best Number of Ones fitness: " + bestOnes);
		System.out.println("	Road individuals generated: " + roadGenerated);
		System.out.println("	Best Royal Road fitness: " + bestRoad);

		Population popRoad = new Population(road, 1000, 500);
		Population popOnes = new Population(ones, 1000, 500);
		bestRoad = 0;
		bestOnes = 0;
		onesGenerated = 0;
		roadGenerated = 0;
		boolean onesDone = false;
		boolean roadDone = false;
		for (i = 1; i < 500000; i++) {

			if (i % 1000 == 0) {
				System.out.println(i);
			}
			if (!onesDone) {
				if (!(popOnes.bestFitness() == 500)) {
					if (i % 10 == 0) {
						System.out.println("evolve ones " + i);
					}
					popOnes.evolve();
					bestOnes = popOnes.bestFitness();
					onesGenerated = i + 1;
				} else {
					onesDone = true;
				}
			}
			if (!roadDone) {
				if (!(popRoad.bestFitness() == 100)) {
					if (i % 100 == 0) {
						System.out.println("evolve road " + i);
					}
					popRoad.evolve();
					bestRoad = popRoad.bestFitness();
					roadGenerated = i + 1;
				} else {
					roadDone = true;
				}
			}
		}
		System.out.println("Genetic Algorithm:");
		System.out.println("	Ones individuals generated: " + onesGenerated
				* 1000);
		System.out.println("	Best Number of Ones fitness: " + bestOnes);
		System.out.println("    Ones final average fitness: "
				+ popOnes.averageFitness());
		System.out.println("	Road individuals generated: " + roadGenerated
				* 1000);
		System.out.println("	Best Royal Road fitness: " + bestRoad);
		System.out.println("    Royal Road final average fitness: "
				+ popRoad.averageFitness());
	}

}
