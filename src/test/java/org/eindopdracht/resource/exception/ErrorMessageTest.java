package org.eindopdracht.resource.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ErrorMessageTest {
    private ErrorMessage error;

    @BeforeEach
    void setup() {
        this.error = new ErrorMessage("This is a test");
    }

    @Test
    void testGetErrorMessage() {
       assertEquals("This is a test", error.getError());
    }

    @Test
    void testIfErrorMessageHasBeenChanged() {
        this.error.setError("It changed");
        assertEquals("It changed", error.getError());
    }
}
