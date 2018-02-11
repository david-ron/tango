package com.tango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WrittingAnswer extends AppCompatActivity {
    EditText writtenAnswer;
    TextView displayedAnswer;
    String answers;

    Button submitAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writting_answer);

        submitAnswer = (Button) findViewById(R.id.submitAnswer);
        writtenAnswer = (EditText) findViewById(R.id.writtenAnswer);
        displayedAnswer = (TextView) findViewById(R.id.answers);

        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers = writtenAnswer.getText().toString();
                displayedAnswer.setText(answers);
                Intent submitAnswerIntent = new Intent(WrittingAnswer.this, AnsweringQuestion.class);
                startActivity(submitAnswerIntent);
            }
        });

    }


}
