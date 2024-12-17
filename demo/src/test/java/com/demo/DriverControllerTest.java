package com.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    private Driver driver;
    
    @BeforeEach
    void setUp() {
        driver = new Driver("John Doe", "5000", "Male");
    }

    @Test
    void testCreateDriver() throws Exception {
        when(driverService.createUser(any(Driver.class))).thenReturn(driver);

        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(driver)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.salary").value("5000"))
                .andExpect(jsonPath("$.sex").value("Male"));

        verify(driverService, times(1)).createUser(any(Driver.class));
    }

    @Test
    void testGetAllDrivers() throws Exception {
        when(driverService.getAllUsers()).thenReturn(Arrays.asList(driver));

        mockMvc.perform(get("/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        verify(driverService, times(1)).getAllUsers();
    }

    
    @Test
    void testGetDriver() throws Exception {
        when(driverService.getUser("John Doe")).thenReturn(Optional.of(driver));

        mockMvc.perform(get("/John Doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(driverService, times(1)).getUser("John Doe");
    }

    @Test
    void testDeleteDriver_UserExists() throws Exception {
        when(driverService.deleteUser("John Doe")).thenReturn("User Deleted Succesfully!");

        mockMvc.perform(delete("/John Doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User Deleted Succesfully!"));

        verify(driverService, times(1)).deleteUser("John Doe");
    }

    @Test
    void testDeleteDriver_UserNotFound() throws Exception {
        when(driverService.deleteUser("1")).thenReturn("User Not Found!");

        mockMvc.perform(delete("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User Not Found!"));

        verify(driverService, times(1)).deleteUser("1");
    }
    
    
}

