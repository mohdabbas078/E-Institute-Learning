package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Course_Dao_Bean
{
   public  ArrayList<String> courseId=new ArrayList<>(),courseName=new ArrayList<>();
    String cid,cname;

    public ArrayList<String> getCourseId()
    {
        return courseId;
    }

    public ArrayList<String> getCourseName()
    {
        return courseName;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
        courseId.add(cid);
    }

    public void setCname(String cname) {
        this.cname = cname;
        courseName.add(cname);
    }
}
