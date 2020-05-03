package com.example.eventmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText rTextFirstname;
    EditText rTextSurname;
    EditText rTextEmail;
    EditText rTextUsername;
    EditText rTextPassword;
    EditText rTextCnfPassword;
    Button rButtonRegister;
    TextView rTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        rTextFirstname= (EditText)findViewById(R.id.edittext_first_name);
        rTextSurname = (EditText)findViewById(R.id.edittext_surname);
        rTextEmail= (EditText)findViewById(R.id.edittext_email);
        rTextUsername= (EditText)findViewById(R.id.edittext_username);
        rTextPassword = (EditText)findViewById(R.id.edittext_password);
        rTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        rButtonRegister = (Button)findViewById(R.id.button_Register);
        rTextViewLogin = (TextView)findViewById(R.id.textview_LOGIN);
        rTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(registerIntent);
            }
        });

        rButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = rTextFirstname.getText().toString().trim();
                String sname = rTextSurname.getText().toString().trim();
                String email = rTextEmail.getText().toString().trim();
                String user = rTextUsername.getText().toString().trim();
                String pword = rTextPassword.getText().toString().trim();
                String cnf_pword = rTextCnfPassword.getText().toString().trim();

                if (pword.equals(cnf_pword)){
                    long val = db.addUser(user,fname,sname,email,pword);
                    if (val > 0 ){
                    Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                    Intent moveToHome = new Intent(RegisterActivity.this,HomeActivity.class);
                    startActivity(moveToHome);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registeration ERROR",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
