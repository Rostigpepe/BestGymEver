package bestgymever;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Administration {
    //Preventing instances of a utility class
    private Administration(){}


    public static LocalDate buildDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //It parses the date string to date using this specific format, since that's how it looks in the txt file
        return LocalDate.parse(date, formatter);
    }

    public static boolean hasPaidWithinYear(Customer customer){
        //Getting the current date
        LocalDate currentDate = LocalDate.now();
        LocalDate paymentDate = customer.getLastPayment();

        //Getting the time between these two
        long years = ChronoUnit.YEARS.between(paymentDate, currentDate);

        //Returns true if the difference between the two dates is less than 1
        //Aka, if there's been less than a year since the customer paid
        return years < 1;
    }
}
