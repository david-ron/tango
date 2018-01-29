package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Dummy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        final EditText dName = (EditText) findViewById(R.id.dName);
        final TextView dWelcome = (TextView) findViewById(R.id.dWelcome);

    }
}
