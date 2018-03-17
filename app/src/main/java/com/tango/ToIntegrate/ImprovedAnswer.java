package com.tango;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


public class ImprovedAnswer extends AppCompatActivity {
    LinearLayout answerContainer;

    Button testButton;
    Button submit;
    EditText input;
    TextView question;
    String questions;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        Intent intent = getIntent();
        questions= intent.getStringExtra("questions");
        question = (TextView) findViewById(R.id.question);
        question.setText(questions);
        answerContainer = (LinearLayout) findViewById(R.id.LinearLayout);
        testButton = (Button) findViewById(R.id.testButton);
        submit = (Button) findViewById(R.id.SubmitID);
        input = (EditText) findViewById(R.id.input);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               i++;
                Fragment_Answer comment = new Fragment_Answer();
                Bundle args = new Bundle();
                args.putString("input", "This is random Answer #" +i);
                comment.setArguments(args);
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.add(R.id.LinearLayout, comment);
               ft.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Fragment_Answer comment = new Fragment_Answer();
                Bundle args = new Bundle();
                args.putString("input", input.getText().toString());
                comment.setArguments(args);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.LinearLayout, comment);
                ft.commit();
                input.setText("");
            }
        });

    }


}
