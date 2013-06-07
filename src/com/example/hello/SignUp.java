package com.example.hello;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {

	public String rid = "";

	private Button signIn;
	private Button signUp;
	private EditText account;
	private EditText password;
	private String phone = "";
	private String pwd = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		signIn = (Button) findViewById(R.id.button4);
		signUp = (Button) findViewById(R.id.button5);
		account = (EditText) findViewById(R.id.editText3);
		password = (EditText) findViewById(R.id.editText4);

		signIn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				phone = account.getEditableText().toString();
				pwd = password.getEditableText().toString();

				String path = Message.rootPath
						+ "m/login?phone=" + phone
						+ "&pwd=" + pwd;

				try {
					Network.getJOSN3(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				signInDialog();
			}
		});

		signUp.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				phone = account.getEditableText().toString();
				pwd = password.getEditableText().toString();

				String path = Message.rootPath
						+ "m/signup?phone=" + phone
						+ "&pwd=" + pwd;

				try {
					Network.getJOSN3(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				signUpDialog();
			}
		});

	}

	private void signInDialog() {
		final AlertDialog.Builder signInSucess = new AlertDialog.Builder(SignUp.this);
		signInSucess.setTitle(R.string.app_name);
		if (Message.answer == 1){
			signInSucess.setMessage(R.string.sucess1);
			Message.phone = phone;
		}
		else{
			signInSucess.setMessage(R.string.failed1);
			Message.phone = "";
		}
		signInSucess.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (Message.answer == 1)
							finish();
					}
				}).create().show();
	}

	private void signUpDialog() {
		final AlertDialog.Builder signUpSucess = new AlertDialog.Builder(SignUp.this);
		signUpSucess.setTitle(R.string.app_name);
		if (Message.answer == 1){
			signUpSucess.setMessage(R.string.sucess2);
			Message.phone = phone;
		}
		else{
			signUpSucess.setMessage(R.string.failed2);
			Message.phone = "";
		}
		signUpSucess.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).create().show();
	}
}
