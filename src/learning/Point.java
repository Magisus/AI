package learning;

import static java.lang.Math.*;
import static learning.LinearAlgebra.*;

/** A data point for machine learning. */
public class Point {

	/** Vector of attributes. This is augmented: element 0 is always 1. */
	private double[] attributes;

	/** 1 or -1. */
	private int classification;

	/**
	 * Produces a random point with each of two attributes (after the initial 1)
	 * randomly in [0.0, 0.1). The classification is determined by a perceptron
	 * with weights.
	 */
	public Point(double[] weights) {
		attributes = new double[3];
		attributes[0] = 1;
		attributes[1] = random();
		attributes[2] = random();
		classification = (int) signum(dotProduct(weights, attributes));
	}

	/**
	 * Produces a point with the specified attributes and classification. The
	 * attribute vector is augmented by adding a 1 at the beginning.
	 */
	public Point(double[] attributes, int classification) {
		this.attributes = new double[attributes.length + 1];
		this.attributes[0] = 1;
		for (int i = 0; i < attributes.length; i++) {
			this.attributes[i + 1] = attributes[i];
		}
		this.classification = classification;
	}

	/**
	 * Returns the atttributes of this point, including the bias attribute 1 at
	 * index 0.
	 */
	public double[] getAttributes() {
		return attributes;
	}

	/** Returns the classification of this point (1 ot -1). */
	public int getClassification() {
		return classification;
	}

}
