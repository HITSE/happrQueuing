package com.example.hello;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Network1 {
	
	public String getString() throws IOException{
		String strRet = "";
    	String url =
    			"http://www.dubblogs.cc:8751/Android/Test/API/TestSpinner/spinner1.php";
    	try{
    		strRet = getMethod(url,"utf-8");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return strRet;
	}
	
	public String getMethod(String strGetURL, String strEncoding){
    	String strReturn = "";
    	try{
    		HttpURLConnection urlConnection = null;
    		URL url = new URL(strGetURL);
    		urlConnection = (HttpURLConnection)url.openConnection();
    		urlConnection.connect();
    		InputStream htmlBODY = urlConnection.getInputStream();
    		if(htmlBODY != null){
    			int leng = 0;
    			byte[] Data = new byte[100];
    			byte[] totalData = new byte[0];
    			int totalLeg = 0;
    			
    			do{
    				leng = htmlBODY.read(Data);
    				if(leng>0){
    					totalLeg += leng;
    					byte[] temp = new byte[totalLeg];
    					System.arraycopy(totalData,0,temp,0,totalData.length);
    					System.arraycopy(Data, 0, temp, totalData.length, leng);
    					totalData = temp;
    				}
    			}while(leng>0);
    			
    			strReturn = new String(totalData, strEncoding);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return strReturn;
    }

}
