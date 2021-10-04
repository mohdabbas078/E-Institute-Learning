package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Department_Course_View_Bean
{
    public ArrayList getDeptCode() {
        return deptCode;
    }

    public ArrayList getDeptName() {
        return deptName;
    }

   public ArrayList<String> deptCode=new ArrayList(),deptName=new ArrayList();

    public String getTotalDept()
    {
        return totalDept;
    }

    public void setTotalDept(String totalDept) {
        this.totalDept = totalDept;
    }

    String totalDept;

    public void setDname(String dname)
    {
        this.dname = dname;
        deptName.add(dname);
    }

    public void setDcode(String dcode)
    {
        this.dcode = dcode;
        deptCode.add(dcode);
    }

    String dname,dcode;
}
