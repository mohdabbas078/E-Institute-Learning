package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;
import android.widget.ImageView;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.SessionManage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword
{
    String result="Something Went Wrong";
    boolean b=false;
    public String changePass(String oldPass,String newPass,SessionManage sessionManage)
    {

        try
        {
            Connection connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM login where Id='"+sessionManage.getUsername()+"' and Password='"+oldPass+"' and status='t'");
            while(resultSet.next())
            {
                b=true;
            }
            if(b)
            {
                statement.executeUpdate("update login set Password='"+newPass+"' where Id='"+sessionManage.getUsername()+"'");
                result="Password Successfully Changed";
            }
            else
            {
                result="Old Password Does't Match";
            }
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return result;
    }
}
