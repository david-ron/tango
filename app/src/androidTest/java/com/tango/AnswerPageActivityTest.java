package com.tango;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hking on 3/21/2018.
 */
public class AnswerPageActivityTest {

    @Rule
    public ActivityTestRule<ProfilePage> mActivityTestRule = new ActivityTestRule<>(ProfilePage.class);
    private ProfilePage mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch1() {
        View AnswerField = mActivity.findViewById(R.id.field_comment_text);
        assertNull(AnswerField);
    }

    @Test
    public void testLaunch2() {
        View AutherView = mActivity.findViewById(R.id.post_author);
        assertNull(AutherView);
    }

    @Test
    public void testLaunch3() {
        View questionTitleView = mActivity.findViewById(R.id.post_title);
        assertNull(questionTitleView);
    }

    @Test
    public void testLaunch4() {
        View questionBodyView = mActivity.findViewById(R.id.post_body);
        assertNull(questionBodyView);
    }

    @Test
    public void testLaunch5() {
        View postAnswerButton = mActivity.findViewById(R.id.button_post_comment);
        assertNull(postAnswerButton);
    }

    @Test
    public void testLaunch6() {
        View answerRecyclerView = mActivity.findViewById(R.id.recycler_comments);
        assertNull(answerRecyclerView);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}