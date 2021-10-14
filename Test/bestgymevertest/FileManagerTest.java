package bestgymevertest;

import bestgymever.Administration;
import bestgymever.Customer;
import bestgymever.FileManager;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void checkCustomer(){
        //Testing that the checkcustomer creates a customer from varied inputs
        assertNotNull(FileManager.checkCustomer("Chamade Coriola"));
        assertNotNull(FileManager.checkCustomer("8512021234"));
        assertNull(FileManager.checkCustomer(" Ida Idylle"));
        assertNull(FileManager.checkCustomer("8906138493, Ida Idylle"));

        //Creating customers to test assertequals on;
        LocalDate date = Administration.buildDate("2018-01-09");
        Customer customer1 = new Customer("4604151234", "Kadine Karlsson", date);
        Customer customer2 = FileManager.checkCustomer("4604151234");

        //Checking that the two supposedly identical customers ACTUALLY are identical
        assertEquals(customer1.getLastPayment(), customer2.getLastPayment());
        assertEquals(customer1.getName(), customer2.getName());
        assertEquals(customer1.getSocialSecurityNumber(), customer2.getSocialSecurityNumber());
    }

    @Test
    void registerVisit() throws IOException {
        LocalDate currentDate = LocalDate.now();
        LocalDate updateDate = currentDate.minusDays(4);

        BufferedReader reader = new BufferedReader(new FileReader("resources/customerVisit.txt"));
        String readLine;

        //***PART ONE***
        //Creating customer
        Customer customer = FileManager.checkCustomer("4604151234");

        //Trying to write a file with a customer who has not paid withing a year
        FileManager.registerVisit(customer);
        readLine = reader.readLine();
        //This should be null since we only register visits of paying customers
        assertNull(readLine);


        //***PART TWO***
        //Updating the customer as if they just renewed the payment
        //IMPORTANT!!
        //I use the current date -4 days to check that what is written to file isn't what's in the object
        customer.setLastPayment(updateDate);
        //Trying to register the visit again, since the customer is now supposedly paying
        FileManager.registerVisit(customer);

        //Checking that it actually wrote, and that it wrote the right things
        readLine = reader.readLine();
        assertTrue(readLine.contains("4604151234, Kadine Karlsson"));

        //Checking that the date written was actually the current date rather than last payment date
        readLine = reader.readLine();
        assertNotEquals(readLine, customer.getLastPayment().toString());
        assertEquals(readLine, currentDate.toString());

        reader.close();
    }

}