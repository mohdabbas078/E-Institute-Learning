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

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Teacher_Adapter.Question_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Question_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Question_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Question_View extends AppCompatActivity {
 SessionManage sessionManage;
 Question_View_Bean question_view_bean;
 Question_View_Adapter question_view_adapter;
 Question_View_Operation question_view_operation;
 String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="",s10="",s11="";
 TextView totalQues;
 ImageView addQues;
 ListView listView;
 CircularDotsLoader circularDotsLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question__view);
        sessionManage=new SessionManage(Question_View.this);
        question_view_operation=new Question_View_Operation();
        totalQues=(TextView)findViewById(R.id.ttqv1);
        addQues=(ImageView)findViewById(R.id.tqva1);
        listView=(ListView)findViewById(R.id.tqvlt1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.tqvcdl1);
        new Async().execute();
        addQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=sessionManage.getTtopiccode();
                s9=sessionManage.getTcoursecode();
                s10=sessionManage.getTsubjectcode();
                s11=sessionManage.getTunitcode();
                AlertDialog.Builder builder = new AlertDialog.Builder(Question_View.this);
                builder.setTitle("Add Question").setIcon(R.drawable.addcourse);
                View customLayout= getLayoutInflater().inflate(R.layout.questionviewinsertcustompopup, null);
                builder.setView(customLayout);
                EditText qn=customLayout.findViewById(R.id.tqviqn1);
                EditText qc=customLayout.findViewById(R.id.tqviqc1);
                EditText oa=customLayout.findViewById(R.id.tqvioa1);
                EditText ob=customLayout.findViewById(R.id.tqviob1);
                EditText oc=customLayout.findViewById(R.id.tqvioc1);
                EditText od=customLayout.findViewById(R.id.tqviod1);
                EditText co=customLayout.findViewById(R.id.tqvico1);


                builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(qn.getText().toString(),qc.getText().toString(),oa.getText().toString(),ob.getText().toString(),
                           oc.getText().toString(),od.getText().toString(),co.getText().toString());


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

    public void home(View view)
    {
        startActivity(new Intent(Question_View.this,Teacher_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {

                question_view_bean=question_view_operation.getQuestion(sessionManage.getTtopiccode());

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            if(question_view_bean==null)
            {
                Toast.makeText(Question_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                question_view_adapter=new Question_View_Adapter(Question_View.this,question_view_bean);
                totalQues.setText("(Total Questions :- "+question_view_bean.getTotalQues()+")");
                listView.setAdapter(question_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);

        }

    }

    private void insert(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6)
    {
        s2=toString;
        s3=toString1;
        s4=toString2;
        s5=toString3;
        s6=toString4;
        s7=toString5;
        s8=toString6;
        new InsertQues().execute();
        startActivity(new Intent(Question_View.this,Question_View.class));
        finish();
    }

    class InsertQues extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {

            Connection connection= ConnectionP.getCon();
            Statement stmt=connection.createStatement();
            stmt.executeUpdate("INSERT into insert_question_2 values('"+s3+"','"+s2+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')");
            stmt.executeUpdate("INSERT into insert_question values('"+s9+"','"+s10+"','"+s11+"','"+s1+"','"+s3+"')");
             b=true;
            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            if(b)
            {
                Toast.makeText(Question_View.this, "Question Sucessfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Question_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}