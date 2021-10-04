package com.example.online_examination_system.Student_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student.Student_Question_View;
import com.example.online_examination_system.Student_Bean.Student_Topic_View_Bean;

public class Student_Topic_View_Adapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    Student_Topic_View_Bean student_topic_view_bean;
    SessionManage sessionManage;

    public Student_Topic_View_Adapter(Context context, Student_Topic_View_Bean student_topic_view_bean, SessionManage sessionManage)
    {
        this.context = context;
        this.student_topic_view_bean = student_topic_view_bean;
        this.sessionManage = sessionManage;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return student_topic_view_bean.getTopicName().size();
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
        View view=layoutInflater.inflate(R.layout.studenttopicviewcustomrow,null);

        TextView topicN=(TextView)view.findViewById(R.id.stvname1);
        TextView topicC=(TextView)view.findViewById(R.id.stvcode1);
        Button info=(Button)view.findViewById(R.id.stvinfo1);
        Button Questions=(Button)view.findViewById(R.id.stvques1);

          topicN.setText("Topic Name :- "+student_topic_view_bean.getTopicName().get(position));
          topicC.setText("Topic Code :- "+student_topic_view_bean.getTopicCode().get(position));

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Topic Information").setIcon(R.drawable.education).setMessage("Topic Name :- "+student_topic_view_bean.getTopicName().get(position)+"\n"+
                        "Topic Code :- "+student_topic_view_bean.getTopicCode().get(position)+"\n"+
                        "About Topic :- "+"\n"+student_topic_view_bean.getAboutTopic().get(position)+"\n")

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

        Questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sessionManage.setTtopiccode(student_topic_view_bean.getTopicCode().get(position));
                sessionManage.setTwoinone("one");
                context.startActivity(new Intent(context, Student_Question_View.class));
            }
        });
        return view;
    }
}
