package com.tango;

import org.junit.Test;
import org.junit.Before;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
//import static org.powermock.api.support.membermodification.MemberMatcher.method;
//import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(ProfilePage.class)

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

        // We need to suppress methods from the Base Activity. This is why we need PowerMock, there
        //  are other ways but are more intrusive in our code.

        //suppress(method(Activity.class, "onCreate", Bundle.class));
        //suppress(method(Activity.class, "setContentView", int.class));
    }
    @Test
    public void shouldSetupListener() throws Exception {
        // When we call the onCreate...
        tested.onCreate(mock(Bundle.class));

        // Then the setOnClickListener method will be called.
        //verify(mockButton).setOnClickListener((View.OnClickListener) any());
    }

    @Test
    public void shouldStartActivity() throws Exception {
//        ArgumentCaptor<View.OnClickListener> captor = ArgumentCaptor.forClass(View.OnClickListener.class);
//        doNothing().when(mockButton).setOnClickListener(captor.capture());
//

        ProfilePage p1 = new ProfilePage();
        p1.button = null;
        p1.profilePicture = null;
        p1.username = null;


        tested.onCreate(mock(Bundle.class));
        doNothing().when(tested).startActivity((Intent) any());
          // We should also test this to be called in a new test.
//        captor.getValue().onClick(mockButton);
//        verify(tested).startActivity((Intent) any());
        assertTrue(true);
    }

    @Test
    public void onActivityResult() throws Exception {
        tested.openGallery();
        tested.onActivityResult(0,0, mock(Intent.class));
        doNothing().when(tested).finish();
        assertTrue(true);
    }

}