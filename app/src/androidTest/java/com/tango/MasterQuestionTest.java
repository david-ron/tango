package com.tango;

import org.junit.After;
import org.junit.Before;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-07.
 */
public class MasterQuestionTest {
    @Rule
    public ActivityTestRule<MasterQuestion> mActivityTestRule = new ActivityTestRule<>(MasterQuestion.class);
    private MasterQuestion mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLunch1(){
        View questionButton = mActivity.findViewById(R.id.newQuestionButton);
        assertNotNull(questionButton);
    }

    @Test
    public void testLunch2(){
        View questionView = mActivity.findViewById(R.id.LinearLayoutQ);
        assertNotNull(questionView);
    }
    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}