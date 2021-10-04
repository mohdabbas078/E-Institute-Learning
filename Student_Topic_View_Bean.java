package com.example.online_examination_system.Student_Bean;

import java.util.ArrayList;

public class Student_Topic_View_Bean
{
    ArrayList<String> topicName=new ArrayList<>(),topicCode=new ArrayList<>(),aboutTopic=new ArrayList<>();

    public ArrayList<String> getTopicName() {
        return topicName;
    }

    public ArrayList<String> getTopicCode() {
        return topicCode;
    }

    public ArrayList<String> getAboutTopic() {
        return aboutTopic;
    }

    String totaltopics;

    public String getTotaltopics() {
        return totaltopics;
    }

    public void setTotaltopics(String totaltopics) {
        this.totaltopics = totaltopics;
    }

    String tname,tcode,atopic;

    public void setTname(String tname)
    {
        this.tname = tname;
        topicName.add(tname);
    }

    public void setTcode(String tcode)
    {
        this.tcode = tcode;
        topicCode.add(tcode);
    }

    public void setAtopic(String atopic)
    {
        this.atopic = atopic;
        aboutTopic.add(atopic);
    }
}
