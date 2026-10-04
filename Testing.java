// Katharine Leaver

import java.util.Scanner;

public class Testing { // Testing class!

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        boolean valid = false;
        int number = 0;

        @SuppressWarnings("unused")
        Recursive recursive;

        // the below code has to run once outside of the loop to allow the user to exit
        // as soon as they enter "-1", since it has to prompt at the end of the loop
        // to avoid the whole thing running one more time after -1 is entered.

        while (!valid) {

            try {
                System.out.println("Please enter the binary number (starting with 1) to encode, or enter -1 to stop: ");
                // NOTE THAT RIGHT NOW THE BINARY NUMBER MUST START WITH 1 FOR THIS TO WORK AT ALL.
                // I haven't found a way around this without using String / char instead of Math to figure out the "length" or # of digits
                number = scnr.nextInt(); // should trigger Exception here if the input isn't valid for any reason.
                valid = validateInput(number); // if the Exception is not triggered, the input is validated and this should break the loop if it is valid.
            }
            
            catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }

        }
        

        while (number != -1) { // runs as many times as the user wants until -1 is entered

            recursive = new Recursive(number);

            // below code runs the loop again.

            valid = false;
            number = 0;

            while (!valid) {

                try {
                    System.out.println("Please enter the binary number to encode, or enter -1 to stop: ");
                    number = scnr.nextInt(); // should trigger Exception here if the input isn't valid for any reason.
                    valid = validateInput(number); // if the Exception is not triggered, the input is validated and this should break the loop if it is valid.
                }
                
                catch (Exception e) {
                    System.out.println("Invalid input, please try again.");
                }
    
            }

        }

        scnr.close();
        System.exit(0);

    }

    private static boolean validateInput(int input) {

        // negative input

        if (input < 0) {
            System.out.println("Invalid input (NEGATIVE), please try again.");
            return false;
        }

        // even input
        
        int mult = 1;
        int temp = input;
        while (temp > 9) {
            temp = (temp - (int)(Math.pow(10, mult)));
            mult++;
        }

        // note that the value of "mult" here is functionally the same as the number of digits!
        
        if ((mult % 2) == 0) { 
            System.out.println("Invalid input (EVEN), please try again.");
            return false;
        }

        // is nonbinary ...........

        // I couldn't figure out a good way of doing this without using char to validate it
        // and a method that is part of String ... but it is stored as a char array so I think that's allowed?

        // only thing I can think of is making this method recursive as well to run it once for every single digit
        // but I don't know if that's necessary

        for (int i = 0; i < mult; i++) {
            char c = String.valueOf(input).charAt(i);
            
            if ((c != '1') && (c != '0')) {
                System.out.println("Invalid input (NOT A BINARY NUMBER), please try again.");
                return false;
            }
        }

        return true;

    }

}