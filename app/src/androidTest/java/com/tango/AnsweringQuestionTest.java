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
public class AnsweringQuestionTest {

    @Rule
    public ActivityTestRule<AnsweringQuestion> mActivityTestRule = new ActivityTestRule<>(AnsweringQuestion.class);
    private AnsweringQuestion mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void testLunchReply(){
        View view = mActivity.findViewById(R.id.reply);
        assertNotNull(view);

    }

    @Test
    public void testLunchSubmit(){
        View view = mActivity.findViewById(R.id.submit);
        assertNotNull(view);

    }

    @Test
    public void testLunchTextbox(){
        View view = mActivity.findViewById(R.id.textBox);
        assertNotNull(view);

    }
    @Test
    public void testLunchquestion(){
        View view = mActivity.findViewById(R.id.questionInAnswerPage);
        assertNotNull(view);

    }
    @Test
    public void testLunchacceptbutton(){
        View view = mActivity.findViewById(R.id.acceptButton);
        assertNotNull(view);

    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}