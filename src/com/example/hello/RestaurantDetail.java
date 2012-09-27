package com.example.hello;

public class RestaurantDetail {
	
	private int num;
	private String name, time, phone, address, describe;

	public RestaurantDetail(String name, int num, String time, String phone, String address, String describe) {
		// TODO Auto-generated constructor stub
		super();
		this.num = num;
		this.name = name;
		this.time = time;
		this.phone = phone;
		this.address = address;
		this.describe = describe;
	}
	
	public String getMessage(int i){
		String result = "";
		if(i==0)
			result = this.name;
		else if(i==1)
			result = Integer.toString(num);
		else if(i==2)
			result = this.time;
		else if(i==3)
			result = this.address;
		else if(i==4)
			result = this.phone;
		else if(i==5)
			result = this.describe;
		return result;
	}
	
}
