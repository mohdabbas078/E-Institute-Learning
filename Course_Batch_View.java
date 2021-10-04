package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.agrawalsuneet.dotsloader.loaders.PullInLoader;
import com.example.online_examination_system.Teacher_Adapter.CourseBatchViewListAdapter;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Course_Batch_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Course_Batch_View_Operation;

public class Course_Batch_View extends AppCompatActivity {
   ListView listView;
   //int img[]={R.drawable.java1};
    CourseBatchViewListAdapter courseBatchViewListAdapter;
    Course_Batch_View_Operation course_batch_view_operation;
    Course_Batch_View_Bean course_batch_view_bean;
    PullInLoader pullInLoader;
    Animation animation;
    TextView totalcourse;
    SessionManage sessionManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__batch__view);
        listView=(ListView)findViewById(R.id.tlt1);
        pullInLoader=(PullInLoader)findViewById(R.id.pio1);
        animation= AnimationUtils.loadAnimation(Course_Batch_View.this,R.anim.entrance);
        totalcourse=(TextView)findViewById(R.id.ttotalcourses);
        sessionManage=new SessionManage(Course_Batch_View.this);
        new Async().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 sessionManage.setTcoursename(course_batch_view_bean.getCourseName().get(position));
                 sessionManage.setTcoursecode(course_batch_view_bean.getCourseCode().get(position));
                Intent intent=new Intent(Course_Batch_View.this,Batch_View.class);
                startActivity(intent);
            }
        });

    }

    class Async extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
           course_batch_view_operation =new Course_Batch_View_Operation();
            course_batch_view_bean=course_batch_view_operation.showCourseBatch();
            return null;
        }

        @Override

        protected void onPostExecute(Void aVoid) {
            totalcourse.setText("(Total Courses :- "+course_batch_view_bean.getTotalcourse()+")");
            courseBatchViewListAdapter=new CourseBatchViewListAdapter(Course_Batch_View.this,course_batch_view_bean.getCourseName(),course_batch_view_bean.getCourseCode());
            listView.setAdapter(courseBatchViewListAdapter);
            listView.setAnimation(animation);
            pullInLoader.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);

        }

    }

    @Override
    public void onBackPressed() {
        course_batch_view_bean.getCourseCode().clear();
        course_batch_view_bean.getCourseName().clear();
        course_batch_view_bean.getCourseDec().clear();
        course_batch_view_bean.getCoursestartdate().clear();
        course_batch_view_bean.getCourseenddate().clear();
        course_batch_view_bean.getCoursescope().clear();
        super.onBackPressed();
    }
}