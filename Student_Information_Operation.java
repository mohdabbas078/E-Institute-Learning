package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Student_Information_Institute_Bean;
import com.example.online_examination_system.Admin_Bean.Student_Information_Personal_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.SessionManage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student_Information_Operation
{

    Student_Information_Personal_Bean student_information_personal_bean;
    Student_Information_Institute_Bean student_information_institute_bean;
    Connection connection;
    public Student_Information_Personal_Bean getData()
    {
        student_information_personal_bean=new Student_Information_Personal_Bean();
        try
        {
            int i=0;
            String id="";
             connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from student");
            while(resultSet.next())
            {

                i+=1;
               student_information_personal_bean.setSid(resultSet.getString("Id"));
               id=resultSet.getString("Id");
               student_information_personal_bean.setFname(resultSet.getString("FirstName"));
               student_information_personal_bean.setMname(resultSet.getString("MiddleName"));
               student_information_personal_bean.setLname(resultSet.getString("LastName"));
               student_information_personal_bean.setEmail(resultSet.getString("Email"));
               student_information_personal_bean.setMno(resultSet.getString("PhoneNO"));
               student_information_personal_bean.setInstitute_beans(getCourseInfo(id));
               student_information_personal_bean.setStudent_information_institute_beans_batch(getBatchInfo(id));
            }

            student_information_personal_bean.setTotalStudents(String.valueOf(i));
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return student_information_personal_bean;
    }

    Student_Information_Institute_Bean getCourseInfo(String id) {

        student_information_institute_bean=new Student_Information_Institute_Bean();
        try
        {

            Statement statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("select  * from course ,course_student where Id='"+id+"' and course_student.Course_Code=course.Course_Code");
            while(resultSet2.next())
            {
                student_information_institute_bean.setcName(resultSet2.getString("Course_Name"));
            }
        }
        catch(Exception e)
        {
          Log.d("myapp",String.valueOf(e));
        }
      return student_information_institute_bean;
    }

    Student_Information_Institute_Bean getBatchInfo(String id) {

        student_information_institute_bean=new Student_Information_Institute_Bean();
        try
        {

            Statement statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("select  * from batch_detail ,batch_student where Id='"+id+"' and batch_student.Batch_Code=batch_detail.Batch_Code");
            while(resultSet2.next())
            {
                student_information_institute_bean.setbName(resultSet2.getString("Batch_Name"));
            }
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return student_information_institute_bean;
    }
}
