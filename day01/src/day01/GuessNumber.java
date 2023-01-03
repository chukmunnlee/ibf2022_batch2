package day01;

import java.io.Console;
import java.security.SecureRandom;
import java.util.Random;

public class GuessNumber {

    // entry point
    public static void main(String[] args) {

        // Create a random number generator
        Random rand = new SecureRandom();

        // Generate a number between 1 and 10
        int toGuess = rand.nextInt(10) + 1;

        System.out.printf("toGuess: %d\n", toGuess);

        Console cons = System.console();

        // number of tries
        Integer tries = 3;
        Boolean win = false;

        // Go into the main loop
        while ((tries > 0) && (!win)) {
            String input = cons.readLine("What is your guess (%d)\n", tries);
            Integer num = Integer.parseInt(input.trim());

            if (num > toGuess) {
                System.out.println("Lower");
            } else if (num < toGuess) {
                System.out.println("Higher");
            } else {
                System.out.println("Correct! You win!");
                win = true;
            }
            tries = tries - 1;
        }

        if (!win) {
            System.out.printf("The number is %d. You lose!", toGuess);
        }
    }
    
}
