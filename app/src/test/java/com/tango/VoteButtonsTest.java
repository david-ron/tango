package com.tango;

import android.widget.LinearLayout;
import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-14.
 */

@RunWith(MockitoJUnitRunner.class)
public class VoteButtonsTest {

    @Mock
    Context mockContext;

    @Test
    public void initializeUpvote() throws Exception {
        int input = 0;
        int output;
        int expected = 1;
        VoteButtons voteButtons = Mockito.mock(VoteButtons.class);
        voteButtons.vote = VoteButtons.Vote.DOWNVOTED;
        //voteButtons.pointValue = 1;
        //int temp = Integer.parseInt(mockContext.toString());
        //when(mockContext.toString(R.string.)).thenReturn(1);
        voteButtons.initializeUpvote(mockContext);
        //voteButtons.upvoteButton.performClick();
        output = voteButtons.pointValue;

        assertEquals(expected, output);
    }

    @Test
    public void initializeDownvote() throws Exception {

    }

}