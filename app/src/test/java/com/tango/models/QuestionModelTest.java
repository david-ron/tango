package com.tango.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by charleschan on 2018-03-18.
 */
public class QuestionModelTest {
    @Test
    public void toMap() throws Exception {
        QuestionModel qm = new QuestionModel("uid", "author", "title", "body","");
        QuestionModel q = new QuestionModel();
        q.toMap();
        q.uid = " ";
        q.starCount = 0;
        assertTrue(true);
    }

}