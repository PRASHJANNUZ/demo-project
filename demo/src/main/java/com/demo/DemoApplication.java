package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {


		    public static void main(String[] args) {
		    		SpringApplication.run(DemoApplication.class, args);
		    		Logger logger= LogManager.getLogger(DemoApplication.class);
		    		logger.info("DemoApplication started..");
		    			
	}

}
