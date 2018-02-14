package com.tango;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VoteOnAnswersTest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_on_answers_test);

        //Create button through java
        //First we need to set up the layout
        ConstraintLayout testLayout = (ConstraintLayout) findViewById(R.id.testLayout);

        //Then add button through the custom button object
        VoteButtons testButton1 = new VoteButtons(this, testLayout, 200, 200);
        VoteButtons testButton2 = new VoteButtons(this, testLayout, 200, 350);

    }

}
