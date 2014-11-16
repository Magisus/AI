package geneticAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoyalRoadTest {

	private String a;
	private String b;
	private String c;
	private RoyalRoad road;
	
	@Before
	public void setUp() throws Exception {
		road = new RoyalRoad(3);
		a = "11101011011101";
		b = "000000000000";
		c = "11111111111101";
	}

	@Test
	public void testFitness() {
		assertEquals(2, road.fitness(a));
		assertEquals(0, road.fitness(b));
		assertEquals(4, road.fitness(c));
	}

	@Test
	public void testIsOptimal() {
		assertTrue(road.isOptimal(c));
		assertFalse(road.isOptimal(b));
		assertFalse(road.isOptimal(a));
	}

}
