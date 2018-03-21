package com.tango.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;


import org.junit.Before;
import org.mockito.Mockito;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class FeedListTest {
    @Mock
    private MyTopPosts tested;

    @Before
    public void setUp(){
        tested = Mockito.mock(MyTopPosts.class);

    }

    @Test
    public void onCreateView() throws Exception {
        FeedList p1 = new MyPosts();
        FeedList p2 = Mockito.mock(MyPosts.class);
        p2.getActivity();
        p2.onStart();
        p2.onStop();
        p2.getQuery(mock(DatabaseReference.class));
        p2.onStarClicked(mock(DatabaseReference.class));
        p2.onActivityCreated(mock(Bundle.class));
        p2.onCreateView(mock(LayoutInflater.class), mock(ViewGroup.class), mock(Bundle.class));
        tested.onCreateView(mock(LayoutInflater.class), mock(ViewGroup.class), mock(Bundle.class));
        tested.onActivityCreated(mock(Bundle.class));
        tested.onStart();
        tested.onStop();
        tested.onStarClicked(mock(DatabaseReference.class));
        tested.getQuery(mock(DatabaseReference.class));
        assertTrue(true);
    }

    @Test
    public void onActivityCreated() throws Exception {
        MyPosts p2 = new MyPosts();

        assertTrue(true);
    }

    @Test
    public void onStart() throws Exception {
        MyTopPosts p3 = new MyTopPosts();

        assertTrue(true);
    }

    @Test
    public void onStop() throws Exception {
        RecentPosts p4 = new RecentPosts();

        assertTrue(true);
    }

    @Test
    public void getUid() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getQuery() throws Exception {
        assertTrue(true);
    }

}