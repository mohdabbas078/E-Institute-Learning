package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Course_Dao_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Course_Dao_Operation
{
    Course_Dao_Bean course_dao_bean;

    public Course_Dao_Bean getCourse_dao_bean()
    {
        course_dao_bean=new Course_Dao_Bean();
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from course");

            while (resultSet.next())
            {
                course_dao_bean.setCid(resultSet.getString("Course_Code"));
                course_dao_bean.setCname(resultSet.getString("Course_Name"));
            }
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }

        return course_dao_bean;
    }
}
