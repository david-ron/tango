package com.tango.models;

import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START comment_class]
@IgnoreExtraProperties
public class AnswerModel {

    public String uid;
    public String author;
    public String text;
    public int starCount = 0;
    private String aid;
    public String answerID;
    public String imageAnswerURL;
    public String profilePicture;
    public Map<String, Boolean> stars = new HashMap<>();

    public AnswerModel() {
        // Default constructor required for calls to DataSnapshot.getValue(AnswerModel.class)
    }
    public AnswerModel(String uid, String author, String text, String profilePicture){
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.imageAnswerURL= null;
        this.profilePicture = profilePicture;
    }
    public AnswerModel(String uid, String author, String text, String image, String profilePicture) {
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.imageAnswerURL= image;
        this.profilePicture= profilePicture;

    }

    public void setAidz(String aid) {
        this.aid = aid;
    }

    public String getAidz() {
        return aid;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("image", imageAnswerURL);
        result.put("profilePicture", profilePicture);
        return result;
    }
    // [END post_to_map]

    public String getImageAnswerURL() {
        return imageAnswerURL;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture){
        this.profilePicture=profilePicture;
    }

}
// [END comment_class]
