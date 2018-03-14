package com.tango;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.mockito.Mockito;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-14.
 */
public class Fragment_AnswerTest {
    @Test
    public void onCreate() throws Exception {
    }

    @Test
    public void onCreateView() throws Exception {
    }

    @Test
    public void initializeUpvote() throws Exception {
        int input = 0;
        int output;
        int expected = 0;
        Fragment_Answer answer = Mockito.mock(Fragment_Answer.class);
        //answer.onCreate(Mockito.isA(Bundle.class));
        //answer.onCreateView(Mockito.isA(LayoutInflater.class), Mockito.isA(ViewGroup.class), Mockito.isA(Bundle.class));
        answer.initializeUpvote();
        //answer.upvoteButton.callOnClick();
        output = answer.pointValue;
        assertEquals(expected, output);
    }

    @Test
    public void initializeDownvote() throws Exception {
    }

    @Test
    public void initializeRadio() throws Exception {
    }

    @Test
    public void initializeUsername() throws Exception {
    }

}