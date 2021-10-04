package com.example.online_examination_system.Teacher_Bean;

import java.util.ArrayList;

public class Course_Batch_View_Bean
{
    public static ArrayList<String> courseName=new ArrayList<>(),courseCode=new ArrayList<>(),courseDec=new ArrayList<>(),
    coursestartdate=new ArrayList<>(),courseenddate=new ArrayList<>(),coursescope=new ArrayList<>();

    public static ArrayList<String> getCourseDec()
    {
        return courseDec;
    }

    public static ArrayList<String> getCoursestartdate()
    {
        return coursestartdate;
    }

    public static ArrayList<String> getCourseenddate()
    {
        return courseenddate;
    }

    public static ArrayList<String> getCoursescope()
    {
        return coursescope;
    }

    String name;
    String code;

    public void setCoursedec(String coursedec)
    {
        this.coursedec = coursedec;
        courseDec.add(coursedec);
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
        coursestartdate.add(startdate);
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
        courseenddate.add(enddate);
    }

    public void setScope(String scope)
    {
        this.scope = scope;
        coursescope.add(scope);
    }

    String coursedec,startdate,enddate,scope;
    public String getTotalcourse()
    {
        return totalcourse;
    }

    public void setTotalcourse(String totalcourse)
    {
        this.totalcourse = totalcourse;
    }

    String totalcourse;

    public void setName(String name) {
        this.name = name;
        courseName.add(name);
    }

    public void setCode(String code) {
        this.code = code;
        courseCode.add(code);
    }

    public ArrayList<String> getCourseName()
    {
        return courseName;
    }

    public ArrayList<String> getCourseCode() {
        return courseCode;
    }


}
