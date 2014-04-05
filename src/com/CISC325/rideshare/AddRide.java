package com.CISC325.rideshare;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.*;

public class AddRide extends Fragment{
	
	int waitTime = 2000;
	Button addRide; 
	EditText start_location, end_location, time, seats, date;
	TextView success;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
		
		View myFragmentView = inflater.inflate(R.layout.addride, container, false);
		
		addRide = (Button) myFragmentView.findViewById(R.id.addRideButton);
		start_location = (EditText) myFragmentView.findViewById(R.id.loginPassword);
		end_location = (EditText) myFragmentView.findViewById(R.id.editEndLocation);
		time = (EditText) myFragmentView.findViewById(R.id.editTime);
		seats = (EditText) myFragmentView.findViewById(R.id.seats);
		date = (EditText) myFragmentView.findViewById(R.id.editDate);
		success = (TextView) myFragmentView.findViewById(R.id.sucess);
		
		
		
		OnClickListener listener = new OnClickListener(){
			public void onClick(View arg0){
				
				if (arg0.getId() ==  R.id.addRideButton){
					boolean testStrings = true;
					boolean didItWork = true;
					
						
						
						
					String nameString = logIn.currentUser;
					String start = start_location.getText().toString();
					String end = end_location.getText().toString();
					String timeString = time.getText().toString();
					String dateString = date.getText().toString();
					String seatsString = seats.getText().toString();
					
					
					if (nameString.equalsIgnoreCase("")|| nameString == null||
							start.equalsIgnoreCase("") || start == null ||
							timeString.equalsIgnoreCase("") || timeString==null ||
							dateString.equalsIgnoreCase("") || dateString == null ||
							seatsString.equalsIgnoreCase("") || seatsString == null){
						
						testStrings = false;
					}
					
					if (!testStrings){
						
						success.setText("Please fill in all fields.");
						
						
					}else{
					
					
					
					Database entry = new Database(getActivity());
					Log.w("Rideshare", "Opening");
					entry.open();
					Log.w("Rideshare", "Opened");
					entry.createEntry(nameString, start, end, timeString, dateString, seatsString);
					entry.close();
					
						
							success.setText("Successfully Added!");
						// end if
					// end finally
				}
					
				}
					
						
				// end switch
			}// end onclick
			}; // end listener creation
			
			
		addRide.setOnClickListener(listener);
		
		return myFragmentView;
	}
	
	
	
		
		
		
	
	
	
	
	
}
