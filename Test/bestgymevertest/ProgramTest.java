package bestgymevertest;

import bestgymever.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void optionGetCustomer(){
        assertNull(Program.optionGetCustomer("7605021234, Elmer Ekorrsson"));
        assertNull(Program.optionGetCustomer("021234, Elmer Ek"));
        assertNull(Program.optionGetCustomer("7605021234, "));
        assertNotNull(Program.optionGetCustomer("7605021234"));
        assertNotNull(Program.optionGetCustomer("    Fritjoff Flacon     "));
        assertNotNull(Program.optionGetCustomer("Ida Idylle"));
    }



}
