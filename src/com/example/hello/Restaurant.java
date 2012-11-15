package com.example.hello;

public class Restaurant {
	
	private int num;
	private String name;
	private String time;
	private int rid;

	public Restaurant(int num, String name, int rid, String time) {
		// TODO Auto-generated constructor stub
		super();
		this.num = num;
		this.name = name;
		this.rid = rid;
		this.time = time;
	}
	
	public String getNum(){
		return Integer.toString(num);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getRid(){
		return Integer.toString(this.rid);
	}
	
	public String getTime(){
		return this.time;
	}

}
