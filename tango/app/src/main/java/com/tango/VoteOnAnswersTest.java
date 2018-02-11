package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VoteOnAnswersTest extends AppCompatActivity {

    private int pointValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_on_answers_test);

        //instantiate the buttonimage (the up arrow)
        final ImageButton upvoteButton= (ImageButton) findViewById(R.id.upvote_button);

        //TODO get value from database, dont just assign 0
        pointValue = 0;

        //the value of the arrow
        final TextView pointQty = (TextView) findViewById(R.id.pointQty);


        pointQty.setText(Integer.toString(pointValue));

        //occurs when up arrow is pressed
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upvoteButton.setImageResource(R.drawable.ic_up_arrow_selected);
                pointQty.setText(Integer.toString(++pointValue));

            }
        });
    }


}
