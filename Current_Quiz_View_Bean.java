package com.example.online_examination_system.Teacher_Bean;

import java.util.ArrayList;

public class Current_Quiz_View_Bean
{
    public ArrayList<String> getQuizName()
    {
        return quizName;
    }

    public ArrayList<String> getQuizCode() {
        return quizCode;
    }

    public ArrayList<String> getStartDate() {
        return startDate;
    }

    public ArrayList<String> getTotalMarks() {
        return totalMarks;
    }

    public ArrayList<String> getTotalTime() {
        return totalTime;
    }

    public ArrayList<String> getTotalQuestions() {
        return totalQuestions;
    }

    public ArrayList<String> getSubjectName() {
        return subjectName;
    }

    public ArrayList<String> getSubjectCode() {
        return subjectCode;
    }

    ArrayList<String> quizName=new ArrayList<>(),quizCode=new ArrayList<>(),startDate=new ArrayList<>(),totalMarks=new ArrayList<>(),totalTime=new ArrayList<>(),
       totalQuestions=new ArrayList<>(),subjectName=new ArrayList<>(),subjectCode=new ArrayList<>();

    public String getTotalQuiz() {
        return totalQuiz;
    }

    public void setTotalQuiz(String totalQuiz) {
        this.totalQuiz = totalQuiz;
    }

    String totalQuiz;

    public void setQname(String qname)
    {
        this.qname = qname;
        quizName.add(qname);
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
        quizCode.add(qcode);
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
        startDate.add(sdate);
    }

    public void setTmarks(String tmarks) {
        this.tmarks = tmarks;
        totalMarks.add(tmarks);
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
        totalTime.add(ttime);
    }

    public void setTquestions(String tquestions) {
        this.tquestions = tquestions;
        totalQuestions.add(tquestions);
    }

    public void setSname(String sname) {
        this.sname = sname;
        subjectName.add(sname);
    }

    public void setScode(String scode) {
        this.scode = scode;
        subjectCode.add(scode);
    }

    String qname,qcode,sdate,tmarks,ttime,tquestions,sname,scode;
}
