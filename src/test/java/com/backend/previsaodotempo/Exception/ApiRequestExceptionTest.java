package com.backend.previsaodotempo.Exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ApiRequestExceptionTest {

	 @Test
	    public void testConstructorWithMessage() {
	        String message = "Test message";
	        ApiRequestException exception = new ApiRequestException(message);

	        assertEquals(message, exception.getMessage());
	    }

	    @Test
	    public void testConstructorWithMessageAndCause() {
	        String message = "Test message";
	        Throwable cause = new RuntimeException("Test cause");
	        ApiRequestException exception = new ApiRequestException(message, cause);

	        assertEquals(message, exception.getMessage());
	        assertEquals(cause, exception.getCause());
	    }
	}
