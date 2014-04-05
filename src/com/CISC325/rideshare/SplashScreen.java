package com.CISC325.rideshare;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.Window;
import android.content.Intent;

public class SplashScreen extends Activity {
	
	private int waitTime = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		
		Handler splashWait = new Handler();
		splashWait.postDelayed(new Runnable (){
			public void run(){
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				finish();
			}
			}, waitTime);
			
	}

}


