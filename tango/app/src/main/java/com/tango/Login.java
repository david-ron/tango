package com.tango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText lgUsername = (EditText) findViewById(R.id.lgUsername);
        final EditText lgPassword = (EditText) findViewById(R.id.lgPassword);

        final Button lgLogin = (Button) findViewById(R.id.lgLogin);
        final TextView lgRegister = (TextView) findViewById(R.id.lgRegister);

        lgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(registerIntent);
            }
        });
    }
}

