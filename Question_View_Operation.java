package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Teacher_Bean.Question_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question_View_Operation
{
  int i=0;
  Question_View_Bean question_view_bean;

  public Question_View_Bean getQuestion(String tc)
  {
      question_view_bean =new Question_View_Bean();
   try
   {
       Connection connection= ConnectionP.getCon();
       Statement stmt=connection.createStatement();
       ResultSet rs=stmt.executeQuery("SELECT * FROM insert_question_2,insert_question WHERE insert_question.Topic_Code='"+tc+"' AND insert_question.Question_Code=insert_question_2.Question_Code ");
       while(rs.next())
       {
           i+=1;
           question_view_bean.setQnumber(String.valueOf(i));

           question_view_bean.setQname(rs.getString("Question_Name"));
           question_view_bean.setQcode(rs.getString("Question_Code"));
           question_view_bean.setOa(rs.getString("Option_A"));
           question_view_bean.setOb(rs.getString("Option_B"));
           question_view_bean.setOc(rs.getString("Option_C"));
           question_view_bean.setOd(rs.getString("Option_D"));
           question_view_bean.setAnswer(rs.getString("Answer"));
       }
       question_view_bean.setTotalQues(String.valueOf(i));
   }
   catch (Exception e)
   {
       Log.d("myapp",String.valueOf(e));
   }
      return  question_view_bean;
  }
}
