package com.tango;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hking on 3/6/2018.
 */
public class GoogleSignInActivityTest {

    @Rule
    public ActivityTestRule<GoogleSignInActivity> mActivityTestRule = new ActivityTestRule<GoogleSignInActivity>(GoogleSignInActivity.class);
    private GoogleSignInActivity mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void testLunchTitle(){
        View view = mActivity.findViewById(R.id.title_text);
        assertNotNull(view);

    }

    @Test
    public void testLunchsigninbutton(){
        View view = mActivity.findViewById(R.id.sign_in_button);
        assertNotNull(view);

    }




    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}