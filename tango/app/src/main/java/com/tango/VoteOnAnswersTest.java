package com.tango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.graphics.Color;

import org.w3c.dom.Text;

public class VoteOnAnswersTest extends AppCompatActivity {

    private int pointValue;


    //the Vote enum is used to check the status of the vote
    enum Vote
    {
        UNSELECTED, UPVOTED, DOWNVOTED;
    }
    private Vote vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_on_answers_test);

        //TODO get value from database, don't just assign 0
        pointValue = 0;
        //TODO initial state of the enum info should be from database
        vote = Vote.UNSELECTED;

        //the pointage to be displayed is the Textview
        final TextView pointQty = (TextView) findViewById(R.id.pointQty);

        //instantiate the buttonimages (the up and down arrows)
        final ImageButton upvoteButton= (ImageButton) findViewById(R.id.upvote_button);
       // upvoteButton.setColorFilter(Color.argb(255, 0, 50, 180));
        final ImageButton downvoteButton= (ImageButton) findViewById(R.id.downvote_button);
      //  downvoteButton.setColorFilter(Color.argb(255, 180, 0, 0));


        //display correct pointage
        pointQty.setText(Integer.toString(pointValue));

        //occurs when up arrow is pressed
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //If no votes yet, upvoting will add a point.
                //(up arrow becomes blue)
                if(vote == Vote.UNSELECTED) {
                    upvoteButton.setColorFilter(getResources().getColor(R.color.blue));
                    pointQty.setText(Integer.toString(++pointValue));
                    vote = Vote.UPVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if downvoted, upvoting adds 2 points
                //(down arrow becomes black and up becomes blue)
                else if(vote == Vote.DOWNVOTED){
                    upvoteButton.setColorFilter(getResources().getColor(R.color.blue));
                    downvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    pointValue++;
                    pointQty.setText(Integer.toString(++pointValue));
                    vote = Vote.UPVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if already upvoted, upvoting again reverts to unselected, so removes a point
                //(up becomes black)
                else if(vote == Vote.UPVOTED){
                    upvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    pointQty.setText(Integer.toString(--pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database

                }
            }
        });
        //occurs when down arrow is pressed
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //If no votes yet, downvoting will remove a point.
                //down becomes red
                if(vote == Vote.UNSELECTED) {
                    downvoteButton.setColorFilter(getResources().getColor(R.color.red));
                    pointQty.setText(Integer.toString(--pointValue));
                    vote = Vote.DOWNVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
            }
            //if upvoted, downvoting removes 2 points
                //up becomes black, down becomes red
                else if(vote == Vote.UPVOTED){
                    upvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    downvoteButton.setColorFilter(getResources().getColor(R.color.red));
                    pointValue--;
                    pointQty.setText(Integer.toString(--pointValue));
                    vote = Vote.DOWNVOTED;
                    //TODO assign pointage value to Database
                    // TODO assign vote status to Database
                }
            //if already downvoted, downvoting again reverts to unselected, so adds a point
                //downbecomes black
                else if(vote == Vote.DOWNVOTED) {
                    downvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    pointQty.setText(Integer.toString(++pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
            }
        });
    }


}
