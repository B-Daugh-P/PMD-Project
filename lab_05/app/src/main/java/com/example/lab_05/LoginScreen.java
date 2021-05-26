package com.example.lab_05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {
    EditText username, password;
    Button signUp, signIn;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.btnsignup);
        signIn = findViewById(R.id.btnsignin);
        db = new DBHelper((this));

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginScreen.this, "Please enter all the info", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = db.checkUsername(user);
                    if(!checkuser){
                        Boolean insert = db.insertData(user, pass);
                        if(insert) {
                            Toast.makeText(LoginScreen.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(LoginScreen.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginScreen.this, "User already exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignIn.class);
                startActivity(i);
            }
        });
    }
}
