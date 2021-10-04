package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Student_Information_Institute_Bean
{
   ArrayList<String> batchName=new ArrayList<>(),courseName=new ArrayList<>();
   String bName,cName;

    public ArrayList<String> getBatchName() {
        return batchName;
    }

    public ArrayList<String> getCourseName() {
        return courseName;
    }

    public void setbName(String bName) {
        this.bName = bName;
        batchName.add(bName);
    }

    public void setcName(String cName) {
        this.cName = cName;
        courseName.add(cName);
    }
}
