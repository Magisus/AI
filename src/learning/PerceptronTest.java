package learning;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

public class PerceptronTest {

	private Perceptron perceptron;
	
	private List<Point> data;
	
	@Before
	public void setUp() throws Exception {
		data = new ArrayList<>();
		data.add(new Point(new double[] {0, 0}, -1));
		data.add(new Point(new double[] {0, 1}, -1));
		data.add(new Point(new double[] {1, 0}, -1));
		data.add(new Point(new double[] {1, 1}, 1));
	}

	@Test
	public void testClassify() {
		perceptron = new Perceptron(new double[] {-1.5, 1, 1});
		for (Point p : data) {
			assertEquals(p.getClassification(), perceptron.classify(p), 0.001);
		}
	}

	@Test
	public void testTest() {
		perceptron = new Perceptron(new double[] {-1.5, 1, 1});
		assertEquals(0.0, perceptron.error(data), 0.001);
		perceptron = new Perceptron(new double[] {-.5, 1, 1});
		assertEquals(0.5, perceptron.error(data), 0.001);		
	}

	@Test
	public void testUpdateSpecificPoint() {
		perceptron = new Perceptron(new double[] {-1.5, 1, 1});
		Point p = new Point(new double[] {0, 1}, 1);
		perceptron.update(p);
		assertEquals(1, perceptron.classify(p));
	}
	
	@Test
	public void testUpdateMisclassifiedPoint() {
		perceptron = new Perceptron(2);
		double errorBefore = perceptron.error(data);
		perceptron.update(data);
		assertTrue(perceptron.error(data) < errorBefore);
	}
	
	@Test
	public void testTrain() {
		perceptron = new Perceptron(2);
		perceptron.train(data);
		assertEquals(0.0, perceptron.error(data), 0.001);
	}
	
	@Test
	public void testTrainLimitedUpdates() {
		perceptron = new Perceptron(2);
		perceptron.train(data, 10);
		assertTrue(perceptron.error(data) > 0);
		perceptron.train(data, 100);
		assertEquals(0.0, perceptron.error(data), 0.001);
	}

}
