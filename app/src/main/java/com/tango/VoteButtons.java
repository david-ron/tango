package com.tango;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Play on 14/02/2018.
 */

public class VoteButtons {

    int pointValue;
    TextView pointValueTextView;
    ImageButton upvoteButton;
    ImageButton downvoteButton;

    enum Vote
    {
        UNSELECTED, UPVOTED, DOWNVOTED;
    }
    //changed to public
    Vote vote;

    VoteButtons(LinearLayout layout) {
       //initial values
        vote = Vote.UNSELECTED;

        //TODO initialize to Database value
        pointValue = 0;

        //TextView
        pointValueTextView = new TextView(layout.getContext());
        pointValueTextView.setText(Integer.toString(pointValue));
        layout.addView(pointValueTextView);

        //Upvote Button
        upvoteButton = new ImageButton(layout.getContext());
        upvoteButton.setImageResource(R.drawable.ic_up_arrow_unselected);
        Drawable d = layout.getContext().getResources().getDrawable(R.drawable.ic_up_arrow_unselected);
        upvoteButton.setLayoutParams(new LinearLayout.LayoutParams(d.getIntrinsicWidth(),d.getIntrinsicHeight()));
        //upvoteButton.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(upvoteButton);
        initializeUpvote(layout.getContext());

        //Downvote Button
        downvoteButton = new ImageButton(layout.getContext());
        downvoteButton.setImageResource(R.drawable.ic_down_arrow_unselected);
        downvoteButton.setLayoutParams(new LinearLayout.LayoutParams(d.getIntrinsicWidth(),d.getIntrinsicHeight()));
        //downvoteButton.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(downvoteButton);
        initializeDownvote(layout.getContext());
    }

    void initializeUpvote(final Context context){

        //occurs when up arrow is pressed
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //If no votes yet, upvoting will add a point.
                //(up arrow becomes blue)
                if(vote == Vote.UNSELECTED) {
                    upvoteButton.setColorFilter(context.getResources().getColor(R.color.blue));
                    pointValueTextView.setText(Integer.toString(++pointValue));
                    vote = Vote.UPVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if downvoted, upvoting adds 2 points
                //(down arrow becomes black and up becomes blue)
                else if(vote == Vote.DOWNVOTED){
                    upvoteButton.setColorFilter(context.getResources().getColor(R.color.blue));
                    downvoteButton.setColorFilter(context.getResources().getColor(R.color.black));
                    pointValue = addOne(pointValue); //changed increment to user method
                    pointValueTextView.setText(Integer.toString(++pointValue));
                    vote = Vote.UPVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if already upvoted, upvoting again reverts to unselected, so removes a point
                //(up becomes black)
                else if(vote == Vote.UPVOTED){
                    upvoteButton.setColorFilter(context.getResources().getColor(R.color.black));
                    pointValueTextView.setText(Integer.toString(--pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database

                }
            }
        });
    }

    void initializeDownvote(final Context context){
        //occurs when down arrow is pressed
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //If no votes yet, downvoting will remove a point.
                //down becomes red
                if(vote == Vote.UNSELECTED) {
                    downvoteButton.setColorFilter(context.getResources().getColor(R.color.red));
                    pointValueTextView.setText(Integer.toString(--pointValue));
                    vote = Vote.DOWNVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if upvoted, downvoting removes 2 points
                //up becomes black, down becomes red
                else if(vote == Vote.UPVOTED){
                    upvoteButton.setColorFilter(context.getResources().getColor(R.color.black));
                    downvoteButton.setColorFilter(context.getResources().getColor(R.color.red));
                    pointValue = minusOne(pointValue); //changed decrement to user method
                    pointValueTextView.setText(Integer.toString(--pointValue));
                    vote = Vote.DOWNVOTED;
                    //TODO assign pointage value to Database
                    // TODO assign vote status to Database
                }
                //if already downvoted, downvoting again reverts to unselected, so adds a point
                //downbecomes black
                else if(vote == Vote.DOWNVOTED) {
                    downvoteButton.setColorFilter(context.getResources().getColor(R.color.black));
                    pointValueTextView.setText(Integer.toString(++pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
            }
        });
    }
    //added user methods to facilitate testing
    public static int addOne(int value){
        int temp = value + 1;
        return temp;
    }
    public static int minusOne(int value){
        int temp = value - 1;
        return temp;
    }
}
