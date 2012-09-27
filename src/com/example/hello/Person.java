package com.example.hello;

public class Person {
	
	private int num;
	private String name;

	public Person(int num, String name) {
		// TODO Auto-generated constructor stub
		super();
		this.num = num;
		this.name = name;
	}
	
	public String getNum(){
		return Integer.toString(num);
	}
	
	public String getName(){
		return this.name;
	}

}
