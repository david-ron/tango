package com.tango;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.tango.models.QuestionModel;
import com.tango.models.User;

import java.util.HashMap;
import java.util.Map;

public class QuestionPageActivity extends BaseActivity {

    private static final String TAG = "QuestionPageActivity";
    private static final String REQUIRED = "Required";

    // Initialize DataBase
    private DatabaseReference rootDB;


    private EditText questionTitle;
    private EditText questionBody;
    private FloatingActionButton submitButton;
    private ImageView profilePictureQuestion;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mProfilePictureReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        mFirebaseStorage = FirebaseStorage.getInstance();
        mProfilePictureReference = mFirebaseStorage.getReference().child("profile_picture");

        // Set rootDB to root of FireBase DataBase
        rootDB = FirebaseDatabase.getInstance().getReference();

        // Initialize Fields
        questionTitle = findViewById(R.id.field_title);
        questionBody = findViewById(R.id.field_body);
        submitButton = findViewById(R.id.fab_submit_post);
        profilePictureQuestion = findViewById(R.id.post_author_photo);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    /**
     * SubmitPost()
     * Checks if all fields have a valid input / not Empty
     * Add new question to the DataBase
     */
    public void submitPost() {
        final String title = questionTitle.getText().toString();
        final String body = questionBody.getText().toString();

        // Tried this to glide the profile picture but oddly it cannot find the view
//        FirebaseAuth mAuth;
//        mAuth = FirebaseAuth.getInstance();
//        final FirebaseUser user = mAuth.getCurrentUser();
//        StorageReference picturesReference = mProfilePictureReference.child(user.getEmail());
//        picturesReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
//        {
//            @Override
//            public void onSuccess(Uri downloadUrl)
//            {
//                final String url = downloadUrl.toString();
//                Glide.with(profilePictureQuestion.getContext()).load(url).into(profilePictureQuestion);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//                int errorCode = ((StorageException) exception).getErrorCode();
//                String errorMessage = exception.getMessage();
//            }
//        });

        // Title is required
        if (TextUtils.isEmpty(title)) {
            questionTitle.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            questionBody.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // Create an EventListener that will write the new question to DB
        final String userId = getUid();
        rootDB.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(QuestionPageActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new question
                            writeNewPost(userId, user.username, title, body, user.getProfilePictureUrl());
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());

                        setEditingEnabled(true);
                    }
                });
    }

    public void setEditingEnabled(boolean enabled) {
        questionTitle.setEnabled(enabled);
        questionBody.setEnabled(enabled);
        if (enabled) {
            submitButton.setVisibility(View.VISIBLE);
        } else {
            submitButton.setVisibility(View.GONE);
        }
    }

    // Writes the actual post to the DB
    public void writeNewPost(String userId, String username, String title, String body, String profilePicture) {
        // Create new questionModel at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = rootDB.child("posts").push().getKey();
        QuestionModel questionModel = new QuestionModel(userId, username, title, body, profilePicture);
        Map<String, Object> postValues = questionModel.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        rootDB.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
