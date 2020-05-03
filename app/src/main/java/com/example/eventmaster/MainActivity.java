package com.example.eventmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    TextView mTextViewAdmin;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewAdmin = (TextView)findViewById(R.id.textview_admin);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mTextViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(adminIntent);
            }
        });



        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pword = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pword);
                if (res == true) {
                    Toast.makeText(MainActivity.this, "Login Successful!!", Toast.LENGTH_LONG).show();
                    Intent moveToHome = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(moveToHome);
                } else {
                    Toast.makeText(MainActivity.this, "Login ERROR!!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
