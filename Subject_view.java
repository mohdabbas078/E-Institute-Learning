package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.PullInLoader;
import com.example.online_examination_system.Teacher_Adapter.Subjects_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Subject_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Subject_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Subject_view extends AppCompatActivity {
Subject_View_Bean subject_view_bean;
Subject_View_Operation subject_view_operation;
Subjects_View_Adapter subjects_view_adapter;
SessionManage sessionManage;
TextView totalSub;
ImageView addSub;
ListView listView;
PullInLoader pullInLoader;
String s1="",s2="",s3="",s4="",s5="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);
        totalSub=(TextView)findViewById(R.id.ttsv1);
        addSub=(ImageView)findViewById(R.id.tsva1);
        listView=(ListView)findViewById(R.id.tsvlt1);
        pullInLoader=(PullInLoader)findViewById(R.id.tsvpio1);
        subject_view_operation=new Subject_View_Operation();
        sessionManage=new SessionManage(Subject_view.this);
        new Async().execute();
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=sessionManage.getTcoursecode();
                AlertDialog.Builder builder = new AlertDialog.Builder(Subject_view.this);
                builder.setTitle("Add Subject").setIcon(R.drawable.addcourse);
                View customLayout= getLayoutInflater().inflate(R.layout.subjectviewinsertcustompopup, null);
                builder.setView(customLayout);
                EditText sn=customLayout.findViewById(R.id.tsvisn1);
                EditText sc=customLayout.findViewById(R.id.tsvisc1);
                EditText tunits=customLayout.findViewById(R.id.tsvitu1);
                EditText pre=customLayout.findViewById(R.id.tsvip1);

                builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(sn.getText().toString(),sc.getText().toString(),tunits.getText().toString(),pre.getText().toString());

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

    public void slHome(View view)
    {
        startActivity(new Intent(Subject_view.this,Teacher_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

               subject_view_bean=subject_view_operation.getStudents(sessionManage.getTcoursecode());
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

            if(subject_view_bean==null)
            {
                Toast.makeText(Subject_view.this, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                pullInLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
              subjects_view_adapter=new Subjects_View_Adapter(Subject_view.this,subject_view_bean.getSubjectName(),subject_view_bean.getSubjectCode(),
                      subject_view_bean,sessionManage);
              listView.setAdapter(subjects_view_adapter);
              totalSub.setText("(Total Subjects :- "+subject_view_bean.getTotalsubjects()+")");
              pullInLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);

        }

    }

    private void insert(String toString, String toString1, String toString2, String toString3)
    {
        s2=toString;
        s3=toString1;
        s4=toString2;
        s5=toString3;
        new InsertSubject().execute();
        startActivity(new Intent(Subject_view.this,Subject_view.class));
        finish();
    }

    class InsertSubject extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("INSERT into subject values('"+s1+"','"+s3+"','"+s2+"','"+s4+"','"+s5+"')");

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

            if(b)
            {
                Toast.makeText(Subject_view.this, "Subject Sucessfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Subject_view.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}