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
import com.example.online_examination_system.Teacher.Course_View;
import com.example.online_examination_system.Teacher.Subject_view;
import com.example.online_examination_system.Teacher_Bean.Course_Batch_View_Bean;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Course_View_Adapter  extends BaseAdapter
{
    Context context;
    String cc="",s2="",s3="",s4="",s5="",s6="";
    LayoutInflater layoutInflater;
    ArrayList<String> course_name;
    ArrayList<String> course_code;
    Course_Batch_View_Bean course_batch_view_bean;
    SessionManage sessionManage;
    public Course_View_Adapter(Context context, ArrayList<String> course_Name, ArrayList<String> course_Code,Course_Batch_View_Bean course_batch_view_bean,SessionManage sessionManage)
    {
        this.context = context;
        this.course_name = course_Name;
        this.course_code = course_Code;
        this.sessionManage=sessionManage;
        layoutInflater=LayoutInflater.from(context);
        this.course_batch_view_bean=course_batch_view_bean;
    }

    @Override
    public int getCount() {
        return course_name.size();
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
        View view=layoutInflater.inflate(R.layout.course_view_custom_row,null);
        TextView cname=(TextView)view.findViewById(R.id.tcvname1);
        TextView ccode=(TextView)view.findViewById(R.id.tcvcode1);
        Button info=(Button)view.findViewById(R.id.tcvinfo1);
        Button Update=(Button)view.findViewById(R.id.tcvupdate1);
        Button Delete=(Button)view.findViewById(R.id.tcvdelete1);
        Button Subjects=(Button)view.findViewById(R.id.tcvsubjects1);
        cname.setText("Course Name :- "+course_name.get(position));
          ccode.setText("Course Code :- "+course_code.get(position));

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Course Information").setIcon(R.drawable.course3).setMessage("Course Name :- "+course_batch_view_bean.getCourseName().get(position)+"\n"+
                        "Course Code :- "+course_batch_view_bean.getCourseCode().get(position)+"\n"+
                        "Course Desc :- "+"\n"+course_batch_view_bean.getCourseDec().get(position)+"\n\n"+
                        "Course Start Date :- "+course_batch_view_bean.getCoursestartdate().get(position)+"\n"+
                        "Course End Date :- "+course_batch_view_bean.getCourseenddate().get(position)+"\n\n"+
                        "Course Scope :- "+"\n"+course_batch_view_bean.getCoursescope().get(position)+"\n")
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
                cc=course_batch_view_bean.getCourseCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Course").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Course")
                        .setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc=course_batch_view_bean.getCourseCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Update Course").setIcon(R.drawable.updte1);

                View customLayout= layoutInflater.inflate(R.layout.courseviewcustompopup, null);
                builder.setView(customLayout);
                EditText cn=customLayout.findViewById(R.id.tcvucn1);
                EditText cds=customLayout.findViewById(R.id.tcvucd1);
                EditText csd=customLayout.findViewById(R.id.tcvusd1);
                EditText ced=customLayout.findViewById(R.id.tcvued1);
                EditText csc=customLayout.findViewById(R.id.tcvus1);
                 cn.setText(course_batch_view_bean.getCourseName().get(position));
                 cds.setText(course_batch_view_bean.getCourseDec().get(position));
                 csd.setText(course_batch_view_bean.getCoursestartdate().get(position));
                 ced.setText(course_batch_view_bean.getCourseenddate().get(position));
                 csc.setText(course_batch_view_bean.getCoursescope().get(position));
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                      update(cn.getText().toString(),cds.getText().toString(),csd.getText().toString(),ced.getText().toString(),csc.getText().toString());
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

        Subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManage.setTcoursecode(course_batch_view_bean.getCourseCode().get(position));
                context.startActivity(new Intent(context, Subject_view.class));
            }
        });
        return view;
    }

    private void delete()
    {
        new Async().execute();
        context.startActivity(new Intent(context, Course_View.class));
        ((Activity)context).finish();
    }
    class Async extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Connection connection= ConnectionP.getCon();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("DELETE FROM course WHERE Course_Code='"+cc+"'");
                stmt.executeUpdate("DELETE quiz_detail_4,quiz_detail_5,student_quiz FROM quiz_detail_5 INNER JOIN quiz_detail_4 INNER JOIN student_quiz WHERE quiz_detail_5.Course_Code='"+cc+"' AND quiz_detail_5.Quiz_Code=quiz_detail_4.Quiz_Code AND quiz_detail_4.Quiz_Code=student_quiz.Quiz_Code");
                stmt.executeUpdate("DELETE subject,quiz_detail,quiz_detail_2,quiz_detail_3 FROM subject INNER JOIN quiz_detail_3 INNER JOIN quiz_detail_2 INNER JOIN quiz_detail WHERE subject.Course_Code='"+cc+"' AND subject.Subject_Code=quiz_detail_3.Subject_Code AND quiz_detail_3.Quiz_Code=quiz_detail_2.Quiz_Code AND quiz_detail_2.Quiz_Code=quiz_detail.Quiz_Code ");
                stmt.executeUpdate("DELETE FROM subject WHERE Course_Code='"+cc+"' ");
                stmt.executeUpdate("DELETE FROM unit WHERE Course_Code='"+cc+"' ");
                stmt.executeUpdate("DELETE FROM topic WHERE Course_Code='"+cc+"' ");
                stmt.executeUpdate("DELETE insert_question,insert_question_2 FROM insert_question INNER JOIN insert_question_2 WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question.Course_Code='"+cc+"' ");
                stmt.executeUpdate("DELETE batch_detail,batch_student FROM batch_detail INNER JOIN batch_student WHERE batch_detail.Batch_Code=batch_student.Batch_Code AND batch_detail.Course_Code='"+cc+"'");
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

            if (b)
            {
                course_batch_view_bean.getCourseCode().clear();
                course_batch_view_bean.getCourseName().clear();
                course_batch_view_bean.getCourseDec().clear();
                course_batch_view_bean.getCoursestartdate().clear();
                course_batch_view_bean.getCourseenddate().clear();
                course_batch_view_bean.getCoursescope().clear();
                Toast.makeText(context, "Data Sucessfully Deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
   private void update(String cn,String cd,String csd,String ced,String cs)
   {
       s2=cn;s3=cd;s4=csd;s5=ced;s6=cs;
       new UpdateCourse().execute();
       context.startActivity(new Intent(context, Course_View.class));
       ((Activity)context).finish();
   }
    class UpdateCourse extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Connection connection= ConnectionP.getCon();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("UPDATE course SET Course_Name='"+s2+"',Course_Desc='"+s3+"',Start_Date='"+s4+"',End_Date='"+s5+"' ,Scope='"+s6+"' WHERE Course_Code='"+cc+"'");
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

            if (b)
            {
                course_batch_view_bean.getCourseCode().clear();
                course_batch_view_bean.getCourseName().clear();
                course_batch_view_bean.getCourseDec().clear();
                course_batch_view_bean.getCoursestartdate().clear();
                course_batch_view_bean.getCourseenddate().clear();
                course_batch_view_bean.getCoursescope().clear();
                Toast.makeText(context, "Data Sucessfully Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
