package com.example.online_examination_system.Student_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student_Bean.Student_Question_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student_Question_View_Operation
{
    int i=0;
   Student_Question_View_Bean student_question_view_bean;

    public Student_Question_View_Bean getQuestion(String tc, SessionManage twoinone)
    {
        student_question_view_bean =new Student_Question_View_Bean();
        try {
            Connection connection = ConnectionP.getCon();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            if (twoinone.getTwoinone().equalsIgnoreCase("one"))
            {
                 rs = stmt.executeQuery("SELECT * FROM insert_question_2,insert_question WHERE insert_question.Topic_Code='" + tc + "' AND insert_question.Question_Code=insert_question_2.Question_Code ");
             }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM insert_question_2,insert_question WHERE Course_Code='"+twoinone.getCoursecode()+"'  AND insert_question.Question_Code=insert_question_2.Question_Code ");
            }
            while(rs.next())
            {
                i+=1;
                student_question_view_bean.setQnumber(String.valueOf(i));

                student_question_view_bean.setQname(rs.getString("Question_Name"));
                student_question_view_bean.setQcode(rs.getString("Question_Code"));
                student_question_view_bean.setOa(rs.getString("Option_A"));
                student_question_view_bean.setOb(rs.getString("Option_B"));
                student_question_view_bean.setOc(rs.getString("Option_C"));
                student_question_view_bean.setOd(rs.getString("Option_D"));
                student_question_view_bean.setAnswer(rs.getString("Answer"));
            }
            student_question_view_bean.setTotalQues(String.valueOf(i));
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }
        return  student_question_view_bean;
    }
}
