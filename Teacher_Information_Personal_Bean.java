package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Teacher_Information_Personal_Bean
{
    ArrayList<String> TeacherId=new ArrayList<>(),FirstName=new ArrayList<>(),MiddleName=new ArrayList<>(),LastName=new ArrayList<>(),Email=new ArrayList<>(),MobileNo=new ArrayList<>();
    String fName,mName,lName,email,mno,tid;
   String totalTeacher;

   ArrayList<Teacher_Information_Institute_Bean> teacher_information_institute_beans_dept=new ArrayList<>(),
    teacher_information_institute_beans_course=new ArrayList<>(),teacher_information_institute_beans_batch=new ArrayList<>();
    public ArrayList<String> getFirstName() {
        return FirstName;
    }

    public ArrayList<String> getMiddleName() {
        return MiddleName;
    }

    public ArrayList<String> getLastName() {
        return LastName;
    }

    public ArrayList<String> getEmail() {
        return Email;
    }

    public ArrayList<String> getMobileNo() {
        return MobileNo;
    }

    public ArrayList<String> getTeacherId() {
        return TeacherId;
    }

    public void setfName(String fName) {
        this.fName = fName;
        FirstName.add(fName);
    }

    public void setmName(String mName) {
        this.mName = mName;
        MiddleName.add(mName);
    }

    public void setlName(String lName) {
        this.lName = lName;
        LastName.add(lName);
    }

    public void setEmail(String email) {
        this.email = email;
        Email.add(email);
    }

    public void setMno(String mno) {
        this.mno = mno;
        MobileNo.add(mno);
    }

    public void setTid(String tid) {
        this.tid = tid;
        TeacherId.add(tid);
    }

    public String getTotalTeacher() {
        return totalTeacher;
    }

    public void setTotalTeacher(String totalTeacher) {
        this.totalTeacher = totalTeacher;
    }


    public ArrayList<Teacher_Information_Institute_Bean> getTeacher_information_institute_beans_dept() {
        return teacher_information_institute_beans_dept;
    }

    public void setTeacher_information_institute_beans_dept(Teacher_Information_Institute_Bean teacher_information_institute_beans_dept) {
        this.teacher_information_institute_beans_dept.add(teacher_information_institute_beans_dept);
    }

    public ArrayList<Teacher_Information_Institute_Bean> getTeacher_information_institute_beans_course() {
        return teacher_information_institute_beans_course;
    }

    public void setTeacher_information_institute_beans_course(Teacher_Information_Institute_Bean teacher_information_institute_beans_course) {
        this.teacher_information_institute_beans_course.add(teacher_information_institute_beans_course);
    }

    public ArrayList<Teacher_Information_Institute_Bean> getTeacher_information_institute_beans_batch() {
        return teacher_information_institute_beans_batch;
    }

    public void setTeacher_information_institute_beans_batch(Teacher_Information_Institute_Bean teacher_information_institute_beans_batch) {
        this.teacher_information_institute_beans_batch.add(teacher_information_institute_beans_batch);
    }
}
