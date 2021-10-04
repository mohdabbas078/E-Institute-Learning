package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Teacher.Student_List;
import com.example.online_examination_system.Teacher_Bean.Student_List_Insert_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_List_Insert_Operation
{
   Student_List_Insert_Bean student_list_insert_bean;

   public Student_List_Insert_Bean getStuList(String ccode)
   {
       student_list_insert_bean=new Student_List_Insert_Bean();

       try
       {
           Connection connection= ConnectionP.getCon();
           Statement stmt=connection.createStatement();
           ResultSet rs=stmt.executeQuery("SELECT * FROM student WHERE Id NOT IN (SELECT ID FROM batch_student where Course_Code='"+ccode+"')");
           while(rs.next())
           {
             student_list_insert_bean.setSid(rs.getString("Id"));
             student_list_insert_bean.setFname(rs.getString("FirstName"));
             student_list_insert_bean.setMname(rs.getString("MiddleName"));
             student_list_insert_bean.setLname(rs.getString("LastName"));
           }
       }
       catch (Exception e)
       {
           Log.d("myapp",String.valueOf(e));
       }
       return student_list_insert_bean;
   }
}
