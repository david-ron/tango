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

    @Test
    public void addOne() throws Exception {
        int input = 0;
        int output;
        int expected = 1;
        VoteButtons voteButtons = Mockito.mock(VoteButtons.class);
        output = voteButtons.addOne(input);
        assertEquals(expected, output, 0.1);
    }

    @Test
    public void minusOne() throws Exception {
        int input = 0;
        int output;
        int expected = -1;
        VoteButtons voteButtons = Mockito.mock(VoteButtons.class);
        output = voteButtons.minusOne(input);
        assertEquals(expected, output, 0.1);
    }

}