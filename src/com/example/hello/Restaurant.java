package com.example.hello;

public class Restaurant {
	
	private int num;
	private String name;
	private int rid;

	public Restaurant(int num, String name, int rid) {
		// TODO Auto-generated constructor stub
		super();
		this.num = num;
		this.name = name;
		this.rid = rid;
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

}
