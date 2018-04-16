package com.tango;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tango.models.AnswerModel;
import com.tango.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfilePage extends AppCompatActivity {

    ImageView profilePicture;
    Button button;
    TextView username;
    TextView email;
    private static int PICK_IMAGE = 100;
    private static final int RC_PHOTO_PICKER = 2;
    Uri imageInGallery;           // This is the image inside the gallery

    //Access to Firebase
    private FirebaseAuth mAuth;

    // Firebase instance for image storage
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mProfilePictureReference;
    public HashMap<String, Uri> usersWithProfilePicture = new HashMap();

    public String userEmail="";
    public String userPictureUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        profilePicture = (ImageView) findViewById(R.id.profilePicture);
        button = (Button) findViewById(R.id.changepicture);

        // Get the current user
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        /**
         * profile_picture contains the Profile pictures
         */

        mFirebaseStorage = FirebaseStorage.getInstance();
        mProfilePictureReference = mFirebaseStorage.getReference().child("profile_picture");

        // Setting username and email
        username.setText(usernameFromEmail(user.getEmail()));
        email.setText(user.getEmail());

        StorageReference picturesReference = mProfilePictureReference.child(user.getEmail());
        picturesReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                final String url = downloadUrl.toString();
                Glide.with(profilePicture.getContext()).load(url).into(profilePicture);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                int errorCode = ((StorageException) exception).getErrorCode();
                String errorMessage = exception.getMessage();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            imageInGallery = data.getData();

            mAuth = FirebaseAuth.getInstance();
            final FirebaseUser userForProfilePicture = mAuth.getCurrentUser();
           // profilePicture.setImageURI(imageInGallery);   // Set the profile picture to rhe image selected in the gallery

            /**
             assume the image is located at Oumar/Gallery/Photos/WinterSelfie
             The method getLastPathSegment will give WinterSelfie as a result

             The first line is for making a reference to store the image at "comment_photos"
             The second line is for uploading the image to Firebase Storage
             **/
            StorageReference picturesReference = mProfilePictureReference.child(userForProfilePicture.getEmail());
            picturesReference.putFile(imageInGallery).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // When the image has successfully uploaded, we get its download URL
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();

//                            // Set the download URL to the message box, so that the user can send it to the database
//                            AnswerModel ImageAsAnswer = new AnswerModel(null, authorName, downloadUrl.toString());
//                            mMessagesDatabaseReference.push().setValue(imageAsAnswer);

                    Glide.with(profilePicture.getContext()).load(downloadUrl.toString()).into(profilePicture);
                    final String uid = getUid();
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Get user information
                                    User user = dataSnapshot.getValue(User.class);
                                    user.setProfilePictureUrl(downloadUrl.toString());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                }
            });
        }
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
