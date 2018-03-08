package com.tango;

import org.junit.After;
import org.junit.Before;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-07.
 */
public class ProfilePageTester {

    @Rule
    public ActivityTestRule<ProfilePage> mActivityTestRule = new ActivityTestRule<ProfilePage>(ProfilePage.class);
    private ProfilePage mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLunch1(){
        View userNameView = mActivity.findViewById(R.id.username);
        assertNotNull(userNameView);
    }

    @Test
    public void testLunch2(){
        View profilePic = mActivity.findViewById(R.id.profilePicture);
        assertNotNull(profilePic);

    }

    @Test
    public void testLunch3(){
        View picButton = mActivity.findViewById(R.id.changepicture);
        assertNotNull(picButton);

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}