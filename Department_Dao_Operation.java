package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Deaprtment_Dao_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Department_Dao_Operation
{
    Deaprtment_Dao_Bean deapartment_dao_bean;

    public Deaprtment_Dao_Bean getDepart()
    {
        deapartment_dao_bean=new Deaprtment_Dao_Bean();
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from department");

            while(resultSet.next())
            {
                deapartment_dao_bean.setDid(resultSet.getString("deptId"));
                deapartment_dao_bean.setDn(resultSet.getString("deptName"));
            }
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return deapartment_dao_bean;
    }
}
