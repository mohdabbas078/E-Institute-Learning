package com.example.online_examination_system.Teacher_Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher.Question_View;
import com.example.online_examination_system.Teacher.Topic_View;
import com.example.online_examination_system.Teacher_Bean.Topic_View_Bean;

import java.sql.Connection;
import java.sql.Statement;

public class Topic_View_Adapter extends BaseAdapter
{
  Context context;
  LayoutInflater layoutInflater;
  Topic_View_Bean topic_view_bean;
  SessionManage sessionManage;
  String s1="",s2="",s3="";
    public Topic_View_Adapter(Context context, Topic_View_Bean topic_view_bean, SessionManage sessionManage)
    {
        this.context = context;
        this.topic_view_bean = topic_view_bean;
        this.sessionManage = sessionManage;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topic_view_bean.getTopicName().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=layoutInflater.inflate(R.layout.topicviewcustomrow,null);
        TextView tname=(TextView)view.findViewById(R.id.ttvtn1);
        TextView tcode=(TextView)view.findViewById(R.id.ttvtc1);
        Button info=(Button)view.findViewById(R.id.ttvinfo1);
        Button Update=(Button)view.findViewById(R.id.ttvupdate1);
        Button Delete=(Button)view.findViewById(R.id.ttvdelete1);
        Button Questions=(Button)view.findViewById(R.id.ttvques1);

        tname.setText("Topic Name :- "+topic_view_bean.getTopicName().get(position));
        tcode.setText("Topic Code :- "+topic_view_bean.getTopicCode().get(position));

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=topic_view_bean.getTopicCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Topic").setIcon(R.drawable.updte1);
                View customLayout= layoutInflater.inflate(R.layout.topicviewupdatecustompopup, null);
                builder.setView(customLayout);
                EditText tn=customLayout.findViewById(R.id.ttvutn1);
                EditText atopic=customLayout.findViewById(R.id.ttvuat1);


                tn.setText(topic_view_bean.getTopicName().get(position));
                atopic.setText(topic_view_bean.getAboutTopic().get(position));
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(tn.getText().toString(),atopic.getText().toString());
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

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Topic Information").setIcon(R.drawable.education).setMessage("Topic Name :- "+topic_view_bean.getTopicName().get(position)+"\n"+
                        "Topic Code :- "+topic_view_bean.getTopicCode().get(position)+"\n"+
                        "About Topic :- "+"\n"+topic_view_bean.getAboutTopic().get(position)+"\n")

                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();

                alert.show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = topic_view_bean.getTopicCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Topic").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Topic ?")
                        .setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                       delete();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();

                alert.show();
            }
        });

        Questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManage.setTtopiccode(topic_view_bean.getTopicCode().get(position));
                context.startActivity(new Intent(context, Question_View.class));
            }
        });
        return view;
    }

    private void update(String toString, String toString1)
    {
       s2=toString;
       s3=toString1;
       new UpdateTopic().execute();
       context.startActivity(new Intent(context, Topic_View.class));
        ((Activity)context).finish();
    }

    class UpdateTopic extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("UPDATE topic SET Topic_Name='"+s2+"',About_Topic='"+s3+"' WHERE Topic_Code='"+s1+"'");
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
            if (b)
            {
                Toast.makeText(context, "Topic Successfully Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
   private void delete()
   {
       new DeleteTopic().execute();
       context.startActivity(new Intent(context, Topic_View.class));
       ((Activity)context).finish();
   }
    class DeleteTopic extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("DELETE FROM topic WHERE Topic_Code='"+s1+"'");
                stmt.executeUpdate("DELETE insert_question,insert_question_2,quiz_detail FROM insert_question INNER JOIN insert_question_2 INNER JOIN quiz_detail WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question_2.Question_Code=quiz_detail.Question_Code AND insert_question.Topic_Code='"+s1+"'");
                stmt.executeUpdate("DELETE insert_question,insert_question_2 FROM insert_question INNER JOIN insert_question_2 WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question.Topic_Code='"+s1+"'");
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
            if (b)
            {
                Toast.makeText(context, "Topic Successfully Deleted", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
