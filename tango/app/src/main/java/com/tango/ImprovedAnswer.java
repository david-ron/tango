package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ImprovedAnswer extends AppCompatActivity {
    LinearLayout answerContainer;
    Button acceptButton;
    Button declineButton;
    Button testButton;
    TextView dynamicAnswers;
    VoteButtons voteButton;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        answerContainer = (LinearLayout) findViewById(R.id.LinearLayout);
        testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                dynamicAnswers = new TextView(answerContainer.getContext());
                dynamicAnswers.setText("hello hello "+ i);
                answerContainer.addView(dynamicAnswers);
                acceptButton = new Button(answerContainer.getContext());
                acceptButton.setText("Accept " + i);
                answerContainer.addView(acceptButton);

                declineButton = new Button(answerContainer.getContext());
                declineButton.setText("Decline " + i);
                answerContainer.addView(declineButton);

                voteButton = new VoteButtons(answerContainer.getContext(), answerContainer, 0,0);

            }
        });

    }


}
