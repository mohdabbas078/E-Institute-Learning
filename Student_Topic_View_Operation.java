package com.example.online_examination_system.Student_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student_Bean.Student_Topic_View_Bean;
import com.example.online_examination_system.Teacher_Bean.Topic_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_Topic_View_Operation
{
    int i=0;
   Student_Topic_View_Bean student_topic_view_bean;

    public Student_Topic_View_Bean getTopics(String uc)
    {
        student_topic_view_bean=new Student_Topic_View_Bean();
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement stmt=connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM topic WHERE Unit_Code='"+uc+"'");
            while (rs.next())
            {
                i+=1;
                student_topic_view_bean.setTname(rs.getString("Topic_Name"));
                student_topic_view_bean.setTcode(rs.getString("Topic_Code"));
                student_topic_view_bean.setAtopic(rs.getString("About_Topic"));
            }
            student_topic_view_bean.setTotaltopics(String.valueOf(i));
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return student_topic_view_bean;
    }
}
