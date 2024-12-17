package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.actuate.health.Health;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorEndpointTest {

	private MyDBHealth healthCheck;

    @BeforeEach
    void setUp() {
        healthCheck = spy(new MyDBHealth());
    }

    @Test
    void testHealthUp() {
        // Mock IsHealthGood to return true
        doReturn(true).when(healthCheck).isHealthGood();

        // Call the health method
        Health health = healthCheck.health();

        // Verify the health status is UP
        assertEquals(Health.up().withDetail("Databsee Service", "Database Service is Running ").build(), health);
    }

    @Test
    void testHealthDown() {
        // Mock IsHealthGood to return false
        doReturn(false).when(healthCheck).isHealthGood();

        // Call the health method
        Health health = healthCheck.health();

        // Verify the health status is DOWN
        assertEquals(Health.down().withDetail("Databsee Service", "Database Service is Running Down... ").build(), health);
    }
}