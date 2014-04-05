package com.CISC325.rideshare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class CurrentRides extends Activity {

	public static String Rides;
	TextView r;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_rides);
		overridePendingTransition(0,0);
		r  = (TextView) findViewById(R.id.cur);
		
		if (Rides != null){
			r.setText(Rides);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.current_rides, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		Intent i = new Intent(CurrentRides.this, SearchAdd.class);
		CurrentRides.this.startActivity(i);
		finish();
	}

}
