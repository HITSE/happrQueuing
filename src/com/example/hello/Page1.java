package com.example.hello;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Page1 extends Activity {
	
    public ListView list = null;
    public TextView mTextView1;
    List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.MyListView);
        mTextView1 = (TextView)findViewById(R.id.textView1);
        
         try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
 /*      for(int i=0;i<10;i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("itemTitle", "This is Title");
            map.put("itemText", "This is text");
            mylist.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this, mylist, 
        		R.layout.myitem, new String[] {"itemTitle", "itemText"}, new int[] {R.id.itemTitle,R.id.itemText}); 
        list.setAdapter(adapter);*/
        
        list.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id){
        		String path = "http://192.168.17.16/se/r.php?phone="+Integer.toString(position)+"&num=888";
        		try {
					Network2.postit(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Intent intent = new Intent();
        		intent.setClass(Page1.this, Page2.class);
        		startActivity(intent);
        	}
        });

    }
    
 /*   public String getMethod(String strGetURL, String strEncoding){
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
    }*/
    
    public void updateListView() throws Exception{
    	String path = "http://192.168.17.16/se/a.php";
    	
    	try{
    	List<Person> persons = Network2.getJOSN1(path);
    	
    	for(int i = 0;i<persons.size();i++){
    		HashMap<String, String> map = new HashMap<String, String>();
            map.put("itemTitle",persons.get(i).getName());
            map.put("itemText", persons.get(i).getNum());
            mylist.add(map);
    	}
    	
    	SimpleAdapter adapter = new SimpleAdapter(this, mylist, 
        		R.layout.myitem, new String[] {"itemTitle", "itemText"}, new int[] {R.id.itemTitle,R.id.itemText}); 
        list.setAdapter(adapter);
    	} catch(Exception e){
    		
    	}
/*    	String strRet = "";
    	String url =
    			"http://www.dubblogs.cc:8751/Android/Test/API/TestSpinner/spinner1.php";
    	try{
    		strRet = getMethod(url,"utf-8");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	String strRet = new Network1().getString();
    	try{
    		if(strRet.length()>0&&eregi("<delimiter1",strRet)){
    			String[] aryTemp1 = strRet.split("<delimiter1");
    			String[] aryTemp2 = aryTemp1[1].split("delimiter2");
    			
    			for(int i = 0;i < aryTemp2.length;i++){
    				HashMap<String, String> map = new HashMap<String, String>();
    	            map.put("itemTitle", aryTemp2[i].trim());
    	            map.put("itemText", "This is text");
    	            mylist.add(map);
    		    }
    	        
    	        SimpleAdapter adapter = new SimpleAdapter(this, mylist, 
    	        		R.layout.myitem, new String[] {"itemTitle", "itemText"}, new int[] {R.id.itemTitle,R.id.itemText}); 
    	        list.setAdapter(adapter);
    	    }
    	}catch(Exception e){
    		
    	}*/
    }
    
/*    public static boolean eregi(String strPat,String strUnknow){
    	String strPattern = "(?i)"+strPat;
    	Pattern p = Pattern.compile(strPattern);
    	Matcher m = p.matcher(strUnknow);
    	return m.find();
    	} */

}
