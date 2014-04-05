package com.CISC325.rideshare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends Activity {
	
	Button signUp, logIn;
	EditText email, username, password;
	TextView success, incorrect;
	private int waitTime = 2500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		overridePendingTransition(0,0);
		signUp = (Button) findViewById(R.id.signUpButton);
		logIn = (Button) findViewById(R.id.l);
		email = (EditText) findViewById(R.id.signUpEmail);
		username = (EditText) findViewById(R.id.signUpName);
		password = (EditText) findViewById(R.id.signUpPassword);
		success = (TextView) findViewById(R.id.successadd);
		incorrect = (TextView) findViewById(R.id.incorrectSignUp);
		
		OnClickListener listener = new OnClickListener(){
			public void onClick(View arg0){
				
				
				if(arg0.getId() == R.id.signUpButton){
					
					boolean didItWork = true;
					
						
					boolean testStrings = true;
						
				
					String nameString = username.getText().toString();
					String  emailString = email.getText().toString();
					String passwordString = password.getText().toString();
					
					if (nameString.equalsIgnoreCase("")|| nameString == null||
							emailString.equalsIgnoreCase("") || emailString == null ||
							passwordString.equalsIgnoreCase("") || passwordString==null){
						
						testStrings = false;
					}
					
					boolean testEmail = isEmailValid(emailString);
					
					
					if(testEmail==false){
						
						incorrect.setText("Please enter a valid email");
						
					}
					else if(testStrings == false){
						incorrect.setText("Please fill in all fields");
					
						
						
					}else{
		
					
					Database entry = new Database(SignUp.this);
					Log.w("Rideshare", "Opening");
					entry.open();
					Log.w("Rideshare", "Opened");
					entry.addAccount(nameString, emailString, passwordString);
					entry.close();
						if (didItWork){
							success.setText("Successfully Signed Up!");
							
							Handler gotoLogin = new Handler();
							gotoLogin.postDelayed(new Runnable (){
								public void run(){
									Intent i = new Intent(SignUp.this, logIn.class);
									startActivity(i);
									finish();
								}
								}, waitTime);
							
						}// end if
					}
					// end finally
				}
					
				else if(arg0.getId()==R.id.l){
					Intent z = new Intent(SignUp.this, logIn.class);
					startActivity(z);
					finish();
				}// end switch
			}// end onclick
			
			}; // end listener creation
			
			
		signUp.setOnClickListener(listener);
		logIn.setOnClickListener(listener);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}

}
