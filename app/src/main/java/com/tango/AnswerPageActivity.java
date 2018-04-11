package com.tango;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tango.models.AnswerModel;
import com.tango.models.QuestionModel;
import com.tango.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnswerPageActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AnswerPageActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference questionRef;
    private DatabaseReference answerRef;
    private ValueEventListener questionListener;
    private String questionKey;
    private AnswerAdapter mAdapter;

    private TextView authorView;
    private TextView questionTitleView;
    private TextView questionBodyView;
    private EditText answerField;
    private Button postAnswerButton;
    private RecyclerView answerRecyclerView;

    // Image answer
    private Button addImageButton;
    public  Uri selectedImage;
    private static final int RC_PHOTO_PICKER = 2;
    public ImageView imageInComment;

    // Firebase instance for image storage
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mImageAnswerReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // Get post key from intent
        questionKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (questionKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        /**
         * posts - Contains questionTitle, questionBody, author, UID
         * post-comments - Contains author, answerText, UID
         */
        questionRef = FirebaseDatabase.getInstance().getReference().child("posts").child(questionKey);
        answerRef = FirebaseDatabase.getInstance().getReference().child("post-comments").child(questionKey);

        /**
         * comment_photos contains the Images
         */
        mFirebaseStorage = FirebaseStorage.getInstance();
        mImageAnswerReference = mFirebaseStorage.getReference().child("comment_photos");

        // Initialize Views
        authorView = findViewById(R.id.post_author);
        questionTitleView = findViewById(R.id.post_title);
        questionBodyView = findViewById(R.id.post_body);
        answerField = findViewById(R.id.field_comment_text);
        postAnswerButton = findViewById(R.id.button_post_comment);
        answerRecyclerView = findViewById(R.id.recycler_comments);

        // Image as an answer button
        addImageButton = (Button) findViewById(R.id.button_image_answer);
        imageInComment = (ImageView) findViewById(R.id.image_in_comment);

        postAnswerButton.setOnClickListener(this);
        answerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Opening gallery to post image
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get QuestionModel object and use the values to update the UI
                QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);

                // Update TextView for Author, Title, Body
                authorView.setText(questionModel.author);
                questionTitleView.setText(questionModel.title);
                questionBodyView.setText(questionModel.body);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting QuestionModel failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(AnswerPageActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        questionRef.addValueEventListener(postListener);

        // Keep copy of post listener so we can remove it when app stops
        questionListener = postListener;

        // Listen for new answers
        mAdapter = new AnswerAdapter(this, answerRef);
        answerRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (questionListener != null) {
            questionRef.removeEventListener(questionListener);
        }

        // Clean up comments listener
        mAdapter.cleanupListener();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_post_comment) {
            postAnswer();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
            selectedImage = data.getData();

            /**
             assume the image is located at Oumar/Gallery/Photos/WinterSelfie
             The method getLastPathSegment will give WinterSelfie as a result

             The first line is for making a reference to store the image at "comment_photos"
             The second line is for uploading the image to Firebase Storage
             **/
            StorageReference picturesReference = mImageAnswerReference.child(selectedImage.getLastPathSegment());
            picturesReference.putFile(selectedImage).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // When the image has successfully uploaded, we get its download URL
                            final Uri downloadUrl = taskSnapshot.getDownloadUrl();

//                            // Set the download URL to the message box, so that the user can send it to the database
//                            AnswerModel ImageAsAnswer = new AnswerModel(null, authorName, downloadUrl.toString());
//                            mMessagesDatabaseReference.push().setValue(imageAsAnswer);

                            final String uid = getUid();
                            FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // Get user information
                                            User user = dataSnapshot.getValue(User.class);
                                            String authorName = user.username;
                                            // Create new answerModel object
                                            String commentText = answerField.getText().toString();
                                            AnswerModel answerModel = new AnswerModel(uid, authorName, commentText, downloadUrl.toString());

                                            Map<String, Object> postValues = answerModel.toMap();
                                            // Push the answerModel, it will appear in the list
                                            answerRef.push().setValue(answerModel);
                                            answerField.setText(null);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                        }
                    });
            }
        }



    // Add new answer to the DB
    public void postAnswer() {
        final String uid = getUid();
        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user information
                        User user = dataSnapshot.getValue(User.class);
                        String authorName = user.username;
                        // Create new answerModel object
                        String commentText = answerField.getText().toString();
                        AnswerModel answerModel = new AnswerModel(uid, authorName, commentText, null);

                        Map<String, Object> postValues = answerModel.toMap();
                        // Push the answerModel, it will appear in the list
                        answerRef.push().setValue(answerModel);
                        //answerModel.answerID = dataSnapshot.getKey();
                        //Log.d("BBBBBBBBBB",dataSnapshot.getRef().);
                        //Log.d("AAAAAAA",answerModel.answerID);
                        //String testers = answerRef.push().setValue(answerModel).toString();
                        //answerModel.setAidz(testers);
                        // Clear the field
                        answerField.setText(null);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    public static class AnswerViewHolder extends RecyclerView.ViewHolder {

        public TextView authorView;
        public TextView bodyView;
        public ImageView starView;
        public TextView numStarsView;
        public ImageView imageInCommentView;
      
        public AnswerViewHolder(View itemView) {
            super(itemView);

            authorView = itemView.findViewById(R.id.comment_author);
            bodyView = itemView.findViewById(R.id.comment_body);
            starView = itemView.findViewById(R.id.star);
            numStarsView = itemView.findViewById(R.id.post_num_stars);
            imageInCommentView = itemView.findViewById(R.id.image_in_comment);


        }

        public void bindToPost(AnswerModel questionModel, View.OnClickListener starClickListener) {

            numStarsView.setText(String.valueOf(questionModel.starCount));


            // Log.d("POSTING", questionModel.toString());
            //Log.d("POSTING-Question", questionModel.title.toString());
            // Log.d("POSTING-Answer", questionModel.body.toString());

            starView.setOnClickListener(starClickListener);
        }
    }

    public static class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {
        private DatabaseReference rootDB;
        private FirebaseRecyclerAdapter<AnswerModel, AnswerViewHolder> nAdapter;
        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;
        private RecyclerView mRecycler;
        private List<String> mCommentIds = new ArrayList<>();
        private List<AnswerModel> mAnswerModels = new ArrayList<>();

        public AnswerAdapter(final Context context, DatabaseReference ref) {
            mContext = context;
            mDatabaseReference = ref;
            rootDB = FirebaseDatabase.getInstance().getReference();
            Query postsQuery = rootDB;
            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<QuestionModel>()
                    .setQuery(postsQuery, QuestionModel.class)
                    .build();
            nAdapter = new FirebaseRecyclerAdapter<AnswerModel, AnswerViewHolder>(options) {

                @Override
                protected void onBindViewHolder(@NonNull AnswerViewHolder holder, final int position, @NonNull AnswerModel model) {
                    final AnswerModel answerModel = mAnswerModels.get(position);


                    holder.authorView.setText(answerModel.author);
                    holder.bodyView.setText(answerModel.text);
                    // Photo as answer
                    //AnswerModel message = getItem(position);
                    boolean isPhoto = answerModel.getImageAnswerURL() != null;
                    if (isPhoto) {
                        //messageTextView.setVisibility(View.GONE);
                        holder.imageInCommentView.setVisibility(View.VISIBLE);
                        Glide.with(holder.imageInCommentView.getContext())
                                .load(answerModel.getImageAnswerURL())
                                .into(holder.imageInCommentView);
                    }

                    //  holder.numStarsView.setText(answerModel.starCount);
                    if (answerModel.stars.containsKey(getUid())) {
                        holder.starView.setImageResource(R.drawable.ic_toggle_star_24);
                    } else {
                        holder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
                    }
                    Log.d("commenterror", answerModel.toString());
                    //  DatabaseReference postRef = FirebaseRecyclerAdapter.getRef(position);
                    holder.bindToPost(answerModel, new View.OnClickListener() {
                        final DatabaseReference postRef = getRef(position);

                        String Key = postRef.getKey();

                        @Override
                        public void onClick(View starView) {
                            // Need to write to both places the post is stored
                            // DatabaseReference globalPostRef = rootDB.child("posts").child(AnswerAdapter.this.toString());


                            Log.d("Keytest", Key.toString());
                            DatabaseReference userPostRef = rootDB.child("post-comments").child(Key);
                            onStarClicked(userPostRef);


                            // Run two transactions
                            // onStarClicked(globalPostRef);
                            //onStarClicked(userPostRef);
                        }
                    });

                }

                @Override
                public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    LayoutInflater inflater = LayoutInflater.from(mContext);
                    View view = inflater.inflate(R.layout.item_comment, parent, false);

                    return new AnswerViewHolder(view);
                }
            };
            // Create child event listener
            // [START child_event_listener_recycler]
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                    // A new answerModel has been added, add it to the displayed list
                    AnswerModel answerModel = dataSnapshot.getValue(AnswerModel.class);
                    answerModel.setAidz(dataSnapshot.getKey());
                    //answerModel.answerID = previousChildName;
                    //Log.d("newtoiwfoiuoiu",previousChildName);
                    // [START_EXCLUDE]
                    // Update RecyclerView
                    mCommentIds.add(dataSnapshot.getKey());
                    mAnswerModels.add(answerModel);
                    answerModel.answerID = dataSnapshot.getRef().getParent().getKey();
                    //answerModel.answerID = previousChildName;
                    // Log.d("TESTINGTHIS",mAnswerModels.toString());
                    //Log.d("TESTTHIS",dataSnapshot.getKey().toString());
                    notifyItemInserted(mAnswerModels.size() - 1);
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so displayed the changed comment.
                    AnswerModel newAnswerModel = dataSnapshot.getValue(AnswerModel.class);
                    String commentKey = dataSnapshot.getKey();
                    //newAnswerModel.answerID = previousChildName;
                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Replace with the new data
                        mAnswerModels.set(commentIndex, newAnswerModel);

                        // Update the RecyclerView
                        notifyItemChanged(commentIndex);
                    } else {
                        Log.w(TAG, "onChildChanged:unknown_child:" + commentKey);
                    }

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    String commentKey = dataSnapshot.getKey();

                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Remove data from the list
                        mCommentIds.remove(commentIndex);
                        mAnswerModels.remove(commentIndex);

                        // Update the RecyclerView
                        notifyItemRemoved(commentIndex);
                    } else {
                        Log.w(TAG, "onChildRemoved:unknown_child:" + commentKey);
                    }
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    AnswerModel movedAnswerModel = dataSnapshot.getValue(AnswerModel.class);
                    String commentKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                    Toast.makeText(mContext, "Failed to load comments.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            ref.addChildEventListener(childEventListener);

            // Store reference to listener so it can be removed on app stop
            mChildEventListener = childEventListener;
        }

        @Override
        public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_comment, parent, false);

            return new AnswerViewHolder(view);
        }

        Query postsQuery = rootDB;
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<AnswerModel>()
                .setQuery(postsQuery, AnswerModel.class)
                .build();


        @Override
        public void onBindViewHolder(AnswerViewHolder holder, final int position) {
            final AnswerModel answerModel = mAnswerModels.get(position);
            Log.d("AnswerMdoel", answerModel.toString());

            //nAdapter = setnAdapter(nAdapter);
            holder.authorView.setText(answerModel.author);
            holder.bodyView.setText(answerModel.text);

            boolean isPhoto = answerModel.getImageAnswerURL() != null;
            if (isPhoto) {
                //messageTextView.setVisibility(View.GONE);
                holder.imageInCommentView.setVisibility(View.VISIBLE);
                Glide.with(holder.imageInCommentView.getContext())
                        .load(answerModel.getImageAnswerURL())
                        .into(holder.imageInCommentView);
            }

          //  holder.numStarsView.setText(answerModel.starCount);

            if (answerModel.stars.containsKey(getUid())) {
                holder.starView.setImageResource(R.drawable.ic_toggle_star_24);
            } else {
                holder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
            }
            Log.d("commenterror", answerModel.uid.toString());

            //  DatabaseReference postRef = FirebaseRecyclerAdapter.getRef(position);
            holder.bindToPost(answerModel, new View.OnClickListener() {


                @Override
                public void onClick(View starView) {
                    // Need to write to both places the post is stored
                    //DatabaseReference globalPostRef = rootDB.child("posts").child(answerModel.answerID).child(answerModel.getAidz());

                    //final int keytest = Log.d("Keytest", answerModel.getAidz().toString());
                    DatabaseReference userPostRef = rootDB.child("post-comments").child(answerModel.answerID).child(answerModel.getAidz());
                    //onStarClicked(userPostRef);


                    // userPostRef.setValue(newCount);

                    // Run two transactions
                    //onStarClicked(globalPostRef);
                    onStarClicked(userPostRef);
                    Log.d("QWERVTB WEQRGEV", answerModel.answerID);
                }
            });

        }

        private void onStarClicked(DatabaseReference postRef) {
            postRef.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    AnswerModel p = mutableData.getValue(AnswerModel.class);
                    if (p == null) {
                        return Transaction.success(mutableData);
                    }

                    if (p.stars.containsKey(getUid())) {
                        // Unstar the post and remove self from stars
                        p.starCount = p.starCount - 1;
                        p.stars.remove(getUid());
                    } else {
                        // Star the post and add self to stars
                        p.starCount = p.starCount + 1;
                        p.stars.put(getUid(), true);
                    }

                    // Set value and report transaction success
                    mutableData.setValue(p);
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b,
                                       DataSnapshot dataSnapshot) {
                    // Transaction completed
                    Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                }
            });
        }


        public String getUid() {
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        @Override
        public int getItemCount() {
            return mAnswerModels.size();
        }

        public void cleanupListener() {
            if (mChildEventListener != null) {
                mDatabaseReference.removeEventListener(mChildEventListener);
            }
        }

    }



}

