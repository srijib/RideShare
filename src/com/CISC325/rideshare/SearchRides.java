package com.CISC325.rideshare;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class SearchRides extends Fragment {
	Button sqlSearch, addride, showAll, currentRides;
	EditText location, time;
	

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
		
		return inflater.inflate(R.layout.searchrides, container, false);
		
	}
	
	@Override
	public void onStart(){
		super.onStart();
		initControls();
	}
	
	public void initControls(){
		sqlSearch = (Button) getView().findViewById(R.id.buttonSearch);
		showAll = (Button) getView().findViewById(R.id.showAll);
		location = (EditText) getView().findViewById(R.id.search_location);
		time = (EditText) getView().findViewById(R.id.search_time);
		
		
		
		
	
		OnClickListener listener = new OnClickListener(){
			public void onClick(View arg0){
				if(arg0.getId() == R.id.buttonSearch){
					String locationString = location.getText().toString();
					String timeString = time.getText().toString();
					String[] together = new String[2];
					together[0] = locationString;
					together[1] = timeString;
					Intent b = new Intent(getActivity(), SQLSearch.class);
					b.putExtra("values", together );
					SearchRides.this.startActivity(b);
					getActivity().finish();
					
					
				}
					
					
				else if (arg0.getId() == R.id.showAll){
					Intent z = new Intent(getActivity(), SQLView.class);
					SearchRides.this.startActivity(z);
					getActivity().finish();
					
				}
					
					
			
		}
	
	};
	
	sqlSearch.setOnClickListener(listener);
	showAll.setOnClickListener(listener);

	
	}
	
}
	

