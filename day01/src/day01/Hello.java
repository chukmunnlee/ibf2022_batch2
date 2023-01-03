// The package name
package day01;

// Imports

// Write the program
public class Hello {

    // entry point
    public static void main(String[] args) {
        System.out.printf("hello, world\n");

        System.out.printf("args.length = %d\n", args.length);

        // Print the contents of args array
        for (int i = 0; i < args.length; i++) {
            System.out.printf(">>>> args[%d] = %s\n", i, args[i]);
        }
    }
}