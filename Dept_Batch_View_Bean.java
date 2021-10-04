package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Dept_Batch_View_Bean
{
    ArrayList<String> batchName=new ArrayList<>();
    ArrayList<String> batchCode=new ArrayList<>();
    ArrayList<String> startDate=new ArrayList<>();
    ArrayList<String> endDate=new ArrayList<>();
    ArrayList<String> startTime=new ArrayList<>();
    ArrayList<String> endTime=new ArrayList<>();
    ArrayList<String> totalStudents=new ArrayList<>();

    public ArrayList<String> getDeptCode()
    {
        return deptCode;
    }

    ArrayList<String> deptCode=new ArrayList<>();
    String batchname;

    public ArrayList<String> getBatchName() {
        return batchName;
    }

    public ArrayList<String> getBatchCode() {
        return batchCode;
    }

    public ArrayList<String> getStartDate() {
        return startDate;
    }

    public ArrayList<String> getEndDate() {
        return endDate;
    }

    public ArrayList<String> getStartTime() {
        return startTime;
    }

    public ArrayList<String> getEndTime() {
        return endTime;
    }

    public ArrayList<String> getTotalStudents() {
        return totalStudents;
    }

    String batchcode;
    String startdate;
    String enddate;
    String starttime;
    String endtime;
    String totalstudents;

    public void setDcode(String dcode) {
        this.dcode = dcode;
        deptCode.add(dcode);
    }

    String dcode;
    public void setBatchname(String batchname) {
        this.batchname = batchname;
        batchName.add(batchname);
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
        batchCode.add(batchcode);
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
        startDate.add(startdate);

    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
        endDate.add(enddate);
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
        startTime.add(starttime);
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
        endTime.add(endtime);
    }

    public void setTotalstudents(String totalstudents) {
        this.totalstudents = totalstudents;
        totalStudents.add(totalstudents);
    }
    String totalBatch;

    public String getTotalBatch() {
        return totalBatch;
    }

    public void setTotalBatch(String totalBatch) {
        this.totalBatch = totalBatch;
    }
}
