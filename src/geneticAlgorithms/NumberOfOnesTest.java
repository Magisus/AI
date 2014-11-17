package geneticAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumberOfOnesTest {

	private String a;
	private String b;
	private String c;
	private NumberOfOnes ones;
	
	@Before
	public void setUp() throws java.lang.Exception {
		ones = new NumberOfOnes();
		a = "111100011";
		b = "111111111";
		c = "000000000";
	}
	
	@Test
	public void testFitness(){
		assertEquals(6, ones.fitness(a));
		assertEquals(9, ones.fitness(b));
		assertEquals(0, ones.fitness(c));
	}
	
	@Test
	public void testIsOptimal(){
		assertTrue(ones.isOptimal(b));
		assertFalse(ones.isOptimal(a));
		assertFalse(ones.isOptimal(c));
	}

}
