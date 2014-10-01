package learning;

import static org.junit.Assert.*;
import static learning.LinearAlgebra.*;
import org.junit.Before;
import org.junit.Test;

public class LinearAlgebraTest {

	private double[] a;
	
	private double[] b;
	
	@Before
	public void setUp() throws Exception {
		a = new double[] {1, 2, 3};
		b = new double[] {4, 5, 6};
	}

	@Test
	public void testDotProduct() {
		assertEquals(32.0, dotProduct(a, b), 0.001);
	}

	@Test
	public void testSum() {
		assertArrayEquals(new double[] {5, 7, 9}, sum(a, b), 0.001);
	}

	@Test
	public void testScale() {
		assertArrayEquals(new double[] {3, 6, 9}, scale(3, a), 0.001);
	}

}
