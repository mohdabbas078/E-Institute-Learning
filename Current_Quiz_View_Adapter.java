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
import com.example.online_examination_system.Teacher.Current_Quiz_View;
import com.example.online_examination_system.Teacher_Bean.Current_Quiz_View_Bean;

import java.sql.Connection;
import java.sql.Statement;

public class Current_Quiz_View_Adapter extends BaseAdapter
{
   Context context;
   LayoutInflater layoutInflater;
   SessionManage sessionManage;
   Current_Quiz_View_Bean current_quiz_view_bean;

    String qc="";
    String s1,s2,s3,s4,s5;

    public Current_Quiz_View_Adapter(Context context, SessionManage sessionManage, Current_Quiz_View_Bean current_quiz_view_bean)
    {
        this.context = context;
        this.sessionManage = sessionManage;
        this.current_quiz_view_bean = current_quiz_view_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return current_quiz_view_bean.getQuizName().size();
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
        View view=layoutInflater.inflate(R.layout.current_quiz_view_custom_row,null);
        TextView QuizName=(TextView)view.findViewById(R.id.tquizname1);
        TextView QuizCode=(TextView)view.findViewById(R.id.tquizcode1);
        TextView QuizSubject=(TextView)view.findViewById(R.id.tquizsubject1);
        Button Info=(Button)view.findViewById(R.id.tquizinfo1);
        Button Update=(Button)view.findViewById(R.id.tquizupdate1);
        Button Delete=(Button)view.findViewById(R.id.tquizdelete1);
        Button ShowQuestions=(Button)view.findViewById(R.id.tshowquestions1);

        QuizName.setText("Quiz Name :- "+current_quiz_view_bean.getQuizName().get(position));
        QuizCode.setText("Quiz Code :- "+current_quiz_view_bean.getQuizCode().get(position));
        QuizSubject.setText("Quiz Subject :- "+current_quiz_view_bean.getSubjectName().get(position));


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              qc=current_quiz_view_bean.getQuizCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Quiz").setIcon(R.drawable.up2);
                View customLayout= layoutInflater.inflate(R.layout.current_quiz_view_update_custom_popup, null);
                builder.setView(customLayout);
                EditText quizN=(EditText)customLayout.findViewById(R.id.tuqn1);
                EditText quizSD=(EditText)customLayout.findViewById(R.id.tuqsd1);
                EditText quizTT=(EditText)customLayout.findViewById(R.id.tutt1);
                EditText quizTQ=(EditText)customLayout.findViewById(R.id.tutq1);
                EditText quizTM=(EditText)customLayout.findViewById(R.id.tutm1);

                 quizN.setText(current_quiz_view_bean.getQuizName().get(position));
                 quizSD.setText(current_quiz_view_bean.getStartDate().get(position));
                 quizTT.setText(current_quiz_view_bean.getTotalTime().get(position));
                 quizTQ.setText(current_quiz_view_bean.getTotalQuestions().get(position));
                 quizTM.setText(current_quiz_view_bean.getTotalMarks().get(position));

                builder.setCancelable(false).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(quizN.getText().toString(),quizSD.getText().toString(),quizTT.getText().toString(),quizTQ.getText().toString(),quizTM.getText().toString());
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

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qc=current_quiz_view_bean.getQuizCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Quiz").setIcon(R.drawable.trash).setMessage("Do You Really Want To Delete This Quiz ??.")
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
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Quiz Information").setIcon(R.drawable.quiz1).setMessage("\n"+"Subject Name :- "+current_quiz_view_bean.getSubjectName().get(position)+"\n\n"+
                        "Quiz Name :- "+current_quiz_view_bean.getQuizName().get(position)+"\n\n"+
                        "Quiz Code :- "+current_quiz_view_bean.getQuizCode().get(position)+"\n\n"+
                        "Quiz Creation Date :- "+current_quiz_view_bean.getStartDate().get(position)+"\n\n"+
                        "Maximum Marks :- "+current_quiz_view_bean.getTotalMarks().get(position)+"\n\n"+
                        "Maximum Time :- "+current_quiz_view_bean.getTotalTime().get(position)+"\n\n"+
                        "Total Questions :- "+current_quiz_view_bean.getTotalQuestions().get(position)+"\n")
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
        return view;
    }

    private void update(String s1,String s2,String s3,String s4,String s5)
    {
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        this.s4=s4;
        this.s5=s5;
        new UpdateQuiz().execute();
        context.startActivity(new Intent(context,Current_Quiz_View.class));
        ((Activity)context).finish();
    }

    class UpdateQuiz extends AsyncTask<Void, Void, Void>
    {
  int i=0;
        @Override
        protected Void doInBackground(Void... voids) {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                i=stmt.executeUpdate("UPDATE quiz_detail_2 SET Quiz_Name='"+s1+"',Start_Date='"+s2+"',Total_Marks='"+s5+"',Total_Time='"+s3+"',Total_Question='"+s4+"' WHERE Quiz_Code='"+qc+"'");
            }
            catch (Exception e)
            {

                Log.d("myapp", String.valueOf(e));

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
           if(i==1)
           {
               Toast.makeText(context, "Quiz Successfully Updated", Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
           }
            super.onPostExecute(aVoid);

        }

    }


    private void delete()
    {
        new DeleteQuiz().execute();
        context.startActivity(new Intent(context,Current_Quiz_View.class));
        ((Activity)context).finish();
    }

    class DeleteQuiz extends AsyncTask<Void, Void, Void>
    {
      boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("DELETE FROM quiz_detail WHERE Quiz_Code='"+qc+"'");
               stmt.executeUpdate("DELETE FROM quiz_detail_2 WHERE Quiz_Code='"+qc+"'");
                stmt.executeUpdate("DELETE FROM quiz_detail_3 WHERE Quiz_Code='"+qc+"'");
                stmt.executeUpdate("DELETE FROM quiz_detail_4 WHERE Quiz_Code='"+qc+"'");
                stmt.executeUpdate("DELETE FROM quiz_detail_5 WHERE Quiz_Code='"+qc+"'");
                stmt.executeUpdate("DELETE FROM student_quiz WHERE Quiz_Code='"+qc+"'");
                b=true;
            }
            catch (Exception e)
            {

                Log.d("myapp", String.valueOf(e));

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(b)
            {
                Toast.makeText(context, "Quiz Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
