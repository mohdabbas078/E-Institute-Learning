package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Department_Course_View_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Department_Course_View_Operation
{

    public Department_Course_View_Bean showDeptCourse()
    {
        Department_Course_View_Bean department_course_view_bean =new Department_Course_View_Bean();
        int i=0;
        try

        {

            Connection connection=ConnectionP.getCon();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM department");

            while(resultSet.next())
            {
                i+=1;
                department_course_view_bean.setDcode(resultSet.getString("deptId"));
                department_course_view_bean.setDname(resultSet.getString("deptName"));
            }

            department_course_view_bean.setTotalDept(String.valueOf(i));
        }

        catch(Exception e)

        {

            Log.d("myapp",String.valueOf(e));

        }
        return department_course_view_bean;
    }
}
