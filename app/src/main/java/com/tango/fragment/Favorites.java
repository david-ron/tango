package com.tango.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tango.models.User;

/**
 * Created by e_khei on 2018-04-14.
 */

public class Favorites extends FeedList {
    public Favorites() {
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // Last 100 posts
        String myUserID = getUid();
        Query favoritepostsQuery = databaseReference.child("posts").orderByChild("stars/"+myUserID).equalTo(true);

        return favoritepostsQuery;
    }
}
