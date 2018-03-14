package com.tango;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SignInActivityUtilTest {

    private SignInActivityUtil utils;

    @Before
    public void setUp(){
        utils = new SignInActivityUtil();
    }

    @Test
    public void aValidEmailAddressPasses() throws Exception {
        assertTrue(utils.isValidEmailAddress("foo@bar.com"));
    }

    @Test
    public void anInvalidEmailAddressFails() throws Exception{
        assertTrue(!utils.isValidEmailAddress("Invalid"));


    }
}