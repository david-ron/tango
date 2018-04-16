package com.tango.models;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class AnswerModelTest {
    @Test
    public void setAidz() throws Exception {
        String input = "Question test";
        AnswerModel a = new AnswerModel();
        a.starCount = 0;
        a.toMap();
        String output;
        String expected = "Question test";
        AnswerModel qt = new AnswerModel("1234", "author", "text","");
        qt.setAidz(input);
        output = qt.getAidz();
        assertEquals(expected, output);
    }

    @Test
    public void getAidz() throws Exception {
        String input = "Question test";
        String output;
        String expected = "Question test";
        AnswerModel qt = new AnswerModel("1234", "author", "text","");
        qt.setAidz(input);
        output = qt.getAidz();
        assertEquals(expected, output);
    }

    @Test
    public void toMap() throws Exception {
        assertTrue(true);
    }
}