package com.tango.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyPosts extends FeedList {

    public MyPosts() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("user-posts")
                .child(getUid());
    }
}
