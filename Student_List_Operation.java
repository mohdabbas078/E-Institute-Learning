package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Student_List_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_List_Operation
{
    Student_List_Bean student_list_bean;
     public Student_List_Bean getStudentList(String batch_Code)
     {
         student_list_bean=new Student_List_Bean();
         int i=0;
         try
         {
             Connection connection= ConnectionP.getCon();
             Statement statement=connection.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM student,batch_student WHERE student.id=batch_student.id && batch_student.Batch_Code='"+batch_Code+"' ");
             while(resultSet.next())
             {
                 i=i+1;
                 student_list_bean.setStuid(resultSet.getString("Id"));
                 student_list_bean.setFname(resultSet.getString("FirstName"));
                student_list_bean.setMname(resultSet.getString("MiddleName"));
                 student_list_bean.setLname(resultSet.getString("LastName"));
               student_list_bean.setEmail(resultSet.getString("Email")) ;
                student_list_bean.setMobile(resultSet.getString("PhoneNO"));
             }

             student_list_bean.setTotalstudents(String.valueOf(i));
         }
         catch (Exception e)
         {
             Log.d("myapp",String.valueOf(e));
         }

        return student_list_bean;
     }
}
