package com.demo;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {
	
    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver)  {
      
            return ResponseEntity.ok(driverService.createUser(driver));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Driver>> geAllDriver()  {
        
        return ResponseEntity.ok(driverService.getAllUsers());
    } 
    
    @GetMapping("/{name}")
    public ResponseEntity<Optional<Driver>> getDriver(@PathVariable String name)  {
    	
        return ResponseEntity.ok(driverService.getUser(name));
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String id)  {
    		
    		return ResponseEntity.ok(driverService.deleteUser(id));
    } 
    
    
}


