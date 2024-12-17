package com.demo;

import org.springframework.boot.actuate.health.Health; 
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;


@Controller
public class MyDBHealth implements HealthIndicator{
	
	public static final String DB_Service = "Databsee Service";
	
	public Boolean isHealthGood()
	{
		return true;
	}

	@Override
	public Health health() {
		
		if(isHealthGood())
		{
			return Health.up().withDetail(DB_Service, "Database Service is Running ").build();
		}
		
		
		return Health.down().withDetail(DB_Service, "Database Service is Running Down... ").build();
	}

	
	
}
