package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnsweringQuestion extends AppCompatActivity {
    private Button reply;
    private Button submit;
    LinearLayout textbox;

    EditText writtenAnswer;
    TextView displayedAnswer;
    String answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answering_question);

        ///////////
        reply = (Button) findViewById(R.id.reply);
        submit = (Button) findViewById(R.id.submit);
        textbox = (LinearLayout) findViewById(R.id.textBox);
        ///////////
        writtenAnswer = (EditText) findViewById(R.id.writtenAnswer);
        displayedAnswer = (TextView) findViewById(R.id.answers);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textbox.setVisibility(View.VISIBLE);
                //Intent replyIntent = new Intent(AnsweringQuestion.this, WrittingAnswer.class);
                //startActivity(replyIntent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers = writtenAnswer.getText().toString();
                displayedAnswer.setText(answers);
                textbox.setVisibility(View.GONE);
                //Intent replyIntent = new Intent(AnsweringQuestion.this, WrittingAnswer.class);
                //startActivity(replyIntent);
            }
        });
    }
}
