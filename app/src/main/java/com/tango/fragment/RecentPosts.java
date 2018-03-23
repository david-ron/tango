package com.tango.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class RecentPosts extends FeedList {

    public RecentPosts() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // Last 100 posts
        Query recentPostsQuery = databaseReference.child("posts").limitToFirst(100);

        return recentPostsQuery;
    }
}
