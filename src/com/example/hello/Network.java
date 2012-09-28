package com.example.hello;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Network {
	
	public static List<Restaurant> getJOSN1(String path) throws Exception{
		List<Restaurant> mlist = new ArrayList<Restaurant>();
		String json = jsonString(path);
		JSONArray array = new JSONArray(json);
				
		for(int i = 0;i<array.length();i++){
			JSONObject item = array.getJSONObject(i);
			String name = item.getString("name");
			int num = item.getInt("num");
			int rid = item.getInt("rid");
			mlist.add(new Restaurant(num, name, rid));
		}
		return mlist;
	}
	
	public static List<RestaurantDetail> getJOSN2(String path) throws Exception{
		List<RestaurantDetail> mlist = new ArrayList<RestaurantDetail>();
		String json = jsonString(path);
		JSONArray array = new JSONArray(json);
		
		for(int i = 0;i<array.length();i++){
			JSONObject item = array.getJSONObject(i);
			String name = item.getString("name");
			int num = item.getInt("num");
			String time = item.getString("time");
			String phone = item.getString("phone");
			String address = item.getString("address");
			String describe = item.getString("describe");
			mlist.add(new RestaurantDetail(name, num, time, phone, address, describe));
			}
		return mlist;
	}

	public static String jsonString(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setReadTimeout(6*1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		byte[] data = readInputStream(inStream);
		String json = new String(data);
		return json;
	}

//	-------------------------------------------------------------------------------------
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int len = 0;
		while((len = inStream.read(buff))!=-1){
			outStream.write(buff, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	public void postJSON(String path,String phone_num,int num_peo) throws Exception{
		URL url = new URL(path);
		JSONObject Json = new JSONObject();
		Json.put("phone", phone_num);
		Json.put("number", num_peo);
		String jsonString = String.valueOf(Json);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(6*1000);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		OutputStream outStream = conn.getOutputStream();
		outStream.write(jsonString.getBytes());
		outStream.close();
		}

	//---------------------------------------------------------------------------------------
	
	public static void postit(String path) throws Exception{
		HttpGet request = new HttpGet(path);
		HttpResponse response = (HttpResponse) new DefaultHttpClient().execute(request);
		if(response.getStatusLine().getStatusCode() == 200){
		}
	}

}
