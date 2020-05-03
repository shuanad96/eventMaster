package com.example.eventmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "register.db";
    public static final String TABLE_NAME= "registeruser";
    public static final String COL_1= "username";
    public static final String COL_2= "firstname";
    public static final String COL_3= "surname";
    public static final String COL_4= "email";
    public static final String COL_5= "password";

    public static final String TABLE_NAME_EVENTS= "eventdetails";
    public static final String EVENT_ID= "eventid";
    public static final String ARTISTNAME= "artistname";
    public static final String LOCATION= "location";
    public static final String TICKETSOUT= "ticketsout";
    public static final String EVENTDATE= "eventdate";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (username TEXT PRIMARY KEY, firstname TEXT, surname TEXT, email TEXT, password TEXT )");
        db.execSQL("CREATE TABLE eventdetails ( eventid INTEGER PRIMARY KEY AUTOINCREMENT,  artistname TEXT, location TEXT, ticketsout TEXT, eventdate TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addUser (String username, String firstname, String surname, String email, String password  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("firstname", firstname);
        contentValues.put("surname", surname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long res =db.insert("registeruser", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser (String username, String password) {
        String [] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_1 + "=?" + " and " + COL_5 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;

    }

    public long addEvent (String artistname, String location, String ticketsout, String eventdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("artistname", artistname);
        contentValues.put("location", location);
        contentValues.put("ticketsout", ticketsout);
        contentValues.put("eventdate", eventdate);
        long res =db.insert("eventdetails", null, contentValues);
        db.close();
        return res;
    }

    public Cursor viewEventsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String eventQuery = "Select * from "+TABLE_NAME_EVENTS;
        Cursor cursor = db.rawQuery(eventQuery, null);

        return cursor;
    }
}
