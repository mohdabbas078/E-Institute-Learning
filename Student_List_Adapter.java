package com.example.online_examination_system.Teacher_Adapter;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.Teacher.Student_List;
import com.example.online_examination_system.Teacher_Bean.Student_List_Bean;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Student_List_Adapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> sid,Fname,Mname,Lname,Mobile,Email;
    TextView t1,t2,t3,t4,t5,t6;
    Button Remove,Add;
    Student_List_Bean student_list_bean;
    String stuid;
    AlertDialog.Builder builder;
    public Student_List_Adapter(Context context, ArrayList<String> sid, ArrayList<String> fname, ArrayList<String> mname, ArrayList<String> lname, ArrayList<String> mobile, ArrayList<String> email,Student_List_Bean student_list_bean)
    {
        this.context = context;
        this.sid = sid;
        Fname = fname;
        Mname = mname;
        Lname = lname;
        Mobile = mobile;
        Email = email;
        this.student_list_bean=student_list_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return sid.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= layoutInflater.inflate(R.layout.tsudentlistcutomrow,null);
         t1=(TextView)view.findViewById(R.id.tslstudentid1);
         t2=(TextView)view.findViewById(R.id.tslfirstname1);
         t3=(TextView)view.findViewById(R.id.tslmiddlename1);
         t4=(TextView)view.findViewById(R.id.tsllastname1);
         t5=(TextView)view.findViewById(R.id.tslemail1);
         t6=(TextView)view.findViewById(R.id.tslmobile1);
         Remove=(Button)view.findViewById(R.id.tslremove1);


         t1.setText("Student Id :- "+sid.get(position));
         t2.setText("First Name :- "+Fname.get(position));
         t3.setText("Middle Name :- "+Mname.get(position));
         t4.setText("Last Name :- "+Lname.get(position));
         t5.setText("E-Mail :- "+Email.get(position));
         t6.setText("Mobile No :- "+Mobile.get(position));

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuid=student_list_bean.getStuId().get(position);
                builder = new AlertDialog.Builder(context);
                builder.setTitle("Remove Student").setIcon(R.drawable.del2).setMessage("Do You Really Want To Remove This Student ?")
                        .setCancelable(false).setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
    private void delete()
    {
        new Delete().execute();
        context.startActivity(new Intent(context, Student_List.class));
    }

    class Delete extends AsyncTask<Void, Void, Void> {

        int status = 0;
        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                status=statement.executeUpdate("DELETE FROM batch_student WHERE Id='"+stuid+"'");

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {
            if(status==1)
            {
                Toast.makeText(context, "Student SucessFully Remove", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);

        }

    }


}
