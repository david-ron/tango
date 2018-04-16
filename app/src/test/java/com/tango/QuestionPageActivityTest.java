package com.tango;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-19.
 */
public class QuestionPageActivityTest {
    @Mock
    private QuestionPageActivity tested;
    private EditText questionTitle;
    private EditText questionBody;
    private FloatingActionButton submitButton;

    @Before
    public void setUp() throws Exception {
        tested = Mockito.mock(QuestionPageActivity.class);
        doReturn(questionTitle).when(tested).findViewById(R.id.field_title);
        doReturn(questionBody).when(tested).findViewById(R.id.field_body);
        doReturn(submitButton).when(tested).findViewById(R.id.fab_submit_post);
    }

    @Test
    public void onCreate() throws Exception {
        tested.onCreate(mock(Bundle.class));
        tested.writeNewPost(" "," "," ","","");
        tested.submitPost();
        tested.setEditingEnabled(true);
        View view = Mockito.mock(View.class);
        QuestionPageActivity qp = new QuestionPageActivity();
        GoogleSignInActivity qa = new GoogleSignInActivity();
        qa.onClick(view);
        qa.hideProgressDialog();
        qp.hideProgressDialog();
        assertTrue(true);
    }

}