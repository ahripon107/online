package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */
public class DetailsAnswer {
    private String question;
    private String MyAns;
    private String CorrectAns;

    public DetailsAnswer(String question, String myAns, String correctAns) {

        this.question = question;
        this.MyAns = myAns;
        this.CorrectAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMyAns() {
        return MyAns;
    }

    public void setMyAns(String myAns) {
        this.MyAns = myAns;
    }

    public String getCorrectAns() {
        return CorrectAns;
    }

    public void setCorrectAns(String correctAns) {
        this.CorrectAns = correctAns;
    }

    @Override
    public String toString() {
        return "DetailsAnswer [question=" + question + ", MyAns=" + MyAns
                + ", CorrectAns=" + CorrectAns + "]";
    }

}
