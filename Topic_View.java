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
import com.example.online_examination_system.Teacher_Adapter.Topic_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Topic_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Topic_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Topic_View extends AppCompatActivity {
   SessionManage sessionManage;
   Topic_View_Bean topic_view_bean;
   Topic_View_Adapter topic_view_adapter;
   Topic_View_Operation topic_view_operation;
   TextView ttopics;
   ImageView addTopic;
   ListView listView;
   CircularDotsLoader circularDotsLoader;
   String cc="",sc="",uc="",s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic__view);
        sessionManage=new SessionManage(Topic_View.this);
        topic_view_operation=new Topic_View_Operation();
        ttopics=(TextView)findViewById(R.id.tttv1);
        addTopic=(ImageView)findViewById(R.id.ttva1);
        listView=(ListView)findViewById(R.id.ttvlt1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.ttvcdl1);
         new Async().execute();

        addTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc=sessionManage.getTcoursecode();
                sc=sessionManage.getTsubjectcode();
                uc=sessionManage.getTunitcode();
                AlertDialog.Builder builder = new AlertDialog.Builder(Topic_View.this);
                builder.setTitle("Add Topic").setIcon(R.drawable.addcourse);
                View customLayout= getLayoutInflater().inflate(R.layout.topicviewinsertcustompopup, null);
                builder.setView(customLayout);
                EditText tn=customLayout.findViewById(R.id.ttvitn1);
                EditText tc=customLayout.findViewById(R.id.ttvitc1);
                EditText atopic=customLayout.findViewById(R.id.ttviat1);
                builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(tn.getText().toString(),tc.getText().toString(),atopic.getText().toString());

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

    public void tlHome(View view)
    {
        startActivity(new Intent(Topic_View .this,Teacher_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                topic_view_bean=topic_view_operation.getTopics(sessionManage.getTunitcode());
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
            if(topic_view_bean==null)
            {
                Toast.makeText(Topic_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                topic_view_adapter=new Topic_View_Adapter(Topic_View.this,topic_view_bean,sessionManage);
                ttopics.setText("(Total Topics :- "+topic_view_bean.getTotaltopics()+")");
                listView.setAdapter(topic_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);
        }

    }
    private void insert(String toString, String toString1, String toString2)
    {
       s1=toString;
        s2=toString1;
        s3=toString2;
        new InsertTopic().execute();
        startActivity(new Intent(Topic_View.this,Topic_View.class));
        finish();
    }

    class InsertTopic extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
               Connection connection= ConnectionP.getCon();
               Statement stmt=connection.createStatement();
               stmt.executeUpdate("INSERT into topic values('"+cc+"','"+sc+"','"+uc+"','"+s1+"','"+s2+"','"+s3+"')");
               b=true;
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
                Toast.makeText(Topic_View.this, "Topic Successfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Topic_View.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }

    }
}