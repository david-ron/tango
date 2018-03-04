package com.tango;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Daniel on 03/03/2018.
 */

public class Fragment_Answer extends Fragment {

    int pointValue;
    private TextView answerText;
    private TextView pointValueTextView;
    private ImageButton upvoteButton;
    private ImageButton downvoteButton;
    private RadioButton acceptButton;
    private RadioButton denyButton;

    enum Vote
    {
        UNSELECTED, UPVOTED, DOWNVOTED;
    }
    private Vote vote;

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
        //TODO initialize answerText to database value
        pointValueTextView = (TextView) view.findViewById(R.id.pointValueTextView);
        pointValueTextView.setText(Integer.toString(pointValue));

        //Upvote/Downvote Buttons
        upvoteButton = (ImageButton) view.findViewById(R.id.upvoteButton);
        downvoteButton = (ImageButton) view.findViewById(R.id.downvoteButton);

        //accept deny buttons
        acceptButton = (RadioButton) view.findViewById(R.id.acceptButton);
        denyButton = (RadioButton) view.findViewById(R.id.denyButton);

        return view;
    }


}
