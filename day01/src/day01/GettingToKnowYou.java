package day01;

// Using the class 
import java.io.Console;

public class GettingToKnowYou {

    // entry point
    public static void main(String[] args) {

        // Read from the command line
        // Get the console 
        Console cons = System.console();

        // Prompt the user and read a line
        String name;
        name = cons.readLine("What is your name? ");

        // Prompt for the  mail
        String email = cons.readLine("What is your email? ");

        if (email.length() > 0) {
            // print the name and email 
            System.out.printf("Hello %s. Your email is %s\n", name, email);
        } else {
            // otherwise just print the name
            System.out.printf("Hello %s.\n", name);
        }

        // If the name is fred, print yabadabadoo!
        //if (name.equals("fred")) {
        if ("fred".equals(name)) {
            System.out.printf("Yabadabadoo\n");
        }
    }
    
}
