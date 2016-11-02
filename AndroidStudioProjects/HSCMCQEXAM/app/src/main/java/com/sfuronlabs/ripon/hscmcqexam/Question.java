package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */
public class Question {
    private int id;
    private String ques;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct;
    private String hardness;

    public Question(int id, String ques, String option1, String option2,
                    String option3, String option4, String correct, String hardness) {

        this.id = id;
        this.ques = ques;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct = correct;
        this.hardness = hardness;
    }

    public Question(String ques, String option1, String option2,
                    String option3, String option4, String correct) {
        this.ques = ques;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct = correct;
    }

    public Question() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", ques=" + ques + ", option1=" + option1
                + ", option2=" + option2 + ", option3=" + option3
                + ", option4=" + option4 + ", correct=" + correct
                + ", hardness=" + hardness + "]";
    }


}
