package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.PullInLoader;
import com.example.online_examination_system.Teacher_Adapter.Course_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Course_Batch_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Course_Batch_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Course_View extends AppCompatActivity {
    ListView listView;
    Course_Batch_View_Bean course_batch_view_bean;
    Course_Batch_View_Operation course_batch_view_operation;
    Course_View_Adapter course_view_adapter;
    PullInLoader pullInLoader;
    TextView totalcourse;
    SessionManage sessionManage;
    Animation animation;
    ImageView addCourse;
    String s1="",s2="",s3="",s4="",s5="",s6="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__view);
        listView=(ListView)findViewById(R.id.tcvlt1) ;
        totalcourse=(TextView)findViewById(R.id.ttcv1);
        pullInLoader=(PullInLoader)findViewById(R.id.tcvpio1);
        animation= AnimationUtils.loadAnimation(Course_View.this,R.anim.entrance);
        addCourse=(ImageView)findViewById(R.id.tcva1) ;
        sessionManage=new SessionManage(Course_View.this);
        new Async().execute();

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Course_View.this);
                builder.setTitle("Add Course").setIcon(R.drawable.addcourse);
                View customLayout= getLayoutInflater().inflate(R.layout.courseviewinsertcustompopup, null);
                builder.setView(customLayout);
                EditText cn=customLayout.findViewById(R.id.tcvucn2);
                EditText cc=customLayout.findViewById(R.id.tcvucc2);
                EditText cds=customLayout.findViewById(R.id.tcvucd2);
                EditText csd=customLayout.findViewById(R.id.tcvusd2);
                EditText ced=customLayout.findViewById(R.id.tcvued2);
                EditText csc=customLayout.findViewById(R.id.tcvus2);

                builder.setCancelable(false).setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(cn.getText().toString(),cc.getText().toString(),cds.getText().toString(),csd.getText().toString(),ced.getText().toString(),csc.getText().toString());

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void insert(String s1, String s2, String s3, String s4, String s5,String s6)
    {
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        this.s4=s4;
        this.s5=s5;
        this.s6=s6;
        new InsertCourse().execute();
        startActivity(new Intent(Course_View.this,Course_View.class));
        finish();
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
            course_view_adapter=new Course_View_Adapter(Course_View.this,course_batch_view_bean.getCourseName(),course_batch_view_bean.getCourseCode(),course_batch_view_bean,sessionManage);
            listView.setAdapter(course_view_adapter);
            pullInLoader.setVisibility(View.INVISIBLE);
            listView.setAnimation(animation);
            super.onPostExecute(aVoid);

        }

    }

    class InsertCourse extends AsyncTask<Void, Void, Void> {
         boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("INSERT into course values('"+s2+"','"+s1+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')");
                b=true;
            }
            catch (Exception e)
            {
                Log.d("myaap",String.valueOf(e));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {

            if (b)
            {
                Toast.makeText(Course_View.this, "Course Successfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Course_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
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