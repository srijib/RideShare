package com.CISC325.rideshare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.*;
import android.content.Intent;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		overridePendingTransition(0,0);
		final Button logIn = (Button) findViewById(R.id.loginButton);
		final Button signUp = (Button) findViewById(R.id.signupButton);
		
		/*
		//read in user accounts, create file if it doesnt exist.
		File accountFile = new File(user)
		InputStream ai = getResources().openRawResource(R.raw.user_accounts);
		InputStreamReader ir = new InputStreamReader(ai);
		BufferedReader accountInput = new BufferedReader(ir);
		String aiLine = null;
		do{
		try {
			aiLine = accountInput.readLine();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		while (aiLine!= null);
		*/
		
		
		
		// create a File object for the parent directory
		File d = new File("RideShare");
		// have the object build the directory structure, if needed.
		if (!d.exists()){
			   try {
				d.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   d.mkdir();
		}

		// create a File object for the output file
		File outputFile = new File(d, "rides.txt");
		// now attach the OutputStream to the file object, instead of a String representation
		try {
			FileOutputStream fos = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		OnClickListener listener = new OnClickListener(){
			@Override
			public void onClick(View v){
				if (v.equals(logIn)){
						Intent i = new Intent(MainActivity.this, logIn.class);
						MainActivity.this.startActivity(i);
						finish();
						
				}
				else if (v.equals(signUp)){
					Intent z = new Intent(MainActivity.this, SignUp.class);
					MainActivity.this.startActivity(z);
					finish();
					
				}
			}
				
				
				
				
			};
			signUp.setOnClickListener(listener);
			logIn.setOnClickListener(listener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 
	
	

}
