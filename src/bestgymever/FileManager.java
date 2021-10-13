package bestgymever;

import java.io.*;
import java.time.LocalDate;

public class FileManager {
    //Preventing instances of a utility class
    private  FileManager(){}


    public static Customer checkCustomer(String input){
        String tempLine;

        try(BufferedReader reader = new BufferedReader(new FileReader("resources/customers.txt"))){
            while((tempLine = reader.readLine()) != null){

                //Instead of separating name and ssn, I check if the line contains what we're looking for instead.
                if(tempLine.contains(input)){

                    //Splitting the ssn and name apart, assigning them to separate Strings
                    String[] splitLine = tempLine.split(",");

                    //Variables for creating a customer
                    //Since the ssn always comes before the name, we can assume that index 0 will be ssn etc
                    String ssn = splitLine[0];
                    String name = splitLine[1].trim();

                    //Now we need the date, I'll use a method created in Administration to accomplish this
                    tempLine = reader.readLine();
                    LocalDate date = Administration.buildDate(tempLine);

                    //Now we will have to check if that returned a valid value
                    //If it does, we'll create a customer instance
                    if (date != null){
                        //Closing the reader is handled by try-with-resources
                        return new Customer(ssn, name, date);
                    }
                    break;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void registerVisit(Customer customer){

        if(Administration.hasPaidWithinYear(customer)){
            //Add ", true" after file path if you don't want to overwrite
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/customerVisit.txt"))){

                LocalDate currentDate = LocalDate.now();
                String information = customer.getSocialSecurityNumber() + ", " + customer.getName();
                String checkInDate = currentDate.toString();

                writer.write(information);
                writer.newLine();
                writer.write(checkInDate);
                writer.newLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Successfully registered\n");
            return;
        }
        System.out.println("Customer does not currently pay\n");
    }
}
