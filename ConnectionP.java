package com.example.online_examination_system.ConnectionProvider;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionP
{
    public static Connection connection=null;

    public static Connection getCon()
    {
        if(connection==null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                 connection = DriverManager.getConnection("jdbc:mysql://192.168.43.118:3306/examsystem", "androuser", "andro123");

            }
            catch (Exception e)
            {
                Log.d("my",String.valueOf(e));
            }
            return connection;
        }
        else
            {
            return connection;
        }
    }
    }

