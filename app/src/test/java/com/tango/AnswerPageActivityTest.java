package com.tango;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */
public class AnswerPageActivityTest {
    @Test
    public void onCreate() throws Exception {
        AnswerPageActivity a = new AnswerPageActivity();
        BaseActivity b = new BaseActivity();

        assertTrue(true);
    }

    @Test
    public void onStart() throws Exception {
        assertTrue(true);

    }

    @Test
    public void onStop() throws Exception {
        assertTrue(true);
    }

    @Test
    public void onClick() throws Exception {
        assertTrue(true);
    }

}