package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Course_View_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Course_View_Operation
{
    public Course_View_Bean showCourse(String deptId)
    {
        Course_View_Bean course_view_bean =new Course_View_Bean();
        int i=0;
        try

        {

         Connection connection= ConnectionP.getCon();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM course where deptId='"+deptId+"'");

            while(resultSet.next())
            {
                i=i+1;
                course_view_bean.setName(resultSet.getString("Course_Name"));
                course_view_bean.setCode(resultSet.getString("Course_Code"));
                course_view_bean.setCoursedec(resultSet.getString("Course_Desc"));
                course_view_bean.setStartdate(resultSet.getString("Start_Date"));
                course_view_bean.setEnddate(resultSet.getString("End_Date"));
                course_view_bean.setScope(resultSet.getString("Scope"));
                course_view_bean.setdCode(resultSet.getString("deptId"));
            }
            course_view_bean.setTotalcourse(String.valueOf(i));

        }

        catch(Exception e)

        {

            Log.d("myapp",String.valueOf(e));

        }
        return course_view_bean;
    }
}
