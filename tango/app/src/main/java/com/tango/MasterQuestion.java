package com.tango;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;





/**
 * Created by daveb on 2018-02-04.
 */


public class MasterQuestion extends AppCompatActivity {
    private Button  newQuestionButton;
    private ArrayList<TextView> Questions;
    private EditText futureQuestion;
    private LinearLayoutCompat LinearLayoutQ;
    //tempstatic var
    public static int counterTextView;

       @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterquestion);
        getSupportActionBar().setTitle("");

        //initViews();
        //initListeners();
        LinearLayoutQ = (LinearLayoutCompat)findViewById(R.id.LinearLayoutQ);
        futureQuestion = (EditText)findViewById(R.id.futureQuestion);
        newQuestionButton = (Button)findViewById(R.id.newQuestionButton);
       newQuestionButton.setOnClickListener(onClick());


        TextView textView = new TextView(this);
        textView.setText("New text");


    }


    private OnClickListener onClick() {
            return new OnClickListener() {
                @Override
                public void onClick(View v) {
                   switch (v.getId()) {
                       case R.id.newQuestionButton:
                           LinearLayoutQ.addView(createNewTextView(futureQuestion.getText().toString()));
                           break;
                       default:
                       Log.d("btnClickListener", "-----TextView Clicked : " + v.getTag());
                       Toast.makeText(MasterQuestion.this, "TextView Clicked : " + v.getTag(),
                               Toast.LENGTH_SHORT).show();
                           Intent intentRegister = new Intent(getApplicationContext(), Register.class);
                           startActivity(intentRegister);
                   }
                }
            };
    }
    OnClickListener btnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

        }

    };
//    private void initListeners()
//    {
//        newQuestionButton.setOnClickListener(onClick());
//
//    }
//    private void initViews()
//    {
//      for(TextView q : Questions){
//         // findViewById(R.id.QuestionMaster)
//      }
////    private void initListeners(){
////        newQuestionButton.setOnClickListener(this);
//    }
private TextView createNewTextView(String text) {
    final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    final TextView textView = new TextView(this);
    textView.setLayoutParams(lparams);
    textView.setText(Html.fromHtml("<h3>" +text +"</h3>"));
    textView.setTag("" + counterTextView);
    textView.setId(counterTextView);

    counterTextView++;
    textView.setClickable(true);
    textView.setOnClickListener(onClick());
  // INSERT NEW textView into DB here
    return textView;
    }


}