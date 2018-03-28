package com.tango.models;

import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

// [START comment_class]
@IgnoreExtraProperties
public class AnswerModel {

    public String uid;
    public String author;
    public String text;
    public int starCount = 0;
    private String Aid;
    public String answerID;
    public String imageAnswerURL;
    public Map<String, Boolean> stars = new HashMap<>();
    public AnswerModel() {
        // Default constructor required for calls to DataSnapshot.getValue(AnswerModel.class)
    }

    public AnswerModel(String uid, String author, String text, String image) {
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.imageAnswerURL= image;

    }

    public void setAidz(String aid) {
        Aid = aid;
    }

    public String getAidz() {
        return Aid;
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

        return result;
    }
    // [END post_to_map]

}
// [END comment_class]
