package com.example.hello;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends Activity {

	public String rid = "";

	private Button mButton4;
	private Button mButton5;
	private EditText mEditText3;
	private EditText mEditText4;
	private TextView mTextView4;
	private TextView mTextView5;
	private String phone = "";
	private String pwd = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		mTextView4 = (TextView) findViewById(R.id.textView4);
		mButton4 = (Button) findViewById(R.id.button4);
		mTextView5 = (TextView) findViewById(R.id.textView5);
		mButton5 = (Button) findViewById(R.id.button5);
		mEditText3 = (EditText) findViewById(R.id.editText3);
		mEditText4 = (EditText) findViewById(R.id.editText4);

		mButton4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				phone = mEditText3.getEditableText().toString();
				pwd = mEditText4.getEditableText().toString();

				String path = Message.rootPath
						+ "m/login?phone=" + phone
						+ "&pwd=" + pwd;

				try {
					Network.getJOSN3(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				showDialog1();
			}
		});

		mButton5.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				phone = mEditText3.getEditableText().toString();
				pwd = mEditText4.getEditableText().toString();

				String path = Message.rootPath
						+ "m/signup?phone=" + phone
						+ "&pwd=" + pwd;

				try {
					Network.getJOSN3(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				showDialog2();
			}
		});

	}

	private void showDialog1() {
		final AlertDialog.Builder sucess1 = new AlertDialog.Builder(SignUp.this);
		sucess1.setTitle(R.string.app_name);
		if (Message.answer == 1){
			sucess1.setMessage(R.string.sucess1);
			Message.phone = phone;
		}
		else{
			sucess1.setMessage(R.string.failed1);
			Message.phone = "";
		}
		sucess1.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (Message.answer == 1)
							finish();
					}
				}).create().show();
	}

	private void showDialog2() {
		final AlertDialog.Builder sucess2 = new AlertDialog.Builder(SignUp.this);
		sucess2.setTitle(R.string.app_name);
		if (Message.answer == 1){
			sucess2.setMessage(R.string.sucess2);
			Message.phone = phone;
		}
		else{
			sucess2.setMessage(R.string.failed2);
			Message.phone = "";
		}
		sucess2.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).create().show();
	}
}
