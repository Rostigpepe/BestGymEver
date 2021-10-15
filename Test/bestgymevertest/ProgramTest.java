package bestgymevertest;

import bestgymever.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void optionGetCustomer(){
        //AssertNulls to be certain that you cannot enter both the SSN and name, and still get a valid return
        assertNull(Program.optionGetCustomer("7605021234, Elmer Ekorrsson"));
        assertNull(Program.optionGetCustomer("021234, Elmer Ek"));
        assertNull(Program.optionGetCustomer("7605021234, "));

        //AssertNotNulls to assure that we get a proper return if we enter a correct ssn or name
        //Also to assure that leading and following blank space do not matter
        assertNotNull(Program.optionGetCustomer("7605021234"));
        assertNotNull(Program.optionGetCustomer("    Fritjoff Flacon     "));
        assertNotNull(Program.optionGetCustomer("Ida Idylle"));
    }
}
