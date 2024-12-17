package com.demo;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@SpringBootTest
class DriverServiceTest {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepo;

    private Driver driver;

    @BeforeEach		//that means before running every single test case this setup() will execute with values again&again 
    void setUp() {
        MockitoAnnotations.openMocks(this);
        driver = new Driver();
       
        driver.setName("John Doe");
        driver.setSalary("5000");
        driver.setSex("Male");
    }

    @Test
    void testCreateUser() throws Exception {
        when(driverRepo.save(any(Driver.class))).thenReturn(driver);

        Driver createdDriver = driverService.createUser(driver);

        assertNotNull(createdDriver);
        assertEquals("John Doe", createdDriver.getName());
        verify(driverRepo, times(1)).save(driver);
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(driverRepo.findAll()).thenReturn(Arrays.asList(driver));

        var drivers = driverService.getAllUsers();

        assertNotNull(drivers);
        assertEquals(1, drivers.size());
        assertEquals("5000", drivers.get(0).getSalary());
        verify(driverRepo, times(1)).findAll();
    }

    @Test
    void testGetUser() throws Exception {
        when(driverRepo.findById("John Doe")).thenReturn(Optional.of(driver));

        Optional<Driver> foundDriver = driverService.getUser("John Doe");

        assertTrue(foundDriver.isPresent());
        assertEquals("Male", foundDriver.get().getSex());
        verify(driverRepo, times(1)).findById("John Doe");
    }

//    @Test
//    void testDeleteUser() throws Exception {
// 	   
// 		doNothing().when(driverRepo).deleteById("John Doe");
//
//        String response = driverService.deleteUser("John Doe");
//
//        assertEquals("User Deleted Succesfully!", response);
//        verify(driverRepo, times(1)).deleteById("John Doe");
//
//    }
    
    @Test
    void testDeleteUser_UserExists() throws Exception {
        // Arrange
        when(driverRepo.findById("John Doe")).thenReturn(Optional.of(driver));
        doNothing().when(driverRepo).deleteById("John Doe");

        // Act
        String response = driverService.deleteUser("John Doe");

        // Assert
        assertEquals("User Deleted Succesfully!", response);
        verify(driverRepo, times(1)).findById("John Doe");
        verify(driverRepo, times(1)).deleteById("John Doe");
    }

    @Test
    void testDeleteUser_UserNotFound() throws Exception {
        // Arrange
        when(driverRepo.findById("1")).thenReturn(Optional.empty());

        // Act
        String response = driverService.deleteUser("1");

        // Assert
        assertEquals("User Not Found!", response);
        verify(driverRepo, times(1)).findById("1");
        verify(driverRepo, never()).deleteById("1");
    }
}
