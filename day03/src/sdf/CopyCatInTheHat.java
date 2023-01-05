package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyCatInTheHat {

    public static void main(String[] args) throws IOException {

        // Read the file the_cat_in_the_hat
        Path cithPath = Paths.get("./cat_in_the_hat.txt");
        File cith = cithPath.toFile();

        if (!cith.exists()) {
            System.err.println("Cannot file file");
            System.exit(1);
        }

        FileReader fr = new FileReader(cith);
        BufferedReader br = new BufferedReader(fr);

        FileWriter writer = new FileWriter("CAT_IN_THE_HAT.txt");
        String line;

        while (null != (line = br.readLine())) {
            writer.write(line.toUpperCase());
            writer.write("\n");
        }

        // Close the reader
        br.close();
        fr.close();

        // Close the writer
        writer.flush();
        writer.close();
    }
    
}
