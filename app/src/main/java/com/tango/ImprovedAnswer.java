package com.tango;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


public class ImprovedAnswer extends AppCompatActivity {
    LinearLayout answerContainer;

    Button testButton;
    Button submit;
    EditText input;
    TextView question;
    TextView imageConfirm;
    String questions;
    ImageButton addImageButton;
    Uri imageInGallery;
    final int PICK_IMAGE = 100;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improved_answer);
        Intent intent = getIntent();
        questions= intent.getStringExtra("questions");
        question = (TextView) findViewById(R.id.question);
        question.setText(questions);
        answerContainer = (LinearLayout) findViewById(R.id.LinearLayout);
        testButton = (Button) findViewById(R.id.testButton);
        submit = (Button) findViewById(R.id.SubmitID);
        imageConfirm = (TextView) findViewById(R.id.imageConfirm);
        input = (EditText) findViewById(R.id.input);
        addImageButton = (ImageButton) findViewById(R.id.addImageButton);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               i++;
               Fragment_Answer comment = new Fragment_Answer();
               Bundle args = new Bundle();
               Bundle stringBundle = new Bundle();
               stringBundle.putString("input", "This is random Answer #" +i);
               args.putBundle("strings", stringBundle);
               comment.setArguments(args);
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.add(R.id.LinearLayout, comment);
               ft.commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                Fragment_Answer comment = new Fragment_Answer();
                Bundle args = new Bundle();
                Bundle stringBundle = new Bundle();
                stringBundle.putString("input", input.getText().toString());
                args.putBundle("strings", stringBundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.LinearLayout, comment);
                ft.commit();
                if(imageInGallery != null){
                    Bundle imageBundle = new Bundle();
                    imageBundle.putString("imageUri",imageInGallery.toString());
                    args.putBundle("images", imageBundle);
                    comment.setArguments(args);
                    imageInGallery = null;
                    imageConfirm.setText("");
                } else{
                    comment.setArguments(args);
                }
                input.setText("");
            }
        });

        imageConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageConfirm.getText() != ""){
                    imageInGallery = null;
                    imageConfirm.setText("");
                }
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(); //Find the image you want
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);  // Only 100 images will appear when gallery is opened because PICK_IMAGE=100
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageInGallery = data.getData();
            imageConfirm.setText("image will be included on submit. (click here to cancel)");
        }
    }

}
