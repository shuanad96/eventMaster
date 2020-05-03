package com.example.eventmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpcomingActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button button_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        db = new DatabaseHelper(this);

        button_view = (Button)findViewById(R.id.button_view);
        viewEvents();
    }

    public void viewEvents (){
        button_view.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor cursor =db.viewEventsData();
                        if (cursor.getCount() == 0){
                            //show message
                            showMessage("Error", "Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("eventid :"+ cursor.getString(0)+ "\n");
                            buffer.append("artistname :"+ cursor.getString(1)+ "\n");
                            buffer.append("location :"+ cursor.getString(2)+ "\n");
                            buffer.append("ticketsout :"+ cursor.getString(3)+ "\n");
                            buffer.append("eventdate :"+ cursor.getString(4)+ "\n\n");
                        }

                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


