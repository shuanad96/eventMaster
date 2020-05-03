package com.example.eventmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView hTextViewProfile;
    TextView hTextViewSearch;
    TextView hTextViewUpcoming;
    TextView hTextViewLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hTextViewProfile= (TextView)findViewById(R.id.textview_profile);
        hTextViewSearch= (TextView)findViewById(R.id.textview_search);
        hTextViewUpcoming= (TextView)findViewById(R.id.textview_upcoming);
        hTextViewLogout= (TextView)findViewById(R.id.textview_logout);

        hTextViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        hTextViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        hTextViewUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upcomingIntent = new Intent(HomeActivity.this, UpcomingActivity.class);
                startActivity(upcomingIntent);
            }
        });

        hTextViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Logged Out Successfully!!", Toast.LENGTH_LONG).show();
                Intent logoutIntent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(logoutIntent);

            }
        });



    }
}
