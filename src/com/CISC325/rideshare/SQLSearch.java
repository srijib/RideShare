package com.CISC325.rideshare;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SQLSearch extends Activity {

ListView listview;
String[] values;
String email, selected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 overridePendingTransition(0,0);
		setContentView(R.layout.activity_sqlview);
		listview = (ListView) findViewById(R.id.showAllView);
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			values = extras.getStringArray("values");
		}
		Log.w("Search", values[0] +" " + values[1]);
		Database info = new Database(this);
		info.open();
		List<String> data = info.getSearch(values[0], values[1]);
		info.close();
		
		View header = getLayoutInflater().inflate(R.layout.header, null);
		listview.addHeaderView(header);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, data);
		listview.setAdapter(adapter);
		
		//copy from here 
				listview.setOnItemClickListener(new OnItemClickListener(){
					
					

					@Override
					public synchronized void onItemClick(AdapterView<?> parent, View view, int position, long id){
						selected = (String) (listview.getItemAtPosition(position));
						String[] splitSelected = selected.split(" ");
						String[] splitSelected2 = splitSelected[2].split("\n");
						Log.w("Test Select", splitSelected2[0] + " has been selected first");
						String selected2 = splitSelected2[0];
						Log.w("Test Select", selected2 + " has been selected");
						setEmail(selected2);
						//Log.w("email value", email);
						
						
						
						
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								SQLSearch.this);
				 
							// set title
							alertDialogBuilder.setTitle("Requesting a Ride");
				 
							// set dialog message
							alertDialogBuilder
								.setMessage("Are you sure you want to request a ride?")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										sendEmail();
										
									}
								  })
								.setNegativeButton("No",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show();
						
						
					}
					
				}
				
						
						);
				
				
			}
			
			
			protected void sendEmail(){
				Intent emailSend = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
				emailSend.setType("message/rfc822");
				emailSend.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
				emailSend.putExtra(Intent.EXTRA_SUBJECT, logIn.currentUser + " would like to request a ride!");
				emailSend.putExtra(Intent.EXTRA_TEXT   , "Hi, this is " + logIn.currentUser + " and I would like to request a ride for your offer: \n" + selected);
				try {
				    startActivity(Intent.createChooser(emailSend, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(SQLSearch.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
			//to here
			
			public void setEmail(String name) {
				Log.w("email", "starting email");
				try{
				Database compare = new Database(this);
				compare.open();
				Log.w("email", "Database is open");
				email = compare.getEmail(name);
				Log.w("email", email + " is the email.");
				
				compare.close();
				Log.w("email", email);
				}catch (Exception e){
					Log.w("Exception", e.getMessage());
				}
				
			}

		
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlview, menu);
		return true;
		
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(SQLSearch.this, SearchAdd.class);
		SQLSearch.this.startActivity(i);
		finish();
	}

}