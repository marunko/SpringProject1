package spring.onmaven.Models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Comments {
	private int id;
	public static int count;
	private int counter;
	private String title;
	private String text;
	private String author;
	private Date date;
	 
	private int person_id;
	 
	public Comments(){
		 
		 
	}
	 
	public int getCount() {
		return counter;
	}
	public void setCount(int counter) {
		this.counter = counter;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getAuth() {
		return author;
	}
	public void setAuth(String author) {
		this.author = author;
	}
	
	public int getPerson_id() {
		return person_id;
	}
	
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(String str) {
		// TODO Auto-generated method stub
		this.date = Date.valueOf(str);
	}
	public void setCurrentDate() {
		// TODO Auto-generated method stub
		long now = System.currentTimeMillis();
		this.date = new Date(now);
	}
	
}
