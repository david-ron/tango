package com.tango;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class FeedActivityTest {


    @Rule
    public ActivityTestRule<FeedActivity> mActivityTestRule = new ActivityTestRule<>(FeedActivity.class);
    private FeedActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch1(){
        View tab = mActivity.findViewById(R.id.container);
        assertNotNull(tab);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}