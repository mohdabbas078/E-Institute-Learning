package com.example.online_examination_system.Teacher_Bean;

import java.util.ArrayList;

public class Student_List_Bean
{
    ArrayList<String> stuId=new ArrayList<>(),fName=new ArrayList<>(),MName=new ArrayList<>(),LName=new ArrayList<>(),
            EMail=new ArrayList<>(),Mobile=new ArrayList<>();

    public ArrayList<String> getStuId() {
        return stuId;
    }

    public ArrayList<String> getfName() {
        return fName;
    }

    public ArrayList<String> getMName() {
        return MName;
    }

    public ArrayList<String> getLName() {
        return LName;
    }

    public ArrayList<String> getEMail() {
        return EMail;
    }

    public ArrayList<String> getMobile() {
        return Mobile;
    }

    public void setStuid(String stuid) {
        stuId.add(stuid);
        this.stuid = stuid;
    }

    public void setFname(String fname) {
        fName.add(fname);
        this.fname = fname;
    }

    public void setMname(String mname) {
        MName.add(mname);
        this.mname = mname;
    }

    public void setLname(String lname) {
        LName.add(lname);
        this.lname = lname;
    }

    public void setEmail(String email) {
        EMail.add(email);
        this.email = email;
    }

    public void setMobile(String mobile) {
        Mobile.add(mobile);
        this.mobile = mobile;
    }

    String stuid,fname,mname,lname,email,mobile;

    public String getTotalstudents() {
        return totalstudents;
    }

    public void setTotalstudents(String totalstudents) {
        this.totalstudents = totalstudents;
    }

    String totalstudents;
}
