package com.example.lab_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    EditText username, password;
    Button btnsignin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btnsignin = findViewById(R.id.btnsignin1);
        db = new DBHelper(this);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(SignIn.this, "Please enter all the info", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = db.checkUsernamePassword(user, pass);
                    if(checkuser){
                        Toast.makeText(SignIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(SignIn.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
