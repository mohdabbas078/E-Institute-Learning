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
import com.example.online_examination_system.Student.Student_Topic_View;
import com.example.online_examination_system.Student_Bean.Student_Unit_View_Bean;

public class Student_Unit_View_Adapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    SessionManage sessionManage;
    Student_Unit_View_Bean student_unit_view_bean;

    public Student_Unit_View_Adapter(Context context, SessionManage sessionManage, Student_Unit_View_Bean student_unit_view_bean) {
        this.context = context;
        this.sessionManage = sessionManage;
        this.student_unit_view_bean = student_unit_view_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return student_unit_view_bean.getUnitName().size();
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
        View view=layoutInflater.inflate(R.layout.studentunitviewcustomrow,null);
        TextView unitN=(TextView)view.findViewById(R.id.suvname1);
        TextView unitC=(TextView)view.findViewById(R.id.suvcode1);
        Button Info=(Button)view.findViewById(R.id.suvinfo1);
        Button Topics=(Button)view.findViewById(R.id.suvtopic1);

        unitN.setText("Unit Name :- "+student_unit_view_bean.getUnitName().get(position));
        unitC.setText("Unit Code :- "+student_unit_view_bean.getUnitCode().get(position));


        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Unit Information").setIcon(R.drawable.education).setMessage("Unit Name :- "+student_unit_view_bean.getUnitName().get(position)+"\n\n"+
                        "Unit Code :- "+student_unit_view_bean.getUnitCode().get(position)+"\n"+
                        "Total Topics :- "+student_unit_view_bean.getTotalTopic().get(position)+"\n"+
                        "Total Lecture Required :- "+student_unit_view_bean.getTotalLectures().get(position)+"\n")
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
        Topics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sessionManage.setTwoinone("one");
                sessionManage.setTunitcode(student_unit_view_bean.getUnitCode().get(position));
                context.startActivity(new Intent(context, Student_Topic_View.class));
            }
        });
        return view;
    }
}
