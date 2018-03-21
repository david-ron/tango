package com.tango;

import org.junit.After;
import org.junit.Before;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class QuestionPageActivityTest {
    @Rule
    public ActivityTestRule<QuestionPageActivity> mActivityTestRule = new ActivityTestRule<>(QuestionPageActivity.class);
    private QuestionPageActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch1(){
        View questionButton = mActivity.findViewById(R.id.fab_submit_post);
        assertNotNull(questionButton);
    }

    @Test
    public void testLaunch2(){
        View questionViewTitle = mActivity.findViewById(R.id.field_title);
        assertNotNull(questionViewTitle);
    }

    @Test
    public void testLaunch3(){
        View questionViewBody = mActivity.findViewById(R.id.field_body);
        assertNotNull(questionViewBody);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}