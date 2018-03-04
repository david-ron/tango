package com.tango;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        answerContainer = (LinearLayout) findViewById(R.id.LinearLayout);
        testButton = (Button) findViewById(R.id.testButton);
        submit = (Button) findViewById(R.id.SubmitID);
        input = (EditText) findViewById(R.id.input);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*
                i++;
                dynamicAnswers = new TextView(answerContainer.getContext());
                dynamicAnswers.setText("hello hello "+ i);
                answerContainer.addView(dynamicAnswers);
                acceptButton = new RadioButton(answerContainer.getContext());
                acceptButton.setText("Accept " + i);
                answerContainer.addView(acceptButton);

                declineButton = new RadioButton(answerContainer.getContext());
                declineButton.setText("Decline " + i);
                answerContainer.addView(declineButton);

                voteButton = new VoteButtons(answerContainer);*/

               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.add(R.id.LinearLayout, new Fragment_Answer());
               ft.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment_Answer comment = new Fragment_Answer();

                ft.add(R.id.LinearLayout, comment);
                //comment.setText(input.getText().toString());
                ft.commit();
            }
        });

    }


}
