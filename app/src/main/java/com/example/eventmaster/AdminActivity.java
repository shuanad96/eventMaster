package com.example.eventmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText aTextArtist;
    EditText aTextLocation;
    EditText aTextticketsout;
    EditText aTextEventDate;
    Button aButtonSubmit;
    Button aButtonView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db = new DatabaseHelper(this);
        aTextArtist= (EditText)findViewById(R.id.edittext_artist);
        aTextLocation = (EditText)findViewById(R.id.edittext_location);
        aTextticketsout= (EditText)findViewById(R.id.edittext_ticketsout);
        aTextEventDate= (EditText)findViewById(R.id.edittext_eventdate);
        aButtonSubmit = (Button) findViewById(R.id.button_submit);
        aButtonView = (Button) findViewById(R.id.button_view);

        aButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String artist = aTextArtist.getText().toString().trim();
                String location = aTextLocation.getText().toString().trim();
                String ticketsout = aTextticketsout.getText().toString().trim();
                String eventdate = aTextEventDate.getText().toString().trim();


                db.addEvent(artist,location,ticketsout,eventdate);
                Toast.makeText(AdminActivity.this, "Event Added!!!!", Toast.LENGTH_SHORT).show();
                Intent clear = new Intent(AdminActivity.this,AdminActivity.class);
                startActivity(clear);

            }
        });







    }

}
