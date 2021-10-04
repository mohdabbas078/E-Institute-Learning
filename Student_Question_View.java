package com.example.online_examination_system.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student_Adapter.Student_Question_View_Adapter;
import com.example.online_examination_system.Student_Bean.Student_Question_View_Bean;
import com.example.online_examination_system.Student_Operation.Student_Question_View_Operation;
import com.example.online_examination_system.Teacher.Question_View;
import com.example.online_examination_system.Teacher_Adapter.Question_View_Adapter;

public class Student_Question_View extends AppCompatActivity {
   Student_Question_View_Bean student_question_view_bean;
   Student_Question_View_Adapter student_question_view_adapter;
   Student_Question_View_Operation student_question_view_operation;
   SessionManage sessionManage;

   ListView listView;
   TextView totalQues;
   TextView title;
   CircularDotsLoader circularDotsLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__question__view);
        student_question_view_operation=new Student_Question_View_Operation();
        sessionManage=new SessionManage(Student_Question_View.this);

        listView=(ListView)findViewById(R.id.sqvlt1);
        totalQues=(TextView)findViewById(R.id.stqv1);
        title=(TextView)findViewById(R.id.sqvt1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.sqvcdl1);

        if(sessionManage.getTwoinone().equalsIgnoreCase("one"))
        {
            title.setText("Questions List");
        }
        if (sessionManage.getTwoinone().equalsIgnoreCase("two"))
        {
            title.setText("Questions Bank");
        }
          new Async().execute();
    }

    public void studentquesthome(View view)
    {
        startActivity(new Intent(Student_Question_View.this,Student_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {

                student_question_view_bean=student_question_view_operation.getQuestion(sessionManage.getTtopiccode(),sessionManage);

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            if(student_question_view_bean==null)
            {
                Toast.makeText(Student_Question_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                student_question_view_adapter=new Student_Question_View_Adapter(Student_Question_View.this,student_question_view_bean);
                totalQues.setText("(Total Questions :- "+student_question_view_bean.getTotalQues()+")");
                listView.setAdapter(student_question_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);

        }

    }
}