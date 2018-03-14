package com.tango;

import android.content.Context;
import android.widget.LinearLayout;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-14.
 */
public class VoteButtonsTest {
    //@Mock
    //Context mockContext;

    @Test
    public void addOne() throws Exception {
        int input = 0;
        int output;
        int expected = 1;
        //VoteButtons voteButtons = new VoteButtons(LinearLayout.class);
        VoteButtons voteButtons = Mockito.mock(VoteButtons.class);
        //voteButtons.pointValue = 1;
        //int temp = Integer.parseInt(mockContext.toString());
        //when(mockContext.toString(R.string.)).thenReturn(1);
        //voteButtons.initializeUpvote(mockContext);
        //voteButtons.upvoteButton.performClick();
        output = voteButtons.addOne(input);
        //output = voteButtons.pointValue;

        assertEquals(expected, output, 0.1);
    }

    @Test
    public void minusOne() throws Exception {
        int input = 0;
        int output;
        int expected = -1;
        VoteButtons voteButtons = Mockito.mock(VoteButtons.class);
        //voteButtons.pointValue = 1;
        //int temp = Integer.parseInt(mockContext.toString());
        //when(mockContext.toString(R.string.)).thenReturn(1);
        //voteButtons.initializeUpvote(mockContext);
        //voteButtons.upvoteButton.performClick();
        output = voteButtons.minusOne(input);
        //output = voteButtons.pointValue;

        assertEquals(expected, output, 0.1);
    }

}