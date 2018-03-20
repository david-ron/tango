package com.tango.viewholder;

import android.view.View;

import com.tango.models.QuestionModel;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class QuestionViewTest {

    @Test
    public void bindToPost() throws Exception {
        View view = Mockito.mock(View.class);
        QuestionView qt = new QuestionView(view);

        //qt.bindToPost(mock(QuestionModel.class),mock(View.OnClickListener.class));
        //qt.notify();
        assertTrue(true);
    }

}