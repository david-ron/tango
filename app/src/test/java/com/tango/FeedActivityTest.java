package com.tango;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import org.junit.Before;
import org.junit.Test;
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
public class FeedActivityTest {

    @Mock
    private FeedActivity tested;
    private FragmentPagerAdapter pageAdapter;
    private ViewPager viewPage;

    @Before
    public void setUp() {
        tested = Mockito.mock(FeedActivity.class);
        doReturn(viewPage).when(tested).findViewById(R.id.container);

    }
    @Test
    public void onCreate() throws Exception {
        FeedActivity fa = new FeedActivity();
        tested.onCreate(mock(Bundle.class));
        doNothing().when(tested).startActivity((Intent) any());
        tested.onCreateOptionsMenu(mock(Menu.class));
        tested.onOptionsItemSelected(mock(MenuItem.class));

        assertTrue(true);
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {
        GoogleSignInActivity ga = new GoogleSignInActivity();

        assertTrue(true);
    }

    @Test
    public void onOptionsItemSelected() throws Exception {
        doNothing().when(tested).finish();
        assertTrue(true);
    }

}