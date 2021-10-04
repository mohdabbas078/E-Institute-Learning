package com.example.online_examination_system.Student_Bean;

import java.util.ArrayList;

public class Student_Subject_View_Bean
{
    public ArrayList<String> getSubjectName() {
        return subjectName;
    }

    public ArrayList<String> getSubjectCode() {
        return subjectCode;
    }

    public ArrayList<String> getTotalUnits() {
        return totalUnits;
    }

    public ArrayList<String> getPreRquist() {
        return preRquist;
    }

    ArrayList<String> subjectName=new ArrayList<>(),subjectCode=new ArrayList<>(),totalUnits=new ArrayList<>(),preRquist=new ArrayList<>();

    public String getTotalsubjects() {
        return totalsubjects;
    }

    public void setTotalsubjects(String totalsubjects) {
        this.totalsubjects = totalsubjects;
    }

    String totalsubjects;
    String sname,scode,tunits,prerquist;

    public void setSname(String sname)
    {
        this.sname = sname;
        subjectName.add(sname);
    }

    public void setScode(String scode) {
        this.scode = scode;
        subjectCode.add(scode);
    }

    public void setTunits(String tunits) {
        this.tunits = tunits;
        totalUnits.add(tunits);
    }

    public void setPrerquist(String prerquist) {
        this.prerquist = prerquist;
        preRquist.add(prerquist);
    }
}
