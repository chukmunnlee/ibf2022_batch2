package mat;

import java.io.Console;

public class DotProduct {

	public static void main(String[] args) {

		int rows = Integer.parseInt(args[0]);
		int cols = Integer.parseInt(args[1]);

		// Array for the data
		int[][] data = new int[rows][cols];
		// Array for the weights
		int[] weights = new int[cols];
		// Array for the result of the dot product
		int[] result = new int[rows];

		Console cons = System.console();
		String input;
		String[] values;

		// Read the data
		for (int i = 0; i < rows; i++) {
			input = cons.readLine("Row %d, %d values: ", i, cols);
			values = input.trim().split(" ");
			for (int j = 0; j < cols; j++)
				data[i][j] = Integer.parseInt(values[j]);
		}

		// Read the weights
		input = cons.readLine("Weights: %d: ", cols);
		values = input.trim().split(" ");
		for (int i = 0; i < cols; i++)
				weights[i] = Integer.parseInt(values[i]);

		// Perform the dot product
		for (int i = 0; i < rows; i++) {
			int total = 0;
			for (int j = 0; j < cols; j++)
				total = total + (data[i][j] * weights[j]);
			result[i] = total;
		}

		// Print the result of the dot product
		for (int i = 0; i < rows; i++)
			System.out.printf("> %d\n", result[i]);

	}
}
