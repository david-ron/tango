package com.tango;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tango.models.AnswerModel;
import com.tango.models.QuestionModel;
import com.tango.models.User;

import java.util.ArrayList;
import java.util.List;

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

        // Initialize Views
        authorView = findViewById(R.id.post_author);
        questionTitleView = findViewById(R.id.post_title);
        questionBodyView = findViewById(R.id.post_body);
        answerField = findViewById(R.id.field_comment_text);
        postAnswerButton = findViewById(R.id.button_post_comment);
        answerRecyclerView = findViewById(R.id.recycler_comments);

        postAnswerButton.setOnClickListener(this);
        answerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    // Add new answer to the DB
    private void postAnswer() {
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
                        AnswerModel answerModel = new AnswerModel(uid, authorName, commentText);

                        // Push the answerModel, it will appear in the list
                        answerRef.push().setValue(answerModel);

                        // Clear the field
                        answerField.setText(null);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private static class AnswerViewHolder extends RecyclerView.ViewHolder {

        public TextView authorView;
        public TextView bodyView;

        public AnswerViewHolder(View itemView) {
            super(itemView);

            authorView = itemView.findViewById(R.id.comment_author);
            bodyView = itemView.findViewById(R.id.comment_body);
        }
    }

    private static class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mCommentIds = new ArrayList<>();
        private List<AnswerModel> mAnswerModels = new ArrayList<>();

        public AnswerAdapter(final Context context, DatabaseReference ref) {
            mContext = context;
            mDatabaseReference = ref;

            // Create child event listener
            // [START child_event_listener_recycler]
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                    // A new answerModel has been added, add it to the displayed list
                    AnswerModel answerModel = dataSnapshot.getValue(AnswerModel.class);

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    mCommentIds.add(dataSnapshot.getKey());
                    mAnswerModels.add(answerModel);
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

        @Override
        public void onBindViewHolder(AnswerViewHolder holder, int position) {
            AnswerModel answerModel = mAnswerModels.get(position);
            holder.authorView.setText(answerModel.author);
            holder.bodyView.setText(answerModel.text);
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
