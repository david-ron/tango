package com.tango;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-14.
 */
public class QuestionTest {
    @Test
    public void getQuestion() throws Exception {
        String input = "Question test";
        String output;
        String expected = "Question test";
        //Question qt = Mockito.mock(Question.class);
        Question qt = new Question(input, "");
        output = qt.getQuestion();
        assertEquals(expected, output);
    }

    @Test
    public void getOwnerID() throws Exception {
        String input = "Question test";
        String output;
        String expected = "Question test";
        Question qt = new Question("", input);
        output = qt.getOwnerID();
        assertEquals(expected, output);

    }

}