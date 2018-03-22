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
public class ProfilePageTest {
    @Rule
    public ActivityTestRule<ProfilePage> mActivityTestRule = new ActivityTestRule<>(ProfilePage.class);
    private ProfilePage mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch1(){
        View username = mActivity.findViewById(R.id.username);
        assertNotNull(username);
    }

    @Test
    public void testLaunch2(){
        View pictureButton = mActivity.findViewById(R.id.changepicture);
        assertNotNull(pictureButton);
    }

    @Test
    public void testLaunch3(){
        View picture = mActivity.findViewById(R.id.profilePicture);
        assertNotNull(picture);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}