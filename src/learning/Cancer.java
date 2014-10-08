package learning;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cancer {

	
	public static void main(String[] args) {
		File file = new File("breast-cancer-wisconsin.data");
		List<Point> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String line;
			String[] values = new String[11]; 
			double[] attributes = new double[9];
			boolean valid = true;
			
			// read lines from file
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				// start at 1 because the 0 attribute is useless id number
				for (int i = 1; i < 10; i++) {
					if (values[i].equals("?")) { // do not add this data point if it is missing any field
						valid = false; 
						break; 
						}
					attributes[i-1] = Double.parseDouble(values[i]);
				}			
				if (valid) { 
					int classification = Integer.parseInt(values[10]);
					// change the value, 2 to 1 and 4 to -1
					if (classification == 2) {
						classification = 1;
					} else {
						classification = -1;
					}
					data.add(new Point(attributes, classification));
				}
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Perceptron perceptron = new Perceptron(9);
		perceptron.train(data, 1000);
		
	}
}
