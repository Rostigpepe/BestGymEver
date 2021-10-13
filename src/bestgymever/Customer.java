package bestgymever;

import java.time.LocalDate;

public class Customer {
    private final String socialSecurityNumber;
    private final String name;
    private LocalDate lastPayment;

    public Customer(String ssn, String name, LocalDate date){
        socialSecurityNumber = ssn;
        this.name = name;
        lastPayment = date;
    }


    public String getName() {
        return name;
    }
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public LocalDate getLastPayment() {
        return lastPayment;
    }


    public void setLastPayment(LocalDate lastPayment) {
        this.lastPayment = lastPayment;
    }


    public void printAll(){
        System.out.println("Name: " + name);
        System.out.println("Social Security Number: " + socialSecurityNumber);

        if(Administration.hasPaidWithinYear(this)){
            System.out.println("Active member: Yes\n");
            return;
        }
        System.out.println("Active member: No\n");
    }
}
