package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Course_View_Bean
{
    public  ArrayList<String> courseName=new ArrayList<>();
    public  ArrayList<String> courseCode=new ArrayList<>();
    public  ArrayList<String> courseDec=new ArrayList<>();
    public  ArrayList<String> coursestartdate=new ArrayList<>();
    public  ArrayList<String> courseenddate=new ArrayList<>();
    public  ArrayList<String> coursescope=new ArrayList<>();

    public  ArrayList<String> getDeptId()
    {
        return deptId;
    }

    public  ArrayList<String> deptId=new ArrayList<>();

    public  ArrayList<String> getCourseDec()
    {
        return courseDec;
    }

    public  ArrayList<String> getCoursestartdate()
    {
        return coursestartdate;
    }

    public  ArrayList<String> getCourseenddate()
    {
        return courseenddate;
    }

    public  ArrayList<String> getCoursescope()
    {
        return coursescope;
    }

    String name;
    String code;
  String dCode;

    public void setdCode(String dCode)
    {
        this.dCode = dCode;
        deptId.add(dCode);
    }

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
