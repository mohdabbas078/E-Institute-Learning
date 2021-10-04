package com.example.online_examination_system.Student_Operation;

import android.util.Log;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student_Bean.Student_Subject_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_Subject_View_Operation
{
    public Student_Subject_View_Bean getSubjects(String cc)
    {
        Student_Subject_View_Bean student_subject_view_bean=new Student_Subject_View_Bean();
        int i=0;
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM subject WHERE Course_Code='"+cc+"'");
            while (rs.next())
            {
                i=i+1;
                student_subject_view_bean.setSname(rs.getString("subject_Name"));
                student_subject_view_bean.setScode(rs.getString("Subject_Code"));
                student_subject_view_bean.setTunits(rs.getString("Total_Unit"));
                student_subject_view_bean.setPrerquist(rs.getString("Prerequist"));
            }

            student_subject_view_bean.setTotalsubjects(String.valueOf(i));
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return student_subject_view_bean;
    }
}
