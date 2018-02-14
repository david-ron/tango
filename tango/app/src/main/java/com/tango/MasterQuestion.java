package com.tango;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;





/**
 * Created by daveb on 2018-02-04.
 */


public class MasterQuestion extends BaseActivity implements
        View.OnClickListener{
    private Button  newQuestionButton;
    private ArrayList<TextView> Questions;
    private EditText futureQuestion;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private LinearLayoutCompat LinearLayoutQ;
    private final AppCompatActivity activity = MasterQuestion.this;
    //tempstatic var
    public static int counterTextView;

    public static String questions="";

       @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterquestion);
        getSupportActionBar().setTitle("");
        mAuth = FirebaseAuth.getInstance();
        LinearLayoutQ = (LinearLayoutCompat)findViewById(R.id.LinearLayoutQ);
        futureQuestion = (EditText)findViewById(R.id.futureQuestion);
        newQuestionButton = (Button)findViewById(R.id.newQuestionButton);
       newQuestionButton.setOnClickListener(onClick());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        TextView textView = new TextView(this);
        textView.setText("New text");
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }


    private OnClickListener onClick() {
            return new OnClickListener() {
                @Override
                public void onClick(View v) {

                   switch (v.getId()) {
                       case R.id.newQuestionButton:
                           LinearLayoutQ.addView(createNewTextView(futureQuestion.getText().toString()));
                           break;
                       default:

                           TextView textquestion= (TextView) v;
                       questions= textquestion.getText().toString();   // get the text of the question and assign to a string

                           Intent intentAnswers = new Intent(MasterQuestion.this, AnsweringQuestion.class); // redirecting to answer page
                           intentAnswers.putExtra("questions",questions); // Transfering the string to the answer page
                           startActivity(intentAnswers);

                   }
                }
            };
    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }

                });
        Intent signinGoogle = new Intent(activity, GoogleSignInActivity.class);
        startActivity(signinGoogle);

    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_out_button) {
            signOut();

        }
    }
    OnClickListener btnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

        }

    };

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {

            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        }
    }
private TextView createNewTextView(String text) {
    final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    final TextView textView = new TextView(this);
    textView.setLayoutParams(lparams);
    textView.setText(Html.fromHtml("<h3>" +text +"</h3>"));
    textView.setTag("" + counterTextView);
    textView.setId(counterTextView);


    counterTextView++;
    textView.setClickable(true);
    textView.setOnClickListener(onClick());
  // INSERT NEW textView into DB here
    return textView;
    }



}
