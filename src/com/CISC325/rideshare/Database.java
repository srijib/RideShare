package com.CISC325.rideshare;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class Database{
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "driverName";
	public static final String KEY_START_LOCATION = "StartLocation";
	public static final String KEY_END_LOCATION = "EndLocation";
	public static final String KEY_TIME = "pickup_time";
	public static final String KEY_SEATSLEFT = "seats_left";
	public static final String KEY_DATE = "date";
	
	public static final String ACCOUNT_ID = "_id";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_CURRENT_RIDES = "current_rides";

	private static final String DATABASE_NAME = "Rideshare";
	private static final String DATABASE_RIDES = "Rides";
	private static final String DATABASE_ACCOUNTS = "Accounts";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_RIDES + " (" +
					KEY_ROWID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, "+
					KEY_START_LOCATION + " TEXT NOT NULL, " +
					KEY_END_LOCATION + " TEXT NOT NULL, " +
					KEY_TIME + " TEXT NOT NULL, " +
					KEY_DATE + " TEXT NOT NULL, " +
					KEY_SEATSLEFT + " TEXT NOT NULL);"	
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_ACCOUNTS + " (" +
					ACCOUNT_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_USERNAME + " TEXT NOT NULL, "+
					KEY_PASSWORD + " TEXT NOT NULL, "+
					KEY_EMAIL + " TEXT NOT NULL, " +
					KEY_CURRENT_RIDES + " TEXT " +");"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_RIDES);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_ACCOUNTS);
			onCreate(db);
			
		}
		
		
		
	}
	public Database(Context c){
		ourContext = c;
	}
	
	public Database open() throws SQLException{
		Log.w("Rideshare", "Starting to open");
		ourHelper = new DbHelper(ourContext);
		Log.w("Rideshare", "created our helper");
		ourDatabase = ourHelper.getWritableDatabase();
		Log.w("Rideshare", "Got writable database");
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	void deleteRidesData()
	{
		ourHelper = new DbHelper(ourContext);
	    SQLiteDatabase sdb= ourHelper.getWritableDatabase();
	    sdb.delete(DATABASE_RIDES, null, null);

	}
	
	

	public void createEntry(String nameString, String start, String end,
			String timeString, String dayString, String seatsString) {
		Log.w("Rideshare", "Creating Entry");
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, nameString);
		cv.put(KEY_START_LOCATION, start);
		cv.put(KEY_END_LOCATION, end);
		cv.put(KEY_TIME, timeString);
		cv.put(KEY_DATE, dayString);
		cv.put(KEY_SEATSLEFT, seatsString);
		
		ourDatabase.insert(DATABASE_RIDES, null, cv);
		
	}

	public List<String> getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_START_LOCATION, KEY_END_LOCATION, KEY_TIME, KEY_DATE, KEY_SEATSLEFT };
		Cursor c = ourDatabase.query(DATABASE_RIDES, columns, null, null, null, null, null);
		List<String> result = new ArrayList<String>();
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iStart = c.getColumnIndex(KEY_START_LOCATION);
		int iEnd = c.getColumnIndex(KEY_END_LOCATION);
		int iTime = c.getColumnIndex(KEY_TIME);
		int iDate = c.getColumnIndex(KEY_DATE);
		int iSeats = c.getColumnIndex(KEY_SEATSLEFT);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String resulttemp = "Driver Name: " + c.getString(iName) + "\n" 
					+ "Start Location: " + c.getString(iStart) + " | " 
					+ "End Location: " + c.getString(iEnd) + "\n" 
					+ "Departure Time: " +c.getString(iTime) + " | "
					+ "Date: " + c.getString(iDate) + " \n "
					+ "Seats: " + c.getString(iSeats) + "\n ";
			result.add(resulttemp);
			
		}
		
		return result;
	}

	public void addAccount(String nameString, String emailString,
			String passwordString) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_USERNAME, nameString);
		cv.put(KEY_PASSWORD, passwordString);
		cv.put(KEY_EMAIL, emailString);
		cv.put(KEY_CURRENT_RIDES, "");
		ourDatabase.insert(DATABASE_ACCOUNTS, null, cv);
		
	}

	public List<String[]> getAccountData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_USERNAME, KEY_PASSWORD};
		Cursor c = ourDatabase.query(DATABASE_ACCOUNTS, columns, null, null, null, null, null);
		List<String[]> result = new ArrayList<String[]>();
		int iName = c.getColumnIndex(KEY_USERNAME);
		int iPass = c.getColumnIndex(KEY_PASSWORD);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String[] resulttemp = new String[2];
			resulttemp[0] = c.getString(iName);
			resulttemp[1] = c.getString(iPass);
			result.add(resulttemp);
			
		}
		
		return result;
		
	}

	public List<String> getSearch(String string, String string2) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_START_LOCATION, KEY_END_LOCATION, KEY_TIME, KEY_DATE, KEY_SEATSLEFT };
		Cursor c = ourDatabase.query(DATABASE_RIDES, columns, null, null, null, null, null);
		List<String> result = new ArrayList<String>();
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iStart = c.getColumnIndex(KEY_START_LOCATION);
		int iEnd = c.getColumnIndex(KEY_END_LOCATION);
		int iTime = c.getColumnIndex(KEY_TIME);
		int iDate = c.getColumnIndex(KEY_DATE);
		int iSeats = c.getColumnIndex(KEY_SEATSLEFT);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			
			if (c.getString(iStart).equalsIgnoreCase(string) || 
					c.getString(iEnd).equalsIgnoreCase(string) ||
					c.getString(iTime).equalsIgnoreCase(string2) ||
					c.getString(iDate).equalsIgnoreCase(string2)){
				String resulttemp = "Driver Name: " + c.getString(iName) + "\n" 
						+ "Start Location: " + c.getString(iStart) + " | " 
						+ "End Location: " + c.getString(iEnd) + "\n" 
						+ "Departure Time: " +c.getString(iTime) + " | "
						+ "Date: " + c.getString(iDate) + " \n "
						+ "Seats: " + c.getString(iSeats) + "\n ";
				result.add(resulttemp);
				
			}
			
			
			
		}
		
		return result;
		
	}

	public String getEmail(String user) {
		Log.w("driver name", "The driver name is: " + user);
		String[] columns = new String[]{ KEY_USERNAME, KEY_EMAIL};
		Cursor c = ourDatabase.query(DATABASE_ACCOUNTS, columns, null, null, null, null, null);
		String result = null;
		int iName = c.getColumnIndex(KEY_USERNAME);
		int iEmail = c.getColumnIndex(KEY_EMAIL);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String[] resulttemp = new String[2];
			resulttemp[0] = c.getString(iName);
			//Log.w("database names", resulttemp[0]);
			resulttemp[1] = c.getString(iEmail);
			//Log.w("database emails", resulttemp[1]);
			if (resulttemp[0].equalsIgnoreCase(user) && resulttemp[0] != null && resulttemp[0] != "" ){
				result = resulttemp[1];
				break;
				
			}
			
		}
		Log.w("result", result);
		
		return result;
		

	}
	

}
