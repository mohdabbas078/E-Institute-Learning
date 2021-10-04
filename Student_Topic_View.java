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
import com.example.online_examination_system.Student_Adapter.Student_Topic_View_Adapter;
import com.example.online_examination_system.Student_Adapter.Student_Unit_View_Adapter;
import com.example.online_examination_system.Student_Bean.Student_Topic_View_Bean;
import com.example.online_examination_system.Student_Operation.Student_Topic_View_Operation;

public class Student_Topic_View extends AppCompatActivity
{
    Student_Topic_View_Bean student_topic_view_bean;
    Student_Topic_View_Operation student_topic_view_operation;
    Student_Topic_View_Adapter student_topic_view_adapter;
    SessionManage sessionManage;

    ListView listView;
    TextView totalTopics;
    CircularDotsLoader circularDotsLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__topic__view);
        student_topic_view_operation=new Student_Topic_View_Operation();
        sessionManage=new SessionManage(Student_Topic_View.this);

        listView=(ListView)findViewById(R.id.stvlt1);
        totalTopics=(TextView)findViewById(R.id.sttv1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.stvcdl1);
        new Async().execute();
    }

    public void topichome(View view)
    {
        startActivity(new Intent(Student_Topic_View.this,Student_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {

                student_topic_view_bean=student_topic_view_operation.getTopics(sessionManage.getTunitcode());

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(student_topic_view_bean==null)
            {
                Toast.makeText(Student_Topic_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                student_topic_view_adapter=new Student_Topic_View_Adapter(Student_Topic_View.this, student_topic_view_bean, sessionManage);
                totalTopics.setText("(Total Topics :- "+student_topic_view_bean.getTotaltopics()+")");
                listView.setAdapter(student_topic_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);
        }

    }
}