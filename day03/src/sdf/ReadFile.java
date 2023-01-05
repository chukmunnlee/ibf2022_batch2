package sdf;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {

    public static void main(String[] args) {

        Path cithPath = Paths.get(args[0]);

        System.out.printf("file name: %s\n", cithPath);

        File cith = cithPath.toFile();

        System.out.printf("is directory: %b\n", cith.isDirectory());
        System.out.printf("is file: %b\n", cith.isFile());

        if (cith.isDirectory()) {
            System.out.println("Contents of directory");
            // for-each loop
            for (File file: cith.listFiles()) {
                System.out.printf("\t%s (%d)\n"
                        , file.getAbsolutePath(), file.length());
            }

        } else {
            System.out.printf("file size: %d\n", cith.length());
            System.out.printf("absolute path: %s\n", cith.getAbsolutePath());
        }
    }
    
}
