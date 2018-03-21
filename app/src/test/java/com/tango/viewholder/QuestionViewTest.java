package com.tango.viewholder;

import android.view.View;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class QuestionViewTest {

    @Test
    public void bindToPost() throws Exception {
        View view = Mockito.mock(View.class);
        QuestionView qt = new QuestionView(view);
        assertTrue(true);
    }

}