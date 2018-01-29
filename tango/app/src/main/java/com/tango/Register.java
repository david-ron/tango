package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText rgName = (EditText) findViewById(R.id.rgName);
        final EditText rgEmail = (EditText) findViewById(R.id.rgEmail);
        final EditText rgUsername = (EditText) findViewById(R.id.rgUsername);
        final EditText rgPassword = (EditText) findViewById(R.id.rgPassword);

        final Button rgRegister = (Button) findViewById(R.id.rgRegister);

    }
}
