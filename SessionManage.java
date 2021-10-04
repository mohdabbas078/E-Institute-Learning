package com.example.online_examination_system;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.Connection;

public class SessionManage
{
    private String username;
    private String status;
    private String teachername;
    private String studentname;
    private String coursename;
    private String coursecode;
    private String tcoursename;
    private String tunitcode;
    private String ttopiccode;
    private String twoinone;
    private Connection connection;

    public String getDeptCode()
    {
        deptCode=sharedPreferences.getString("deptCode","");
        return deptCode;
    }

    public void setDeptCode(String deptCode)
    {
        sharedPreferences.edit().putString("deptCode",deptCode).commit();
        this.deptCode = deptCode;
    }

    private String deptCode;
    public String getI()
    {
        i=sharedPreferences.getString("i","");
        return i;
    }

    public void setI(String i)
    {
        sharedPreferences.edit().putString("i",i).commit();
        this.i = i;
    }

    private String i;
    public String getTwoinone()
    {
        twoinone=sharedPreferences.getString("twoinone","");
        return twoinone;
    }

    public void setTwoinone(String twoinone)
    {
        sharedPreferences.edit().putString("twoinone",twoinone).commit();
        this.twoinone = twoinone;
    }

    public String getTtopiccode()
    {
        ttopiccode=sharedPreferences.getString("ttopiccode","");
        return ttopiccode;
    }

    public void setTtopiccode(String ttopiccode)
    {
        sharedPreferences.edit().putString("ttopiccode",ttopiccode).commit();
        this.ttopiccode = ttopiccode;
    }

    public String getTunitcode()
    {
        tunitcode=sharedPreferences.getString("tunitcode","");
        return tunitcode;
    }

    public void setTunitcode(String tunitcode)
    {
        sharedPreferences.edit().putString("tunitcode",tunitcode).commit();
        this.tunitcode = tunitcode;
    }

    public String getTsubjectcode()
    {
        tsubjectcode=sharedPreferences.getString("tsubjectcode","");
        return tsubjectcode;
    }

    public void setTsubjectcode(String tsubjectcode)
    {
        sharedPreferences.edit().putString("tsubjectcode",tsubjectcode).commit();
        this.tsubjectcode = tsubjectcode;
    }

    private String tsubjectcode;
    public String getBatchCode()
    {
        batchCode=sharedPreferences.getString("batchCode","");
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        sharedPreferences.edit().putString("batchCode",batchCode).commit();
        this.batchCode = batchCode;
    }

    private String batchCode;
    public String getTcoursename()
    {
        tcoursename=sharedPreferences.getString("tcoursename","");
        return tcoursename;
    }

    public void setTcoursename(String tcoursename)
    {
        sharedPreferences.edit().putString("tcoursename",tcoursename).commit();
        this.tcoursename = tcoursename;
    }

    public String getTcoursecode()
    {
        tcoursecode=sharedPreferences.getString("tcoursecode","");
        return tcoursecode;
    }

    public void setTcoursecode(String tcoursecode)
    {
        sharedPreferences.edit().putString("tcoursecode",tcoursecode).commit();
        this.tcoursecode = tcoursecode;
    }

    private String tcoursecode;
    Context context;
    SharedPreferences sharedPreferences;
    public SessionManage(Context context)
    {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    public String getTeachername() {
        teachername=sharedPreferences.getString("teachername","");
        return teachername;
    }

    public void setTeachername(String teachername) {
        sharedPreferences.edit().putString("teachername",teachername).commit();
        this.teachername = teachername;
    }

    public String getStudentname() {
        studentname=sharedPreferences.getString("studentname","");
        return studentname;
    }

    public void setStudentname(String studentname) {
        sharedPreferences.edit().putString("studentname",studentname).commit();
        this.studentname = studentname;
    }

    public String getUsername() {
        username=sharedPreferences.getString("user","");
        return username;
    }

    public void setUsername(String username) {
        sharedPreferences.edit().putString("user",username).commit();
        this.username = username;
    }



    public String getStatus()
    {
        status=sharedPreferences.getString("status","");
        return status;
    }

    public void setStatus(String status)
    {
        sharedPreferences.edit().putString("status",status).commit();
        this.status = status;
    }


    public String getCoursename() {
        coursename=sharedPreferences.getString("coursename","");
        return coursename;
    }

    public void setCoursename(String coursename) {
        sharedPreferences.edit().putString("coursename",coursename).commit();
        this.coursename = coursename;
    }

    public String getCoursecode() {
        coursecode=sharedPreferences.getString("coursecode","");
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        sharedPreferences.edit().putString("coursecode",coursecode).commit();
        this.coursecode = coursecode;
    }

    public void remove()
    {

        sharedPreferences.edit().clear().commit();
    }
}
