package com.tango;

/**
 * Created by David on 2018-02-09.
 */

public class Question {
    private String question;
    // assuming we need to keep track of who created the question
    private String ownerID;
    private int score;
    Question(){
        question = "";
        ownerID = "";
        score = 0;
    }
    Question(String question, String ownerID){
        this.question = question;
        this.ownerID = ownerID;
        //this.score = score;
    }
    public String getQuestion(){
        return question;
    }
    public String getOwnerID(){
        return ownerID;
    }

    public int getScore() {
        return score;
    }
}
