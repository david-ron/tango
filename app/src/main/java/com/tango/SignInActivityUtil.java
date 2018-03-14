package com.tango;

/**
 * Created by Hking on 3/14/2018.
 */

public class SignInActivityUtil {


    /**
     * This method checks if the provided string represents a valid email address
     * and returns true if it is.
     * @param email
     * @return
     */

    public boolean isValidEmailAddress (String email){

        boolean hasAtSign = email.indexOf("@") > -1;
        return hasAtSign;
    }

    //
}
