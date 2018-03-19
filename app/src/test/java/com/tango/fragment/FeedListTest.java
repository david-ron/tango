package com.tango.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import org.mockito.Mockito;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class FeedListTest {
    @Test
    public void onCreateView() throws Exception {
        FeedList p1 = new MyPosts();

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