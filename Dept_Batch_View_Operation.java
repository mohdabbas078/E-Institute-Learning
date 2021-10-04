package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Dept_Batch_View_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dept_Batch_View_Operation
{
    public Dept_Batch_View_Bean getBatchList(String coursec)
    {
        Dept_Batch_View_Bean dept_batch_view_bean=new Dept_Batch_View_Bean();
        int i=0;
        try

        {

           Connection connection= ConnectionP.getCon();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM batch_detail where Course_Code='"+coursec+"'");

            while(resultSet.next())
            {
                i=i+1;
                dept_batch_view_bean.setBatchname(resultSet.getString("Batch_Name"));
                dept_batch_view_bean.setBatchcode(resultSet.getString("Batch_Code"));
                dept_batch_view_bean.setStartdate(resultSet.getString("Batch_Start_Date"));
                dept_batch_view_bean.setEnddate(resultSet.getString("Batch_End_Date"));
                dept_batch_view_bean.setStarttime(resultSet.getString("Batch_Start_Time"));
                dept_batch_view_bean.setEndtime(resultSet.getString("Batch_End_Time"));
                dept_batch_view_bean.setTotalstudents(resultSet.getString("Total_Student"));
                dept_batch_view_bean.setDcode(resultSet.getString("deptId"));

            }

            dept_batch_view_bean.setTotalBatch(String.valueOf(i));
        }

        catch(Exception e)

        {

            Log.d("myapp",String.valueOf(e));

        }
        return dept_batch_view_bean;
    }
}
