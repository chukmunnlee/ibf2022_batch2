package sdf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) 
            throws FileNotFoundException, IOException {

        Reader r = new FileReader("googleplaystore.csv");
        BufferedReader br = new BufferedReader(r);

        // Discard the first line
        String input = br.readLine();
        String[] columns;

        while (null != (input = br.readLine())) {
            input = input.trim();
            // If the line is empty, discard it
            if (input.length() <= 0) {
                continue;
            }

            // Split the line by comma
            columns = input.split(",");

            // If the number of columns is less than 3, discard it
            if (columns.length < 3) {
                continue;
            }

            String name = columns[0];
            String category = columns[1];
            String rating = columns[2];
            // If the rating is not a number NaN, discard it
            if (rating.toLowerCase().equals("nan")) {
                continue;
            }

        }

        br.close();
        r.close();
    }

}