package org.AdvancedJavaEindopdracht.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ErrorMessageTest {
    private ErrorMessage error;


    @BeforeEach
    public void setup() {
        this.error = new ErrorMessage("This is a test");
    }

    @Test
    public void testGetErrorMessage() {
       assertEquals("This is a test", error.getError());
    }

    @Test
    public void testIfErrorMessageHasBeenChanged() {
        this.error.setError("It changed");
        assertEquals("It changed", error.getError());
    }
}
