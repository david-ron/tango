package com.tango.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyTopPosts extends FeedList {

    public MyTopPosts() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // My top posts by number of stars
        String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("user-posts").child(myUserId)
                .orderByChild("starCount");

        return myTopPostsQuery;
    }
}
