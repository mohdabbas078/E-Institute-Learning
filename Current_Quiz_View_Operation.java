package com.example.online_examination_system.Teacher_Operation;

import android.util.Log;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Teacher_Bean.Current_Quiz_View_Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class Current_Quiz_View_Operation
{
    Current_Quiz_View_Bean current_quiz_view_bean;

    public Current_Quiz_View_Bean getQuiz()
    {
        current_quiz_view_bean=new Current_Quiz_View_Bean();
        try
        {
            int i=0;
            Connection connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet  rs = statement.executeQuery("SELECT * FROM quiz_detail_2,quiz_detail_3,subject WHERE quiz_detail_2.Quiz_Code=quiz_detail_3.Quiz_Code AND quiz_detail_3.Subject_Code=subject.Subject_Code ");
            while(rs.next())
            {
               i+=1;
               current_quiz_view_bean.setQname(rs.getString("Quiz_Name"));
               current_quiz_view_bean.setQcode(rs.getString("Quiz_Code"));
               current_quiz_view_bean.setSdate(rs.getString("Start_Date"));
               current_quiz_view_bean.setTmarks(rs.getString("Total_Marks"));
               current_quiz_view_bean.setTtime(rs.getString("Total_Time"));
               current_quiz_view_bean.setTquestions(rs.getString("Total_Question"));
               current_quiz_view_bean.setSname(rs.getString("Subject_Name"));
               current_quiz_view_bean.setScode(rs.getString("Subject_Code"));
            }
            current_quiz_view_bean.setTotalQuiz(String.valueOf(i));
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }

        return current_quiz_view_bean;
    }
}
