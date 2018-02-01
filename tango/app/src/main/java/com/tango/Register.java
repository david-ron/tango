package com.tango;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText rgName = (EditText) findViewById(R.id.rgName);
        final EditText rgEmail = (EditText) findViewById(R.id.rgEmail);
        final EditText rgUsername = (EditText) findViewById(R.id.rgUsername);
        final EditText rgPassword = (EditText) findViewById(R.id.rgPassword);

        final TextView rgFailPrompt = (TextView) findViewById(R.id.rgFailPrompt);
        rgFailPrompt.setText("");

        final Button rgRegister = (Button) findViewById(R.id.rgRegister);

        rgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add data from each field into database
                boolean promptFail = false;
                if(rgName.getText().toString().equals("") || rgEmail.getText().toString().equals("") || rgUsername.getText().toString().equals("") || rgPassword.getText().toString().equals("")){promptFail = true;};
                if(promptFail){
                    rgFailPrompt.setText("YOU MUST FILL IN ALL TEXT FIELDS!");
                    rgFailPrompt.setTextColor(Color.RED);
                }
            }
        });

    }
}
