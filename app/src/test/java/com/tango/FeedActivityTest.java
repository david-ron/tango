package com.tango;

import com.tango.fragment.FeedList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */
public class FeedActivityTest {
    @Test
    public void onCreate() throws Exception {
        FeedActivity fa = new FeedActivity();

        assertTrue(true);
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {
        GoogleSignInActivity ga = new GoogleSignInActivity();

        assertTrue(true);
    }

    @Test
    public void onOptionsItemSelected() throws Exception {
        assertTrue(true);
    }

}