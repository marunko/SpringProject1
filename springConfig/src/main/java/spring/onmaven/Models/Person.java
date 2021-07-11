package spring.onmaven.Models;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person
{
	
	private int id; 
	
	@Size(min = 2, max = 200, message 
		      = "About Me must be between 2 and 200 characters")
	private String name;
	
	@Min(value=0, message="from 0 to 100")
	private int age;
	

	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	public Person(int id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public int get_id() {
		return this.id;
	}
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
}
