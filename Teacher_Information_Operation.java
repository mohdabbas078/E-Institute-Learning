package com.example.online_examination_system.Admin_Operation;

import android.util.Log;

import com.example.online_examination_system.Admin_Bean.Teacher_Information_Institute_Bean;
import com.example.online_examination_system.Admin_Bean.Teacher_Information_Personal_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teacher_Information_Operation
{
    Teacher_Information_Personal_Bean teacher_information_personal_bean=null;
    Teacher_Information_Institute_Bean teacher_information_institute_bean=null;
    Connection connection;

    public Teacher_Information_Personal_Bean getPersonalData()
    {

        String id="";
        int i=0;
        try
        {
             connection= ConnectionP.getCon();
             Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from teacher");
            teacher_information_personal_bean=new Teacher_Information_Personal_Bean();
            while(resultSet.next())
            {
                i+=1;
                teacher_information_personal_bean.setTid(resultSet.getString("Id"));
                id=resultSet.getString("Id");
                teacher_information_personal_bean.setfName(resultSet.getString("FirstName"));
                teacher_information_personal_bean.setmName(resultSet.getString("MiddleName"));
                teacher_information_personal_bean.setlName(resultSet.getString("LastName"));
                teacher_information_personal_bean.setEmail(resultSet.getString("Email"));
                teacher_information_personal_bean.setMno(resultSet.getString("PhoneNo"));
                teacher_information_personal_bean.setTeacher_information_institute_beans_dept(getDepartments(id));
                teacher_information_personal_bean.setTeacher_information_institute_beans_course(getCourses(id));
                teacher_information_personal_bean.setTeacher_information_institute_beans_batch(getBatches(id));
            }
          teacher_information_personal_bean.setTotalTeacher(String.valueOf(i));
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }

        return  teacher_information_personal_bean;
    }

    Teacher_Information_Institute_Bean getDepartments(String id)
    {

        try
        {
            Statement  statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("select  * from dept_teacher ,department where Id='"+id+"' and dept_teacher.deptId=department.deptId");
            teacher_information_institute_bean=new Teacher_Information_Institute_Bean();
            while(resultSet2.next())
            {
                 teacher_information_institute_bean.setdName(resultSet2.getString("deptName"));
            }
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
      return teacher_information_institute_bean;
    }

    Teacher_Information_Institute_Bean getCourses(String id)
    {

        try
        {
            Statement  statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("select  * from course_teacher ,course where course_teacher.Id='"+id+"' and course_teacher.Course_Code=course.Course_Code");
            teacher_information_institute_bean=new Teacher_Information_Institute_Bean();
            while(resultSet2.next())
            {
                teacher_information_institute_bean.setcName(resultSet2.getString("Course_Name"));
            }
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return teacher_information_institute_bean;
    }

    Teacher_Information_Institute_Bean getBatches(String id)
    {

        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet2=statement.executeQuery("select  * from batch_teacher ,batch_detail where batch_teacher.Id='"+id+"' and batch_teacher.Batch_Code=batch_detail.Batch_Code");
            teacher_information_institute_bean=new Teacher_Information_Institute_Bean();
            while(resultSet2.next())
            {
                teacher_information_institute_bean.setbName(resultSet2.getString("Batch_Name"));
            }
        }
        catch(Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return teacher_information_institute_bean;
    }
}
