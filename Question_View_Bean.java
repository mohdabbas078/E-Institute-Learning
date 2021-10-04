package com.example.online_examination_system.Teacher_Bean;

import java.util.ArrayList;

public class Question_View_Bean
{
    ArrayList<String> questionNumber=new ArrayList<>(),questionName=new ArrayList<>(),optionA=new ArrayList<>(),optionB=new ArrayList<>(),
     optionC=new ArrayList<>(),optionD=new ArrayList<>(),questionCode=new ArrayList<>(),Answer=new ArrayList<>();

    public ArrayList<String> getQuestionCode() {
        return questionCode;
    }

    public ArrayList<String> getAnswer() {
        return Answer;
    }

    public ArrayList<String> getQuestionNumber() {
        return questionNumber;
    }

    public ArrayList<String> getQuestionName() {
        return questionName;
    }

    public ArrayList<String> getOptionA() {
        return optionA;
    }

    public ArrayList<String> getOptionB() {
        return optionB;
    }

    public ArrayList<String> getOptionC() {
        return optionC;
    }

    public ArrayList<String> getOptionD() {
        return optionD;
    }

    public String getTotalQues() {
        return totalQues;
    }

    public void setTotalQues(String totalQues) {
        this.totalQues = totalQues;
    }

    String totalQues;

    public void setQnumber(String qnumber) {
        this.qnumber = qnumber;
        questionNumber.add(qnumber);
    }

    public void setQname(String qname) {
        this.qname = qname;
        questionName.add(qname);
    }

    public void setOa(String oa) {
        this.oa = oa;
        optionA.add(oa);
    }

    public void setOb(String ob) {
        this.ob = ob;
        optionB.add(ob);
    }

    public void setOc(String oc) {
        this.oc = oc;
        optionC.add(oc);
    }

    public void setOd(String od) {
        this.od = od;
        optionD.add(od);
    }

    String qnumber;
    String qname;
    String oa;
    String ob;
    String oc;
    String od;
    String qcode;

    public void setQcode(String qcode) {
        this.qcode = qcode;
        questionCode.add(qcode);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        Answer.add(answer);
    }

    String answer;
}
