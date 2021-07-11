package spring.onmaven.Models;

import java.util.ArrayList;
import java.util.List;

public class Pages {
	private int all;
	
	public int getAll() {
		
		return all;
	}
	public void setAll(int all) {
			
			this.all = all;
		}
	
	public static int getFrom(int x, int size) {
		
		return x*size-size;
	}
	public static int getTo(int x, int size) {
			
			return x*size;
		}
	
	private int pagesCount(int x, int size) {
		int rest = x%size>1? 1: 0;
		 
		return x/size+rest;
	}
	public List<Integer> PageList(int x, int size){
		 
		List<Integer> list = new ArrayList<>();
		for(int i =1; i <= pagesCount(x,size); i++){
	        list.add(i);
	    }
		return list;
	}
	public int PageAddOne(int page) {
		
		return ++page;
		
	}
	public int PageMinOne(int page) {
			
			return --page;
			
		}
}
