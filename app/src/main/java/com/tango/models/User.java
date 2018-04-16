package com.tango.models;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String profilePictureUrl;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String profilePictureUrl) {
        this.username = username;
        this.email = email;
        this.profilePictureUrl=profilePictureUrl;
    }

    public void setProfilePictureUrl(String url){
        this.profilePictureUrl= url;
    }
    public String getProfilePictureUrl(){
        return this.profilePictureUrl;
    }

//    public String getProfilePictureUrl(){
//        FirebaseStorage mFirebaseStorage;
//        StorageReference mProfilePictureReference;
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        final FirebaseUser userPP = mAuth.getCurrentUser();
//        mFirebaseStorage = FirebaseStorage.getInstance();
//        mProfilePictureReference = mFirebaseStorage.getReference().child("profile_picture");
//        StorageReference picturesReference = mProfilePictureReference.child(userPP.getEmail());
//        picturesReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
//        {
//            @Override
//            public void onSuccess(Uri downloadUrl)
//            {
//                String profilePictureUrl= downloadUrl.toString();
//                User.profilePictureUrl=profilePictureUrl;
//
//            }
//        });
//        return profilePictureUrl;
//    }

}
// [END blog_user_class]
