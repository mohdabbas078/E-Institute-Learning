package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.agrawalsuneet.dotsloader.loaders.PullInLoader;
import com.example.online_examination_system.Teacher_Adapter.Student_List_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Student_List_Bean;
import com.example.online_examination_system.Teacher_Bean.Student_List_Insert_Bean;
import com.example.online_examination_system.Teacher_Operation.Student_List_Insert_Operation;
import com.example.online_examination_system.Teacher_Operation.Student_List_Operation;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Student_List extends AppCompatActivity {

    ListView listView;
    ImageView homeImage;
    TextView totalStudents;
    CircularDotsLoader pullInLoader;
    Student_List_Bean student_list_bean;
    Student_List_Adapter student_list_adapter;
    Student_List_Operation student_list_operation;
    SessionManage sessionManage;
    Student_List_Insert_Bean student_list_insert_bean;
    Student_List_Insert_Operation student_list_insert_operation;
    ArrayList<CheckBox> checkBoxes=new ArrayList<>();
    ArrayList<String> id,fname,mname,lname;
    String batchCode="",studentId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__list);
        listView=(ListView)findViewById(R.id.tlt3);
        homeImage=(ImageView)findViewById(R.id.tstudentlisthome);
        totalStudents=(TextView)findViewById(R.id.ttotalstudent1);
        pullInLoader=(CircularDotsLoader) findViewById(R.id.tqvcdl1);
        student_list_operation=new Student_List_Operation();
        sessionManage=new SessionManage(Student_List.this);
        student_list_insert_operation=new Student_List_Insert_Operation();
        new StudentList().execute();
        new Async().execute();
        homeImage.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(Student_List.this,Teacher_Home_Page.class));
              finish();
          }
      });
    }

    public void addStudents(View view)
    {
        id=student_list_insert_bean.getsID();
        fname=student_list_insert_bean.getFirstName();
        mname=student_list_insert_bean.getMiddleName();
        lname=student_list_insert_bean.getLastName();
        batchCode=sessionManage.getBatchCode();
        AlertDialog.Builder builder = new AlertDialog.Builder(Student_List.this);
        builder.setTitle("Select Students").setIcon(R.drawable.addcourse).setMessage("If You Want To Add Students In This Batch?");
        View customLayout= getLayoutInflater().inflate(R.layout.tstudentlistinsertcustompopup, null);
        builder.setView(customLayout);
        LinearLayout linearLayout=(LinearLayout)customLayout.findViewById(R.id.tslilt1);
        for (int i=0;i<id.size();i++)
        {
            CheckBox checkBox1=new CheckBox(this);
            checkBox1.setText(fname.get(i)+" "+mname.get(i)+" "+lname.get(i)+" "+"["+id.get(i)+"]");
            linearLayout.addView(checkBox1);
            checkBoxes.add(checkBox1);
        }
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                insertstudent();
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



    class Async extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try
            {
                student_list_bean=student_list_operation.getStudentList(sessionManage.getBatchCode());
            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {

            if(student_list_bean==null)
            {
                pullInLoader.setVisibility(View.INVISIBLE);
                Toast.makeText(Student_List.this, "SomeThing Went Wrong"+sessionManage.getBatchCode(), Toast.LENGTH_SHORT).show();

            }
            else
            {
                pullInLoader.setVisibility(View.INVISIBLE);
                student_list_adapter=new Student_List_Adapter(Student_List.this,student_list_bean.getStuId(),student_list_bean.getfName(),student_list_bean.getMName(),
                        student_list_bean.getLName(),student_list_bean.getMobile(),student_list_bean.getEMail(),student_list_bean);
                totalStudents.setText("Total Students :- "+student_list_bean.getTotalstudents());
                listView.setAdapter(student_list_adapter);

            }
            super.onPostExecute(aVoid);

        }

    }

    class StudentList extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                student_list_insert_bean=student_list_insert_operation.getStuList(sessionManage.getTcoursecode());
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

           if(student_list_insert_bean==null)
           {
               Toast.makeText(Student_List.this, "No Student For Adding", Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(Student_List.this, "New Student Is Available In List For Adding", Toast.LENGTH_SHORT).show();
           }
            super.onPostExecute(aVoid);

        }

    }

    private void insertstudent()
    {
       // Toast.makeText(Student_List.this, "Please Wait it can Take SomeTime..", Toast.LENGTH_SHORT).show();
        new InsertStudent().execute();
        startActivity(new Intent(Student_List.this,Student_List.class));
        finish();
    }

    class InsertStudent extends AsyncTask<Void, Void, Void>
    {
        int j=5;
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {

                Connection connection1= ConnectionP.getCon();
                Statement stmt1=connection1.createStatement();
                for(int i=0;i<checkBoxes.size();i++)
                {
                    if(checkBoxes.get(i).isChecked())
                    {

                        studentId=id.get(i);

                        j=stmt1.executeUpdate("INSERT into batch_student  values('" + studentId + "','" + batchCode + "','"+sessionManage.getTcoursecode()+"')");

                    }
                }
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
                  Toast.makeText(Student_List.this, "Student Added", Toast.LENGTH_SHORT).show();
              }
             else
              {
                  Toast.makeText(Student_List.this, "Something Went Wrong"+"\n don't select all the students", Toast.LENGTH_LONG).show();
              }
            super.onPostExecute(aVoid);
        }

    }
}