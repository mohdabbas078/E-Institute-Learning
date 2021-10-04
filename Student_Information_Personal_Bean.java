package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Student_Information_Personal_Bean
{
    ArrayList<String> sID=new ArrayList<>(),firstName=new ArrayList<>(),middleName=new ArrayList<>(),lastName=new ArrayList<>(),Email=new ArrayList<>(),
      mobileNo=new ArrayList<>();
    ArrayList<Student_Information_Institute_Bean> student_information_institute_beans=new ArrayList<>(), student_information_institute_beans_batch=new ArrayList<>();
    String totalStudents;

    String sid,fname,mname,lname,email,mno;

    public ArrayList<String> getsID() {
        return sID;
    }

    public ArrayList<String> getFirstName() {
        return firstName;
    }

    public ArrayList<String> getMiddleName() {
        return middleName;
    }

    public ArrayList<String> getLastName() {
        return lastName;
    }

    public ArrayList<String> getEmail() {
        return Email;
    }

    public ArrayList<String> getMobileNo() {
        return mobileNo;
    }

    public void setSid(String sid) {
        this.sid = sid;
        sID.add(sid);
    }

    public void setFname(String fname) {
        this.fname = fname;
        firstName.add(fname);
    }

    public void setMname(String mname) {
        this.mname = mname;
        middleName.add(mname);
    }

    public void setLname(String lname) {
        this.lname = lname;
        lastName.add(lname);
    }

    public void setEmail(String email) {
        this.email = email;
        Email.add(email);
    }

    public void setMno(String mno) {
        this.mno = mno;
        mobileNo.add(mno);
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public ArrayList<Student_Information_Institute_Bean> getInstitute_beans()
    {
        return student_information_institute_beans;
    }

    public void setInstitute_beans(Student_Information_Institute_Bean institute_beans)
    {
        student_information_institute_beans.add(institute_beans);
    }

    public ArrayList<Student_Information_Institute_Bean> getStudent_information_institute_beans_batch()
    {
        return student_information_institute_beans_batch;
    }

    public void setStudent_information_institute_beans_batch(Student_Information_Institute_Bean student_information_institute_beans_batch) {
        this.student_information_institute_beans_batch.add(student_information_institute_beans_batch);
    }
}
