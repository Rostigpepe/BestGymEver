package bestgymever;

import java.util.Scanner;

public class Program {
    //Private constructor for utility class
    private Program(){}

    static Scanner uInput = new Scanner(System.in);
    static boolean run = true;


    public static void program(){
        while (run){
            optionDisplay();
        }

    }


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

    public static void optionCheckCustomer(){
        Customer customer = optionGetCustomer();

        if(customer != null){
            customer.printAll();
            return;
        }
        System.out.println("This customer does not exist\n");
    }

    public static void optionRegisterVisit(){
        Customer customer = optionGetCustomer();

        FileManager.registerVisit(customer);
    }

    public static void optionExit(){
        run = false;
    }

    public static Customer optionGetCustomer(){

        System.out.println("Please enter the Social Security Number or Name");
        System.out.print(">> ");

        String input = uInput.nextLine();
        return FileManager.checkCustomer(input);
    }
}
