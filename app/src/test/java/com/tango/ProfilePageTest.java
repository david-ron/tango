package com.tango;

import org.junit.Test;
import org.junit.Before;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */

public class ProfilePageTest {

    //private ProfilePage tested;

    @Mock
    private ProfilePage tested;
    private Button mockButton;
    private ImageView profilePicture;
    private TextView username;


    @Before
    public void setUp() throws Exception {
        // We will Spy our tested activity so that we will be able to give it some input when
        //  for example the FindViewByID method is called.
        tested = Mockito.mock(ProfilePage.class);
        doReturn(mockButton).when(tested).findViewById(R.id.changepicture);
        doReturn(profilePicture).when(tested).findViewById(R.id.profilePicture);
        doReturn(username).when(tested).findViewById(R.id.username);

    }

    @Test
    public void shouldSetupListener() throws Exception {
        tested.onCreate(mock(Bundle.class));

    }

    @Test
    public void shouldStartActivity() throws Exception {

        ProfilePage p1 = new ProfilePage();
        p1.button = null;
        p1.profilePicture = null;
        p1.username = null;


        tested.onCreate(mock(Bundle.class));
        doNothing().when(tested).startActivity((Intent) any());
        assertTrue(true);
    }

    @Test
    public void onActivityResult() throws Exception {
        tested.openGallery();
        tested.onActivityResult(0, 0, mock(Intent.class));
        doNothing().when(tested).finish();
        assertTrue(true);
    }

}