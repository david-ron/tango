package com.tango;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;

public class AnsweringQuestion extends AppCompatActivity {
    private Button reply;
    private Button submit;
    LinearLayout textbox;

    EditText writtenAnswer;   // Answer in the text box
    TextView displayedAnswer; // Answer box in the the page s
    String answers="";           // This is the text that will be displayed in the answer box
    ArrayList<String> listOfAnswers= new ArrayList<String>();  // The answers will be stored in a list for voting system it will be easy to reorganise the list

    //******
    private RadioButton acceptButton;
    private RadioButton declineButton;
    EditText numR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answering_question);

        ///////////
        reply = (Button) findViewById(R.id.reply);
        submit = (Button) findViewById(R.id.submit);
        textbox = (LinearLayout) findViewById(R.id.textBox);
        ///////////
        writtenAnswer = (EditText) findViewById(R.id.writtenAnswer);   // Answer in the text box
        displayedAnswer = (TextView) findViewById(R.id.answers);     // Answer in the main page


        acceptButton = (RadioButton) findViewById(R.id.acceptButton);
        declineButton = (RadioButton) findViewById(R.id.declineButton);
        numR = (EditText) findViewById(R.id.numRadio);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textbox.setVisibility(View.VISIBLE);          // The textbox appears
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answers = ""; // Reinitialises the answer string

                listOfAnswers.add(writtenAnswer.getText().toString());      // Answers are added in the list


                for (int i = 0; i < listOfAnswers.size(); i++) {          // Answers are stored one by one in a string
                    answers += "UserName" + (i + 1) + "\n" + listOfAnswers.get(i) + "\n\n\n"; ///////////  saves username skips a line and displays answer
                }

                displayedAnswer.setText(answers);    // The string containing all the answers is displayed

                textbox.setVisibility(View.GONE);    // After submitting the answer the textbox dissapears



            }
        });
        numR = (EditText) findViewById(R.id.numRadio);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int num = Integer.parseInt(numR.getText().toString());
                String str = listOfAnswers.get(num) + "    \n *Accepted*";
                listOfAnswers.set(num, str);

//                SpannableString str1 = new SpannableString(listOfAnswers.get(0));
//                str1.setSpan(new ForegroundColorSpan(Color.RED), 0, str1.length(), 0);
            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int num = Integer.parseInt(numR.getText().toString());
                String str = listOfAnswers.get(num) + "    \n *Declined*";
                listOfAnswers.set(num, str);
            }
        });



    }
//    public void onRadioButtonClicked(View view){
//        boolean checked = ((RadioButton) view).isChecked();
//        //SpannableStringBuilder builder = new SpannableStringBuilder();
//        SpannableString str1 = new SpannableString(listOfAnswers.get(0));
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.acceptButton:
//                if (checked)
//                    str1.setSpan(new ForegroundColorSpan(Color.RED), 0, str1.length(), 0);
//                    break;
//            case R.id.declineButton:
//                if (checked)
//                    // Ninjas rule
//                    break;
//        }
//    }

}
