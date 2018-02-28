package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ImprovedAnswer extends AppCompatActivity {
    LinearLayout answerContainer;
    Button testButton;
    TextView dynamicAnswers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        answerContainer = (LinearLayout) findViewById(R.id.LinearLayout);
        testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicAnswers = new TextView(answerContainer.getContext());
                dynamicAnswers.setText("hello hello");
                answerContainer.addView(dynamicAnswers);

            }
        });

    }


}
