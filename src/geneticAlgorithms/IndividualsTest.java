package geneticAlgorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IndividualsTest {
	
	@Before
	public void setUp() {
		
	}

	@Test
	public void testCross() {
		String a = Individuals.random(100);
		String b = Individuals.random(100);
		int different = 0;
		for(int i = 0; i < 100; i++){
			String child = Individuals.cross(a, b);
			if(!child.equals(a) && !child.equals(b)){
				different++;
			}
		}
		assertTrue(different > 90);
	}
	
	@Test
	public void testMutate() {
		String original = Individuals.random(100);
		String mutated = Individuals.mutate(original, 25);
		assertFalse(original.equals(mutated));		
	}
	
	@Test
	public void testRandom() {
		String bits = Individuals.random(100);
		assertEquals(100, bits.length());
		assertTrue(bits.contains("0"));
		assertTrue(bits.contains("1"));
	}

}
