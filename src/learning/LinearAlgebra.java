package learning;

public class LinearAlgebra {

		public static double dotProduct(double[] a, double[] b) {
			double result = 0;
			return result;
		}
	
		public static double[] sum(double[] a, double[] b) {
			return new double[1];
		}
		
		public static double[] scale(double a, double[] b) {
			double[] bb = new double[b.length];
			for(int i = 0; i < b.length; i++){
				bb[i] = b[i] * a;
			}
			return bb;
		}
		
}
