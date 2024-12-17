package com.demo;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
	
	@Autowired
	DriverRepository driverRepo;
	
	
	Driver createUser(Driver s)  {
		return driverRepo.save(s);
	}
	
    List<Driver> getAllUsers()  {
    	
		return driverRepo.findAll();	
    }
    
    Optional<Driver> getUser(String id) {
		return driverRepo.findById(id);
	}
    
   String deleteUser(String id) {
	   	
	   Optional<Driver> d= driverRepo.findById(id);
	   //If the ID is not found in Database deleteById() silently ignores. that's why we write this logic to return not found
	   if(!d.isEmpty())
	   {
		   driverRepo.deleteById(id);
		   return "User Deleted Succesfully!";
	   }
	   return "User Not Found!";
		 
	}
   
}

