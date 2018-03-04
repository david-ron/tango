package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


public class ImprovedAnswer extends AppCompatActivity {
    LinearLayout answerContainer;
    RadioButton acceptButton;
    RadioButton declineButton;

    TextView dynamicAnswers;
    VoteButtons voteButton;
    Button SubmitID;
    TextView answerBox;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        answerContainer = findViewById(R.id.LinearLayout);
        SubmitID = findViewById(R.id.SubmitID);
        answerBox = findViewById(R.id.AnswerBox);
        SubmitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                dynamicAnswers = new TextView(answerContainer.getContext());
                dynamicAnswers.setText(answerBox.getText());
                answerContainer.addView(dynamicAnswers);

                acceptButton = new RadioButton(answerContainer.getContext());
                acceptButton.setText("Accept " + i);
                answerContainer.addView(acceptButton);

                declineButton = new RadioButton(answerContainer.getContext());
                declineButton.setText("Decline " + i);
                answerContainer.addView(declineButton);

                voteButton = new VoteButtons(answerContainer);

            }
        });

    }


}
