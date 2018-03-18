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
public class AnswerPageActivityTest {

    @Rule
    public ActivityTestRule<AnswerPageActivity> mActivityTestRule = new ActivityTestRule<>(AnswerPageActivity.class);
    private AnswerPageActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch1(){
        View answerTitle = mActivity.findViewById(R.id.comment_form);
        assertNotNull(answerTitle);
    }

    @Test
    public void testLaunch2(){
        View answerBody = mActivity.findViewById(R.id.field_comment_text);
        assertNotNull(answerBody);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}