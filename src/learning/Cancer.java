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
			double[] attributes = new double[10];
			boolean valid = true;
			
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				for (int i = 0; i < 10; i++) {
					if (values[i].equals("?")) { 
						valid = false; 
						break; 
						}
					attributes[i] = Double.parseDouble(values[i]);
				}			
				if (valid) {
					int classification = Integer.parseInt(values[10]);
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
		
		Perceptron perceptron = new Perceptron(10);
		perceptron.train(data, 100);
		
	}
}
