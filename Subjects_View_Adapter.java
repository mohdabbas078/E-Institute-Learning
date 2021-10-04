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
import com.example.online_examination_system.Teacher.Subject_view;
import com.example.online_examination_system.Teacher.Unit_View;
import com.example.online_examination_system.Teacher_Bean.Subject_View_Bean;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Subjects_View_Adapter extends BaseAdapter
{
Context context;
LayoutInflater layoutInflater;
ArrayList<String> subjectName,subjectCode;
Subject_View_Bean subject_view_bean;
SessionManage sessionManage;
String s1="",s2="",s3="",s4="";
    public Subjects_View_Adapter(Context context, ArrayList<String> subjectName, ArrayList<String> subjectCode, Subject_View_Bean subject_view_bean, SessionManage sessionManage)
    {
        this.context = context;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subject_view_bean = subject_view_bean;
        this.sessionManage = sessionManage;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return subjectName.size();
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
        View view=layoutInflater.inflate(R.layout.subjectviewcustomrow,null);
        TextView sname=(TextView)view.findViewById(R.id.tsvname1);
        TextView scode=(TextView)view.findViewById(R.id.tsvcode1);
        Button info=(Button)view.findViewById(R.id.tsvinfo1);
        Button Update=(Button)view.findViewById(R.id.tsvupdate1);
        Button Delete=(Button)view.findViewById(R.id.tsvdelete1);
        Button Units=(Button)view.findViewById(R.id.tsvunits1);

        sname.setText("Subject Name :- "+subjectName.get(position)); 
        scode.setText("Subject Code :- "+subjectCode.get(position));

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Subject Information").setIcon(R.drawable.education).setMessage("Subject Name :- "+subject_view_bean.getSubjectName().get(position)+"\n\n"+
                        "Subject Code :- "+subject_view_bean.getSubjectCode().get(position)+"\n"+
                        "Total Units :- "+subject_view_bean.getTotalUnits().get(position)+"\n"+
                        "Prerequist :- "+subject_view_bean.getPreRquist().get(position)+"\n")
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
                s1=subject_view_bean.getSubjectCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Subject").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Subject")
                        .setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                         new DeleteSubject().execute();
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=subject_view_bean.getSubjectCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Course").setIcon(R.drawable.updte1);
                View customLayout= layoutInflater.inflate(R.layout.subjectviewupdatecustompopup, null);
                builder.setView(customLayout);
                EditText sn=customLayout.findViewById(R.id.tsvusn1);
                EditText tunit=customLayout.findViewById(R.id.tsvutu1);
                EditText pre=customLayout.findViewById(R.id.tsvup1);

                     sn.setText(subject_view_bean.getSubjectName().get(position));
                     tunit.setText(subject_view_bean.getTotalUnits().get(position));
                     pre.setText(subject_view_bean.getPreRquist().get(position));
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(sn.getText().toString(),tunit.getText().toString(),pre.getText().toString());
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

        Units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sessionManage.setTsubjectcode(subject_view_bean.getSubjectCode().get(position));
                context.startActivity(new Intent(context, Unit_View.class));
            }
        });
        return view;
    }



    class DeleteSubject extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
               Connection connection= ConnectionP.getCon();
               Statement stmt=connection.createStatement();
                stmt.executeUpdate("DELETE FROM subject WHERE Subject_Code='"+s1+"'");
                stmt.executeUpdate("DELETE quiz_detail_4,quiz_detail_5,student_quiz FROM quiz_detail_5 INNER JOIN quiz_detail_4 INNER JOIN student_quiz WHERE quiz_detail_5.Subject_Code='"+s1+"' AND quiz_detail_5.Quiz_Code=quiz_detail_4.Quiz_Code AND quiz_detail_4.Quiz_Code=student_quiz.Quiz_Code");
                stmt.executeUpdate("DELETE quiz_detail,quiz_detail_2,quiz_detail_3 FROM quiz_detail_3 INNER JOIN quiz_detail_2 INNER JOIN quiz_detail WHERE quiz_detail_3.Subject_Code='"+s1+"' AND quiz_detail_3.Quiz_Code=quiz_detail_2.Quiz_Code AND quiz_detail_2.Quiz_Code=quiz_detail.Quiz_Code ");
                stmt.executeUpdate("DELETE FROM unit WHERE Subject_Code='"+s1+"' ");
                stmt.executeUpdate("DELETE FROM topic WHERE Subject_Code='"+s1+"' ");
                stmt.executeUpdate("DELETE insert_question,insert_question_2 FROM insert_question INNER JOIN insert_question_2 WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question.Subject_Code='"+s1+"' ");
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
               Toast.makeText(context, "Subject Sucessfully Deleted", Toast.LENGTH_SHORT).show();
               context.startActivity(new Intent(context, Subject_view.class));
               ((Activity)context).finish();
           }
           else
           {
               Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
           }
            super.onPostExecute(aVoid);

        }

    }
    private void update(String toString, String toString1, String toString2)
    {
        s2=toString;
        s3=toString1;
        s4=toString2;
      new UpdateSubject().execute();
        context.startActivity(new Intent(context, Subject_view.class));
        ((Activity)context).finish();
    }
    class UpdateSubject extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("UPDATE subject SET Subject_Name='"+s2+"',Total_Unit='"+s3+"',Prerequist='"+s4+"' WHERE Subject_Code='"+s1+"'");
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
                Toast.makeText(context, "Subject Sucessfully Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
