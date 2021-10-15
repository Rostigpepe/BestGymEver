package bestgymever;

import java.util.Scanner;

public class Program {
    //Private constructor for utility class
    private Program(){}

    static Scanner uInput = new Scanner(System.in);
    static boolean run = true;


    public static void program(){
        while (run) {
            optionDisplay();
        }
    }

    //Quick switch statement to check what input the user gives, calls the correct option method thereafter
    //If no correct input was given, the method simply asks the user for correct input and calls itself again
    public static void optionDisplay(){
        System.out.println("1: Check customer status");
        System.out.println("2: Register customer visit");
        System.out.println("3: Exit program");
        System.out.print(">> ");

        String input = uInput.nextLine();

        switch (input){
            case "1" -> optionCheckCustomer();
            case "2" -> optionRegisterVisit();
            case "3" -> optionExit();
            default -> {
                System.out.println("Please enter 1, 2, or 3\n");
                optionDisplay();
            }
        }
    }

    //Calls getCustomer to create customer, if the returned customer is null they do not exist
    //If the returned customer does indeed exist, we can use the customer classes inbuilt method to print all info
    public static void optionCheckCustomer(){
        Customer customer = optionGetCustomer(getInput());

        if(customer != null){
            customer.printAll();
            return;
        }
        System.out.println("This customer does not exist\n");
    }

    //Calls getCustomer to create customer, if the returned customer is null they do not exist
    //If the returned customer is not null the customer DOES exist, we can write them to file
    public static void optionRegisterVisit(){
        Customer customer = optionGetCustomer(getInput());

        if(customer != null){
            FileManager.registerVisit(customer);
            return;
        }
        System.out.println("This customer does not exist\n");
    }

    //If the user wants to exit the program, we simply exit out of the main loop
    public static void optionExit(){
        run = false;
    }

    public static Customer optionGetCustomer(String input){
        //Trimming the input string to remove and possible leading and ending blank spaces
        input = input.trim();
        //This was to get around a bug where, if you entered either " ", "," or ", " the method returned the first customer in the file
        if(input.length() <= 2){
            return null;
        }

        //Looking for a comma in the string input, and if one is detected, we assume there's a SSN and name
        if(input.contains(",")){
            System.out.println("Please only enter a Social Security Number or a Name");
            return null;
        }

        return FileManager.checkCustomer(input);
    }

    //Had to create this getInput method to test the above method
    //Otherwise I had to manually input something everytime, and automated tests are preferred
    public static String getInput(){
        System.out.println("Please enter the Social Security Number or Name");
        System.out.print(">> ");

        return uInput.nextLine();
    }
}
