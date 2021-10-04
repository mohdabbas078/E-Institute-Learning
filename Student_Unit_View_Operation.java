package com.example.online_examination_system.Student_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student_Bean.Student_Unit_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_Unit_View_Operation
{
    Student_Unit_View_Bean student_unit_view_bean;
    int i=0;
    public Student_Unit_View_Bean getUnits(String sc)
    {
        student_unit_view_bean=new Student_Unit_View_Bean();
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM unit WHERE Subject_Code='"+sc+"' ");

            while(rs.next())
            {
                i=i+1;

                student_unit_view_bean.setUname(rs.getString("Unit_Name"));
                student_unit_view_bean.setUcode(rs.getString("Unit_Code"));
                student_unit_view_bean.setTotaltopics(rs.getString("Total_Topic"));
                student_unit_view_bean.setTotallectures(rs.getString("Total_Lectures"));
            }
            student_unit_view_bean.setTotalunits(String.valueOf(i));
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }

        return student_unit_view_bean;
    }
}
