package com.tango;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */
public class ProfilePageTest {
    @Test
    public void onCreate() throws Exception {
        ProfilePage p1 = new ProfilePage();
        p1.button = null;
        p1.profilePicture = null;
        p1.username = null;
        assertTrue(true);
    }

    @Test
    public void onActivityResult() throws Exception {
        assertTrue(true);
    }

}