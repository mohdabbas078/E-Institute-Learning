package com.example.online_examination_system.Admin_Adapter;

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

import com.example.online_examination_system.Admin.Course_View;
import com.example.online_examination_system.Admin.Department_Course_View;
import com.example.online_examination_system.Admin_Bean.Department_Course_View_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Adapter.Subjects_View_Adapter;

import java.sql.Connection;
import java.sql.Statement;

public class Department_Course_View_Adapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    SessionManage sessionManage;
    Department_Course_View_Bean department_course_view_bean;
    String dc="",dn="";
    public Department_Course_View_Adapter(Context context, SessionManage sessionManage, Department_Course_View_Bean department_course_view_bean)
    {
        this.context = context;
        this.sessionManage = sessionManage;
        this.department_course_view_bean = department_course_view_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return department_course_view_bean.getDeptCode().size();
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
        View view=layoutInflater.inflate(R.layout.departmentcourseviewcustomrow,null);
        TextView DeptName=(TextView)view.findViewById(R.id.deptname1);
        TextView deptCode=(TextView)view.findViewById(R.id.deptcode1);
        Button Info=(Button)view.findViewById(R.id.deptinfo1);
        Button Update=(Button)view.findViewById(R.id.deptupdate1);
        Button Delete=(Button)view.findViewById(R.id.deptdelete1);
        Button Courses=(Button)view.findViewById(R.id.deptcourses1);

        DeptName.setText("Department Name :- "+department_course_view_bean.getDeptName().get(position));
        deptCode.setText("Department Code :- "+department_course_view_bean.getDeptCode().get(position));


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dc=department_course_view_bean.getDeptCode().get(position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update DEPT").setIcon(R.drawable.updte1);
                View customLayout= layoutInflater.inflate(R.layout.dept_update_custom_popup, null);
                builder.setView(customLayout);
                EditText dn=customLayout.findViewById(R.id.udeptname12);
                dn.setText(department_course_view_bean.getDeptName().get(position).toString());
                builder.setCancelable(false).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        update(dn.getText().toString());

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

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dc=department_course_view_bean.getDeptCode().get(position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Dept").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Dept??")
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
                builder.setTitle("Department Information").setIcon(R.drawable.education)
                        .setMessage("Department Name :- "+department_course_view_bean.getDeptName().get(position).toString()+"\n\n"+
                        "Department Code :- "+department_course_view_bean.getDeptCode().get(position).toString()+"\n\n")
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
        Courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  sessionManage.setDeptCode(department_course_view_bean.getDeptCode().get(position).toString());
                  context.startActivity(new Intent(context, Course_View.class));
            }
        });
        return view;
    }

    private void update(String dn)
    {
      this.dn=dn;
      new UpdateDepartment().execute();
      context.startActivity(new Intent(context,Department_Course_View.class));
        ((Activity)context).finish();
    }
    class UpdateDepartment extends AsyncTask<Void, Void, Void> {
        int i=0;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                i=statement.executeUpdate("update department set deptName='"+dn+"' where deptId='"+dc+"'");
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
                Toast.makeText(context, "Department Successfully Updated", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(aVoid);

        }

    }

    private void delete()
    {
        new DeleteDepartment().execute();
        context.startActivity(new Intent(context,Department_Course_View.class));
        ((Activity)context).finish();
    }
    class DeleteDepartment extends AsyncTask<Void, Void, Void> {
        int i=0;
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                i=statement.executeUpdate("delete from department where deptId='"+dc+"'");
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
                Toast.makeText(context, "Department Successfully Deleted", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(context, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(aVoid);

        }

    }
}
