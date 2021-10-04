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
import com.example.online_examination_system.Student.Student_Unit_View;
import com.example.online_examination_system.Student_Bean.Student_Subject_View_Bean;

public class Student_Subject_View_Adapter extends BaseAdapter
{

    Context context;
    LayoutInflater layoutInflater;
    Student_Subject_View_Bean student_subject_view_bean;
    SessionManage sessionManage;

    public Student_Subject_View_Adapter(Context context, Student_Subject_View_Bean student_subject_view_bean, SessionManage sessionManage)
    {
        this.context = context;
        this.student_subject_view_bean = student_subject_view_bean;
        this.sessionManage = sessionManage;

        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return student_subject_view_bean.getSubjectName().size();
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
        View view=layoutInflater.inflate(R.layout.studentsubjectviewcustomrow,null);
        TextView subjectn=(TextView)view.findViewById(R.id.ssvname1);
        TextView subjectc=(TextView)view.findViewById(R.id.ssvcode1);
        Button Info=(Button)view.findViewById(R.id.ssvinfo1);
        Button Units=(Button)view.findViewById(R.id.ssvunits1);

        subjectn.setText("Subject Name :- "+student_subject_view_bean.getSubjectName().get(position));
        subjectc.setText("Subject Code :- "+student_subject_view_bean.getSubjectCode().get(position));

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Subject Information").setIcon(R.drawable.education).setMessage("Subject Name :- "+student_subject_view_bean.getSubjectName().get(position)+"\n\n"+
                        "Subject Code :- "+student_subject_view_bean.getSubjectCode().get(position)+"\n"+
                        "Total Units :- "+student_subject_view_bean.getTotalUnits().get(position)+"\n"+
                        "Prerequist :- "+student_subject_view_bean.getPreRquist().get(position)+"\n")
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

        Units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManage.setTsubjectcode(student_subject_view_bean.getSubjectCode().get(position));
                sessionManage.setTwoinone("one");
                context.startActivity(new Intent(context, Student_Unit_View.class));
            }
        });
        return view;
    }
}
