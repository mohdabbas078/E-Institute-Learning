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
import com.example.online_examination_system.Teacher.Question_View;
import com.example.online_examination_system.Teacher_Bean.Question_View_Bean;

import java.sql.Connection;
import java.sql.Statement;

public class Question_View_Adapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    Question_View_Bean question_view_bean;
    String qc="",s1="",s2="",s3="",s4="",s5="",s6="";
    public Question_View_Adapter(Context context, Question_View_Bean question_view_bean)
    {
        this.context = context;
        this.question_view_bean = question_view_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return question_view_bean.getQuestionName().size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view=layoutInflater.inflate(R.layout.questionviewcustomrow,null);
        TextView quesno=(TextView)view.findViewById(R.id.tqvqn1);
        TextView quesname=(TextView)view.findViewById(R.id.tqvqname1);
        TextView optiona=(TextView)view.findViewById(R.id.tqvoa1);
        TextView optionb=(TextView)view.findViewById(R.id.tqvob1);
        TextView optionc=(TextView)view.findViewById(R.id.tqvoc1);
        TextView optiond=(TextView)view.findViewById(R.id.tqvod1);
        Button Update=(Button)view.findViewById(R.id.tqvupdate1);
        Button Delete=(Button)view.findViewById(R.id.tqvdelete1);
        Button ShowAnswer=(Button)view.findViewById(R.id.tqvsa1);

        quesno.setText("Ques No. :- "+question_view_bean.getQuestionNumber().get(position));
        quesname.setText(question_view_bean.getQuestionName().get(position));
        optiona.setText("a) "+question_view_bean.getOptionA().get(position));
        optionb.setText("b) "+question_view_bean.getOptionB().get(position));
        optionc.setText("c) "+question_view_bean.getOptionC().get(position));
        optiond.setText("d) "+question_view_bean.getOptionD().get(position));

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qc=question_view_bean.getQuestionCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Question").setIcon(R.drawable.updte1);
                View customLayout= layoutInflater.inflate(R.layout.questionviewupdatecustompopup, null);
                builder.setView(customLayout);
                EditText qn=customLayout.findViewById(R.id.tqvuqn1);
                EditText oa=customLayout.findViewById(R.id.tqvuoa1);
                EditText ob=customLayout.findViewById(R.id.tqvuob1);
                EditText oc=customLayout.findViewById(R.id.tqvuoc1);
                EditText od=customLayout.findViewById(R.id.tqvuod1);
                EditText co=customLayout.findViewById(R.id.tqvuco1);


                qn.setText(question_view_bean.getQuestionName().get(position));
                oa.setText(question_view_bean.getOptionA().get(position));
                ob.setText(question_view_bean.getOptionB().get(position));
                oc.setText(question_view_bean.getOptionC().get(position));
                od.setText(question_view_bean.getOptionD().get(position));
                co.setText(question_view_bean.getAnswer().get(position));
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(qn.getText().toString(),oa.getText().toString(),ob.getText().toString(),
                                oc.getText().toString(),od.getText().toString(),co.getText().toString());
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
                qc = question_view_bean.getQuestionCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Question").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Question ?")
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

        ShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Question Information").setIcon(R.drawable.education).setMessage("Question Number :- "+question_view_bean.getQuestionNumber().get(position)+"\n"+
                        "Question :- "+"\n"+question_view_bean.getQuestionName().get(position)+"\n\n"+
                        "Correct Option :- "+question_view_bean.getAnswer().get(position)+"\n")
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



    private void update(String toString, String toString1, String toString2, String toString3, String toString4, String toString5)
    {
        s1=toString;
        s2=toString1;
        s3=toString2;
        s4=toString3;
        s5=toString4;
        s6=toString5;
        new UpdateQues().execute();
        context.startActivity(new Intent(context,Question_View.class));
        ((Activity) context).finish();
    }

    class UpdateQues extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("UPDATE insert_question_2 SET Question_Name='"+s1+"',Option_A='"+s2+"',Option_B='"+s3+"',Option_C='"+s4+"',Option_D='"+s5+"',Answer='"+s6+"' WHERE Question_Code='"+qc+"'"); stmt.executeUpdate("UPDATE insert_question_2 SET Question_Name='"+s1+"',Option_A='"+s2+"',Option_B='"+s3+"',Option_C='"+s4+"',Option_D='"+s5+"',Answer='"+s6+"' WHERE Question_Code='"+qc+"'");
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
                Toast.makeText(context, "Question Successfully Updated", Toast.LENGTH_SHORT).show();
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
       new DeleteQues().execute();
       context.startActivity(new Intent(context,Question_View.class));
        ((Activity)context).finish();
    }
    class DeleteQues extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("DELETE insert_question_2,insert_question FROM insert_question_2 INNER JOIN insert_question WHERE insert_question_2.Question_Code=insert_question.Question_Code AND insert_question_2.Question_Code='"+qc+"'");
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
                Toast.makeText(context, "Question Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
