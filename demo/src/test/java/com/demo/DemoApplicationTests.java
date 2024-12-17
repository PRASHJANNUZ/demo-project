package com.demo;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.system.CapturedOutput;



@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class DemoApplicationTests {

	
	
	@Test
    void contextLoads() {
        // Verifies that the Spring application context loads successfully
        //assertDoesNotThrow(() -> DemoApplication.main(new String[]{}));
    }

    @Test
    void testLoggerOutput(CapturedOutput output) {
        // Run the application
        DemoApplication.main(new String[]{});

        // Verify logger output contains the expected message
        assertTrue(output.getOut().contains("DemoApplication started.."));
    }

}
