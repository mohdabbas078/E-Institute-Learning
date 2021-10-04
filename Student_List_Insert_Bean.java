package com.example.online_examination_system.Teacher_Bean;

import java.util.ArrayList;

public class Student_List_Insert_Bean
{
   ArrayList<String> sID=new ArrayList<>(),firstName=new ArrayList<>(),middleName=new ArrayList<>(),lastName=new ArrayList<>();
   String sid,fname,mname,lname;

    public void setSid(String sid)
    {
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

    public ArrayList<String> getsID()
    {
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
}
