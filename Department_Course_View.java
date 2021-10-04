package com.example.online_examination_system.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Admin_Adapter.Department_Course_View_Adapter;
import com.example.online_examination_system.Admin_Bean.Department_Course_View_Bean;
import com.example.online_examination_system.Admin_Operation.Department_Course_View_Operation;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher.Batch_View;
import com.example.online_examination_system.Teacher.Course_Batch_View;
import com.example.online_examination_system.Teacher.Subject_view;

import java.sql.Connection;
import java.sql.Statement;

public class Department_Course_View extends AppCompatActivity {

    SessionManage sessionManage;
    Department_Course_View_Bean department_course_view_bean;
    Department_Course_View_Adapter department_course_view_adapter;
    Department_Course_View_Operation department_course_view_operation;

    ListView listView;
    TextView totalDept;
    CircularDotsLoader circularDotsLoader;
    ImageView insertDept;
    String dName="",dCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department__course__view);
        sessionManage=new SessionManage(Department_Course_View.this);
        department_course_view_operation=new Department_Course_View_Operation();

        listView=(ListView)findViewById(R.id.lt1);
        totalDept=(TextView)findViewById(R.id.totaldept);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.dvcdl1);
        insertDept=(ImageView)findViewById(R.id.deptinsert1) ;
        new ViewDepartment().execute();


        insertDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Department_Course_View.this);
                builder.setTitle("Add DEPT").setIcon(R.drawable.add);
                View customLayout= getLayoutInflater().inflate(R.layout.dept_insert_custom_popup, null);
                builder.setView(customLayout);
                EditText dn=customLayout.findViewById(R.id.ideptname12);
                EditText dc=customLayout.findViewById(R.id.ideptcode12);

                builder.setCancelable(false).setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(dn.getText().toString(),dc.getText().toString());

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }




    class ViewDepartment extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                 department_course_view_bean=department_course_view_operation.showDeptCourse();
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
            if (department_course_view_bean!=null)
            {
                department_course_view_adapter=new Department_Course_View_Adapter(Department_Course_View.this, sessionManage, department_course_view_bean);
                listView.setAdapter(department_course_view_adapter);
                totalDept.setText("(Total Department :- "+department_course_view_bean.getTotalDept()+")");
                Toast.makeText(Department_Course_View.this, "Department list", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                Toast.makeText(Department_Course_View.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);

        }

    }

    private void insert(String toString, String toString1)
    {
        dName=toString;
        dCode=toString1;
        new InsertDepartment().execute();
        startActivity(new Intent(Department_Course_View.this,Department_Course_View.class));
        finish();
    }

    class InsertDepartment extends AsyncTask<Void, Void, Void> {
        int i=0;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection=ConnectionP.getCon();
                Statement statement=connection.createStatement();
                i=statement.executeUpdate("insert into department values ('"+dCode+"','"+dName+"')");
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
            if (i==1)
            {
                Toast.makeText(Department_Course_View.this, "Department Successfully Added", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(Department_Course_View.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(aVoid);

        }

    }
}