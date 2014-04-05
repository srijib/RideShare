package com.CISC325.rideshare;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
	
	
public class logIn extends Activity{
	public static 
	
	
	
	
	
	
	String currentUser = null;
	EditText username, password;
	TextView incorrect;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		overridePendingTransition(0,0);
		final Button logIn = (Button) findViewById(R.id.addRideButton);
		final Button sign = (Button) findViewById(R.id.s);
		
		
		username = (EditText) findViewById(R.id.loginName);
		password = (EditText) findViewById(R.id.loginPassword);
		incorrect = (TextView) findViewById(R.id.incorrect);
		
	
	
	OnClickListener listener = new OnClickListener(){
		@Override
		public void onClick(View v){
			if (v.equals(logIn)){
				boolean test = false;
				String uname = username.getText().toString();
				String pass = password.getText().toString();
				String set = "Incorrect Username or Password";
				
				
				Database info = new Database(logIn.this);
				info.open();
				List<String[]> data = info.getAccountData();
				info.close();
				
				for (int i=0; i < data.size(); i++)
				{
				    String[] temp = data.get(i);
				    if (uname.equalsIgnoreCase(temp[0]) && pass.equals(temp[1])){
				    	
				    	test = true;
				    	currentUser = temp[0];
				    	break;
				    }
				}
				
				if (test){
					Intent i = new Intent(logIn.this, SearchAdd.class);
					logIn.this.startActivity(i);
					finish();
				}else{
					incorrect.setText(set);
				}
					
				
				
					
			}
			if(v.equals(sign)){
				Intent x = new Intent(logIn.this, SignUp.class);
				logIn.this.startActivity(x);
				finish();
			}
		}
	};
	logIn.setOnClickListener(listener);
	sign.setOnClickListener(listener);
	}
}

