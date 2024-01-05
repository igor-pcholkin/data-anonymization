package com.discreet.datamasking.anonymizer;

import com.discreet.datamasking.anonymizer.name.FullNameMildAnonymizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FullNameMildAnonymizerTest {
    FullNameMildAnonymizer anonymizer = new FullNameMildAnonymizer();

    @Test
    public void testNameOnly() {
        String input = "John";
        String output = anonymizer.anonymize(input);

        assertNotEquals (output, input);
        assertEquals(input.length(), output.length());
        assertTrue(output.matches("J[a-z]*"));
    }

    @Test
    public void testNamePlusLastName() {
        String input = "John Smith";
        String output = anonymizer.anonymize(input);

        assertNotEquals (output, input);
        assertEquals(input.length(), output.length());
        assertTrue(output.matches("J[a-z]* S[a-z]*"));
    }

    @Test
    public void testNamePlusLastNamePlusMiddleName() {
        String input = "John Paul Smith";
        String output = anonymizer.anonymize(input);

        assertNotEquals (output, input);
        assertEquals(input.length(), output.length());
        assertTrue(output.matches("J[a-z]* P[a-z]* S[a-z]*"));
    }

    @Test
    public void testFullNameWithCaseCorrection() {
        String input = "JOHN paul sMITH";
        String output = anonymizer.anonymize(input);

        assertNotEquals (output, input);
        assertEquals(input.length(), output.length());
        assertTrue(output.matches("J[a-z]* P[a-z]* S[a-z]*"));
    }

    @Test
    public void testInvalidFullName() {
        String input = ",John123Paul Smith";
        String output = anonymizer.anonymize(input);

        assertNotEquals (output, input);
        assertEquals(input.length(), output.length());
        assertTrue(output.matches("[A-Za-z]* S[a-z]*"));
    }
}
