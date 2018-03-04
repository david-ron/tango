package com.tango;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by Daniel on 03/03/2018.
 */

public class Fragment_Answer extends Fragment {

    int pointValue;
    String answer;
    private TextView answerText;

    private TextView pointValueTextView;
    private ImageButton upvoteButton;
    private ImageButton downvoteButton;
    private RadioButton acceptButton;
    private RadioButton denyButton;
    private RadioGroup radioGroup;
    enum Vote
    {
        UNSELECTED, UPVOTED, DOWNVOTED;
    }
    private Vote vote;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        answer = getArguments().getString("input","No string entered");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_answer, container, false);

        //initial values
        vote = Vote.UNSELECTED;

        //TODO initialize to Database value
        pointValue = 0;

        //TextView for points and text
        answerText = (TextView) view.findViewById(R.id.answerText);
        answerText.setText(answer);
        //TODO initialize answerText to database value
        pointValueTextView = (TextView) view.findViewById(R.id.pointValueTextView);
        pointValueTextView.setText(Integer.toString(pointValue));

        //Upvote/Downvote Buttons
        upvoteButton = (ImageButton) view.findViewById(R.id.upvoteButton);
        downvoteButton = (ImageButton) view.findViewById(R.id.downvoteButton);
        initializeUpvote();
        initializeDownvote();

        //accept deny buttons
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        acceptButton = (RadioButton) view.findViewById(R.id.acceptButton);
        denyButton = (RadioButton) view.findViewById(R.id.denyButton);
        initializeRadio();

        return view;
    }

    void initializeUpvote(){
        //occurs when up arrow is pressed
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //If no votes yet, upvoting will add a point.
                //(up arrow becomes blue)
                if(vote == Vote.UNSELECTED) {
                    upvoteButton.setColorFilter(getResources().getColor(R.color.blue));
                    pointValueTextView.setText(Integer.toString(++pointValue));
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
                    pointValueTextView.setText(Integer.toString(++pointValue));
                    vote = Vote.UPVOTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
                //if already upvoted, upvoting again reverts to unselected, so removes a point
                //(up becomes black)
                else if(vote == Vote.UPVOTED){
                    upvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    pointValueTextView.setText(Integer.toString(--pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database

                }
            }
        });
    }
    void initializeDownvote(){
        //occurs when down arrow is pressed
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //If no votes yet, downvoting will remove a point.
                //down becomes red
                if(vote == Vote.UNSELECTED) {
                    downvoteButton.setColorFilter(getResources().getColor(R.color.red));
                    pointValueTextView.setText(Integer.toString(--pointValue));
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
                    pointValueTextView.setText(Integer.toString(--pointValue));
                    vote = Vote.DOWNVOTED;
                    //TODO assign pointage value to Database
                    // TODO assign vote status to Database
                }
                //if already downvoted, downvoting again reverts to unselected, so adds a point
                //downbecomes black
                else if(vote == Vote.DOWNVOTED) {
                    downvoteButton.setColorFilter(getResources().getColor(R.color.black));
                    pointValueTextView.setText(Integer.toString(++pointValue));
                    vote = Vote.UNSELECTED;
                    //TODO assign pointage value to Database
                    //TODO assign vote status to Database
                }
            }
        });
    }
    void initializeRadio(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //TODO ACCESS DATAASE as needed per button pressed
            }
        });
    };
    public void setText(String text){
        answerText.setText(text);
    }
}
