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
import com.example.online_examination_system.Teacher.Topic_View;
import com.example.online_examination_system.Teacher.Unit_View;
import com.example.online_examination_system.Teacher_Bean.Unit_View_Bean;

import java.sql.Connection;
import java.sql.Statement;

public class Unit_View_Adapter extends BaseAdapter
{

    Context context;
    LayoutInflater layoutInflater;
    Unit_View_Bean unit_view_bean;
    SessionManage sessionManage;
    String s1="",s2="",s3="",s4="";
    public Unit_View_Adapter(Context context, Unit_View_Bean unit_view_bean, SessionManage sessionManage)
    {
        this.context = context;
        this.unit_view_bean = unit_view_bean;
        this.sessionManage = sessionManage;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return unit_view_bean.getUnitName().size();
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
        View view=layoutInflater.inflate(R.layout.unitviewcustomrow,null);
        TextView uname=(TextView)view.findViewById(R.id.tuvun1);
        TextView ucode=(TextView)view.findViewById(R.id.tuvuc1);
        Button info=(Button)view.findViewById(R.id.tuvinfo1);
        Button Update=(Button)view.findViewById(R.id.tuvupdate1);
        Button Delete=(Button)view.findViewById(R.id.tuvdelete1);
        Button Units=(Button)view.findViewById(R.id.tuvtpoics1);

        uname.setText("Unit Name :- "+unit_view_bean.getUnitName().get(position));
        ucode.setText("Unit Code :- "+unit_view_bean.getUnitCode().get(position));

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=unit_view_bean.getUnitCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Unit").setIcon(R.drawable.updte1);
                View customLayout= layoutInflater.inflate(R.layout.unitviewcustomupdatepopup, null);
                builder.setView(customLayout);
                EditText un=customLayout.findViewById(R.id.tuvuun1);
                EditText ttopics=customLayout.findViewById(R.id.tuvutt1);
                EditText tlecture=customLayout.findViewById(R.id.tuvutlr1);

                un.setText(unit_view_bean.getUnitName().get(position));
                ttopics.setText(unit_view_bean.getTotalTopic().get(position));
                tlecture.setText(unit_view_bean.getTotalLectures().get(position));
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(un.getText().toString(),ttopics.getText().toString(),tlecture.getText().toString());
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
                s1 = unit_view_bean.getUnitCode().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Unit").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Unit")
                        .setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        new DeleteUnit().execute();
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

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Unit Information").setIcon(R.drawable.education).setMessage("Unit Name :- "+unit_view_bean.getUnitName().get(position)+"\n\n"+
                        "Unit Code :- "+unit_view_bean.getUnitCode().get(position)+"\n"+
                        "Total Topics :- "+unit_view_bean.getTotalTopic().get(position)+"\n"+
                        "Total Lecture Required :- "+unit_view_bean.getTotalLectures().get(position)+"\n")
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
                 sessionManage.setTunitcode(unit_view_bean.getUnitCode().get(position));
                 context.startActivity(new Intent(context, Topic_View.class));
            }
        });
        return view;
    }

    private void update(String toString, String toString1, String toString2)
    {
        s2=toString;
        s3=toString1;
        s4=toString2;
         new UpdateUnit().execute();
         context.startActivity(new Intent(context, Unit_View.class));
        ((Activity)context).finish();
    }

    class UpdateUnit extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("UPDATE unit SET Unit_Name='"+s2+"',Total_Topic='"+s3+"',Total_Lectures='"+s4+"' WHERE Unit_Code='"+s1+"'");
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
                Toast.makeText(context, "Unit Successfully Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
    class DeleteUnit extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("DELETE FROM unit WHERE Unit_Code='"+s1+"'");
                stmt.executeUpdate("DELETE FROM topic WHERE Unit_Code='"+s1+"'");
                stmt.executeUpdate("DELETE insert_question,insert_question_2,quiz_detail FROM insert_question INNER JOIN insert_question_2 INNER JOIN quiz_detail WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question_2.Question_Code=quiz_detail.Question_Code AND insert_question.Unit_Code='"+s1+"' ");
                stmt.executeUpdate("DELETE insert_question,insert_question_2 FROM insert_question INNER JOIN insert_question_2 WHERE insert_question.Question_Code=insert_question_2.Question_Code AND insert_question.Unit_Code='"+s1+"' ");
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
                Toast.makeText(context, "Unit Successfully Deleted", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, Unit_View.class));
                ((Activity)context).finish();
            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
