package com.demo;


import org.springframework.data.annotation.Id;  
import org.springframework.data.mongodb.core.mapping.Document;




@Document("Converted")
public class Driver {

	
	@Id
    private String name;
    private String salary;
    private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Driver(String name, String salary, String sex) {
		super();
		this.name = name;
		this.salary = salary;
		this.sex = sex;
	}
	public Driver() {
		super();
	}
    
    

    
    
}
