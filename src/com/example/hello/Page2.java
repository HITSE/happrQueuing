package com.example.hello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Page2 extends Activity{
	
	public Button button1;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        button1 = (Button)findViewById(R.id.button1);
        
        
        
        button1.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		
        		showDialog1();
        		
        	}
        });
	}
	
	public void showDialog1(){
		
//		LayoutInflater inflater = 
//				(LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		final EditText input1;
		final EditText input2;
    	View view = getLayoutInflater().inflate(R.layout.dialog1,(ViewGroup)findViewById(R.id.mdialog));
    	input1 = (EditText)view.findViewById(R.id.editText1);
    	input2 = (EditText)view.findViewById(R.id.editText2);
		final AlertDialog.Builder dialog1 = new AlertDialog.Builder(Page2.this);
		dialog1.setView(view);
		dialog1.setTitle(R.string.app_name)
		.setPositiveButton("发送", 
				new DialogInterface.OnClickListener() {
			
			        public void onClick(DialogInterface arg0, int arg1){
			        	/*EditText input1,input2;
			        	View view = getLayoutInflater().inflate(R.layout.dialog1,(ViewGroup)findViewById(R.id.mdialog));
			        	input1 = (EditText)view.findViewById(R.id.editText1);
			        	input2 = (EditText)view.findViewById(R.id.editText2);*/
			        	
			        	String phone_num = "";
			        	String num_peo;
			        	phone_num = input1.getEditableText().toString();
			        	num_peo = input2.getEditableText().toString();
			        	//发送信息在这里！！！
			        	String path = "http://192.168.17.16/se/r.php?phone="+phone_num+"&num="+num_peo;
			        	try {
							Network2.postit(path);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
		        })
		.setNegativeButton("取消", 
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						dialog1.setCancelable(true);
					}
				})
		.create().show();
	}

}
