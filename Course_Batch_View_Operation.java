package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;

import com.example.online_examination_system.Teacher_Bean.Course_Batch_View_Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Course_Batch_View_Operation
{

    public Course_Batch_View_Bean showCourseBatch()
    {
        Course_Batch_View_Bean course_batch_view_bean =new Course_Batch_View_Bean();
        int i=0;
        try

        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.43.118:3306/examsystem", "androuser", "andro123");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM course");

            while(resultSet.next())
            {
                i=i+1;
                course_batch_view_bean.setName(resultSet.getString("Course_Name"));
                course_batch_view_bean.setCode(resultSet.getString("Course_Code"));
                course_batch_view_bean.setCoursedec(resultSet.getString("Course_Desc"));
                course_batch_view_bean.setStartdate(resultSet.getString("Start_Date"));
                course_batch_view_bean.setEnddate(resultSet.getString("End_Date"));
                course_batch_view_bean.setScope(resultSet.getString("Scope"));
            }
            course_batch_view_bean.setTotalcourse(String.valueOf(i));

        }

        catch(Exception e)

        {

            Log.d("myapp",String.valueOf(e));

        }
        return course_batch_view_bean;
    }
}
