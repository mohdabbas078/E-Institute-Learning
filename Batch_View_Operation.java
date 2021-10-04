package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;

import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Batch_View_Bean;
import com.example.online_examination_system.Teacher_Bean.Course_Batch_View_Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Batch_View_Operation
{
    public Batch_View_Bean getBatchList(String coursec)
    {
        Batch_View_Bean batch_view_bean=new Batch_View_Bean();
        int i=0;
        try

        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.43.118:3306/examsystem", "androuser", "andro123");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM batch_detail where Course_Code='"+coursec+"'");

            while(resultSet.next())
            {
                  i=i+1;
                  batch_view_bean.setBatchname(resultSet.getString("Batch_Name"));
                  batch_view_bean.setBatchcode(resultSet.getString("Batch_Code"));
                  batch_view_bean.setStartdate(resultSet.getString("Batch_Start_Date"));
                  batch_view_bean.setEnddate(resultSet.getString("Batch_End_Date"));
                  batch_view_bean.setStarttime(resultSet.getString("Batch_Start_Time"));
                  batch_view_bean.setEndtime(resultSet.getString("Batch_End_Time"));
                  batch_view_bean.setTotalstudents(resultSet.getString("Total_Student"));

            }

         batch_view_bean.setTotalBatch(String.valueOf(i));
        }

        catch(Exception e)

        {

            Log.d("myapp",String.valueOf(e));

        }
        return batch_view_bean;
    }
}
