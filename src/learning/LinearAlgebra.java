package learning;

public class LinearAlgebra {

	public static double dotProduct(double[] a, double[] b) {
		double result = 0;
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		return result;
	}

	public static double[] sum(double[] a, double[] b) {
		double sum[] = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			sum[i] = a[i] + b[i];
		}
		return sum;
	}

	public static double[] scale(double a, double[] b) {
		double[] bb = new double[b.length];
		for (int i = 0; i < b.length; i++) {
			bb[i] = b[i] * a;
		}
		return bb;
	}

}
