package com.example.online_examination_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.online_examination_system.Admin.Admin_Home_Page;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student.Student_Home_Page;
import com.example.online_examination_system.Teacher.Teacher_Home_Page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {
   EditText userID,userPass;
   AllianceLoader allianceLoader;
   SessionManage sessionManage;
   String status="",teacherName,studentName="";
    String cn="",cc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        allianceLoader=(AllianceLoader)findViewById(R.id.al1);
        userID=(EditText)findViewById(R.id.userid);
        userPass=(EditText)findViewById(R.id.userpass);
        sessionManage=new SessionManage(Login.this);
    }

    public void logintohome(View view)
    {
        if (userID.getText().toString().equals(""))
        {
            userID.setError("ID Can't be Empty");
            userID.requestFocus();
            return;
        }
        else if (userPass.getText().toString().equals(""))
        {
            userPass.setError("Password Can't be Empty");
            userPass .requestFocus();
            return;
        }
        else
        {

            allianceLoader.setVisibility(View.VISIBLE);
            new Async().execute();
        }
    }

    class Async extends AsyncTask<Void, Void, Void> {

        String redirect = "";

        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.43.118:3306/examsystem", "androuser", "andro123");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM login where Id='"+userID.getText().toString()+"' and Password='"+userPass.getText().toString()+"'");
                while(resultSet.next())
                {
                  redirect= resultSet.getString("status");
                }

                if (redirect.equalsIgnoreCase("s"))
                {
                    ResultSet resultSet1 = statement.executeQuery("SELECT * FROM student where Id='"+userID.getText().toString()+"'");
                    while (resultSet1.next())
                    {
                       studentName=resultSet1.getString("FirstName");
                       studentName=studentName+" "+resultSet1.getString("MiddleName");
                       studentName=studentName+" "+resultSet1.getString("LastName");
                    }
                    sessionManage.setStudentname(studentName);

                    ResultSet resultSet2= statement.executeQuery("SELECT * FROM batch_student,batch_detail,course WHERE batch_student.Batch_Code=batch_detail.Batch_Code AND batch_detail.Course_Code=course.Course_Code AND batch_student.Id='"+userID.getText().toString()+"'");

                    while (resultSet2.next())
                      {
                          cn=cn+""+resultSet2.getString("Course_Name");
                          cc=cc+""+resultSet2.getString("Course_Code");
                      }
                    sessionManage.setCoursename(cn);
                    sessionManage.setCoursecode(cc);
                }



                if (redirect.equalsIgnoreCase("t"))
                {
                    ResultSet resultSet1 = statement.executeQuery("SELECT * FROM teacher where Id='"+userID.getText().toString()+"'");
                    while (resultSet1.next())
                    {
                        teacherName=resultSet1.getString("FirstName");
                        teacherName=teacherName+" "+resultSet1.getString("MiddleName");
                        teacherName=teacherName+" "+resultSet1.getString("LastName");
                    }
                    sessionManage.setTeachername(teacherName);
                }
                connection.close();

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {

           if(redirect.equalsIgnoreCase("s"))
           {
               status="s";
                setSessionData();
               allianceLoader.setVisibility(View.INVISIBLE);
               startActivity(new Intent(Login.this, Student_Home_Page.class)); 
               finish();
           }
           else if(redirect.equalsIgnoreCase("t"))
           {
               status="t";
               setSessionData();
               allianceLoader.setVisibility(View.INVISIBLE);
               startActivity(new Intent(Login.this, Teacher_Home_Page.class));

               finish();
           }
           else if(redirect.equalsIgnoreCase("a"))
           {
               status="a";
               setSessionData();
               allianceLoader.setVisibility(View.INVISIBLE);
               startActivity(new Intent(Login.this, Admin_Home_Page.class));
               finish();
           }
           else
           {
               allianceLoader.setVisibility(View.INVISIBLE);
               Toast.makeText(Login.this, "Wrong Id or Password !", Toast.LENGTH_SHORT).show();
           }

            super.onPostExecute(aVoid);

        }

    }

    public void setSessionData()
    {

        sessionManage.setUsername(userID.getText().toString());
        sessionManage.setStatus(status);
    }
}