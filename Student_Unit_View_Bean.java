package com.example.online_examination_system.Student_Bean;

import java.util.ArrayList;

public class Student_Unit_View_Bean
{
    ArrayList<String> unitName=new ArrayList<>(),unitCode=new ArrayList<>(),totalTopic=new ArrayList<>(),totalLectures=new ArrayList<>();

    public ArrayList<String> getUnitName() {
        return unitName;
    }

    public ArrayList<String> getUnitCode() {
        return unitCode;
    }

    public ArrayList<String> getTotalTopic() {
        return totalTopic;
    }

    public ArrayList<String> getTotalLectures() {
        return totalLectures;
    }

    public String getTotalunits() {
        return totalunits;
    }

    public void setTotalunits(String totalunits) {
        this.totalunits = totalunits;
    }

    String totalunits;
    String uname,ucode,totaltopics,totallectures;

    public void setUname(String uname)
    {
        this.uname = uname;
        unitName.add(uname);
    }

    public void setUcode(String ucode)
    {
        this.ucode = ucode;
        unitCode.add(ucode);
    }

    public void setTotaltopics(String totaltopics)
    {
        this.totaltopics = totaltopics;
        totalTopic.add(totaltopics);
    }

    public void setTotallectures(String totallectures)
    {
        this.totallectures = totallectures;
        totalLectures.add(totallectures);
    }
}
