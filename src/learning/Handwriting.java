package learning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Handwriting {

	public static void main(String[] args) {
		File file = new File("optdigits.tes");
		List<Point> data = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			String[] values = new String[65]; 
			double[] attributes = new double[64];
			
			// read lines from file
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				for (int i = 0; i < 64; i++) {
					attributes[i] = Double.parseDouble(values[i]);
				}			

				int classification = Integer.parseInt(values[64]);
				// change the value, 3 is 1 and all else are 0 
				if (classification == 3) {
					classification = 1;
				} else {
					classification = -1;
				}
				data.add(new Point(attributes, classification));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		PocketPerceptron perceptron = new PocketPerceptron(9);
		perceptron.train(data, 1000);
	}
	
}
