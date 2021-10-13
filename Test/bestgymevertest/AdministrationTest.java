package bestgymevertest;

import bestgymever.Administration;
import bestgymever.Customer;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AdministrationTest {

    LocalDate date = Administration.buildDate("2015-03-24");
    Customer customer = new Customer("7605021234", "Robin Stenskytt", date);

    @Test
    void buildDate() {
        assertNotNull(Administration.buildDate("2019-08-18"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d1 = Administration.buildDate("2018-03-27");
        LocalDate d2 = LocalDate.parse("2018-03-27", formatter);
        assertEquals(d1, d2);
    }

    @Test
    void hasPaidWithinYear(){
        assertEquals(customer.getLastPayment(), date);
        assertFalse(Administration.hasPaidWithinYear(customer));

        customer.setLastPayment(LocalDate.now());
        assertTrue(Administration.hasPaidWithinYear(customer));
    }
}
