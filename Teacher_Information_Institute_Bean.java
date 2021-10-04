package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Teacher_Information_Institute_Bean
{
    ArrayList DeptName=new ArrayList(),CourseName=new ArrayList(),BatchName=new ArrayList();
    String dName,cName,bName;

    public ArrayList getDeptName() {
        return DeptName;
    }

    public ArrayList getCourseName() {
        return CourseName;
    }

    public ArrayList getBatchName() {
        return BatchName;
    }

    public void setdName(String dName) {
        this.dName = dName;
        DeptName.add(dName);
    }

    public void setcName(String cName) {
        this.cName = cName;
        CourseName.add(cName);
    }

    public void setbName(String bName) {
        this.bName = bName;
        BatchName.add(bName);
    }
}
