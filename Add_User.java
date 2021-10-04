package com.example.online_examination_system.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Add_User extends AppCompatActivity
{

    ArrayList<String> spinnerItems;
    EditText coursename,yearofjoining,email;
    Button generate;
    Spinner spinner;
    SearchableSpinner dynamicSpinner , searchableSpinner;
    String username,password,status;
    String usertype="notselect",cName,yoj,emailid,courseid="notselect",courseName2,deptId="notselect",deptName;
    CardView cardView2,cardView3;
    Intent intent;
    ArrayList<String> cname,cid,did,dname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);

        dynamicSpinner=(SearchableSpinner) findViewById(R.id.spinner2);
        cardView2=(CardView)findViewById(R.id.cv2);
        cardView3=(CardView)findViewById(R.id.cv3);
        searchableSpinner=findViewById(R.id.spinner3);
        spinnerItems=new ArrayList<>();
        spinnerItems.add("Select User Type");spinnerItems.add("Admin");spinnerItems.add("Teacher");spinnerItems.add("Student");
        coursename=(EditText)findViewById(R.id.coursename1);
        yearofjoining=(EditText)findViewById(R.id.yearofjoing1);
        email=(EditText)findViewById(R.id.email1);
        generate=(Button)findViewById(R.id.generate1);
        spinner=(Spinner)findViewById(R.id.spinner1);
        intent=getIntent();
        cid=intent.getStringArrayListExtra("cid");
        cname=intent.getStringArrayListExtra("cname");
        did=intent.getStringArrayListExtra("did");
        dname=intent.getStringArrayListExtra("dname");
        ArrayAdapter<String> ad=new ArrayAdapter<>(Add_User.this, android.R.layout.simple_spinner_dropdown_item,spinnerItems);
        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        usertype="notselect";
                        cardView2.setVisibility(View.GONE);
                        cardView3.setVisibility(View.GONE);
                        break;
                    case 1:
                        usertype="Admin";
                        status="a";
                        cardView2.setVisibility(View.GONE);
                        cardView3.setVisibility(View.GONE);
                        break;
                    case 2:
                        usertype="Teacher";
                        status="t";
                        cardView2.setVisibility(View.GONE);
                        ArrayAdapter<String> ad=new ArrayAdapter<>(Add_User.this, android.R.layout.simple_spinner_dropdown_item,dname);
                        searchableSpinner.setAdapter(ad);
                        cardView3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        usertype="Student";
                        status="s";
                        ArrayAdapter<String> ad1=new ArrayAdapter<>(Add_User.this, android.R.layout.simple_spinner_dropdown_item,cname);
                        dynamicSpinner.setAdapter(ad1);
                        cardView2.setVisibility(View.VISIBLE);
                        cardView3.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseid =cid.get(position);
                courseName2=cname.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptId=did.get(position);
                deptName=dname.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cName=coursename.getText().toString();
                yoj=yearofjoining.getText().toString();
                emailid=email.getText().toString();
                if(cName.isEmpty())
                {
                    coursename.setError("Field Can't be empty");
                    coursename.requestFocus();
                    return;
                }
                else if(yoj.isEmpty())
                {
                    yearofjoining.setError("Field Can't be empty");
                    yearofjoining.requestFocus();
                    return;
                }
                else if(yoj.length()!=4)
                {
                    yearofjoining.setError("digits must be 4");
                    yearofjoining.requestFocus();
                    return;
                }
                else if(emailid.isEmpty())
                {
                    email.setError("Field Can't be empty");
                    email.requestFocus();
                    return;
                }

                else if(usertype.equalsIgnoreCase("notselect"))
                {
                    Toast.makeText(Add_User.this, "Select User Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
                {
                    email.setError("Enter a Valid Email");
                    email.requestFocus();
                    return;
                }
                else if (usertype.equalsIgnoreCase("Student") && courseid.equalsIgnoreCase("notselect"))
                {
                    Toast.makeText(Add_User.this, "!Please Select Course", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (usertype.equalsIgnoreCase("Teacher") && deptId.equalsIgnoreCase("notselect"))
                {
                    Toast.makeText(Add_User.this, "!Please Select Department", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    password=String.valueOf(new Random().nextInt(9999));
                    username=yoj.substring(2)+cName.toUpperCase()+password;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_User.this);
                    builder.setTitle("Generated UserId And Password").setIcon(R.drawable.adduser).setMessage("If you want to E-mail username and password to user and add  user press the Register button? "+"\n\n"+
                            "User Name  :- "+username+"\n"+
                            "Password :- "+password+"\n"+
                            "User Type :- "+usertype+"\n")
                            .setCancelable(false).setPositiveButton("Register", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            registerUser();

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
            }
        });

    }

    private  void registerUser()
    {
        new RegisterUser().execute();
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailid});
        intent.putExtra(Intent.EXTRA_SUBJECT,"E-Institute Learning");
        if(status.equalsIgnoreCase("t")) {
            intent.putExtra(Intent.EXTRA_TEXT, "Now You are the member of E-Institute Learning. below are your login details \n\n" + "User Name: " + username + "\n Password: " + password + "\n User Type: " + usertype + "\n Department Name: " + deptName.toUpperCase());
        }
        else
        {
            intent.putExtra(Intent.EXTRA_TEXT, "Now You are the member of E-Institute Learning. below are your login details \n\n" + "User Name: " + username + "\n Password: " + password + "\n User Type: " + usertype + "\n Course Name: " + courseName2.toUpperCase());

        }
        try
        {
            startActivity(Intent.createChooser(intent,"Email via..."));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(Add_User.this, "There Are No E-mail Client", Toast.LENGTH_SHORT).show();
        }
    }

    class RegisterUser extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Connection connection= ConnectionP.getCon();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("insert into login values('"+username+"','"+password+"','"+status+"')");

                if(status.equalsIgnoreCase("t"))
                {
                    stmt.executeUpdate("insert into teacher (Id,Email) values('"+username+"','"+emailid+"')");
                    stmt.executeUpdate("insert into dept_teacher  values('"+username+"','"+deptId+"')");
                }
                if(status.equalsIgnoreCase("s"))
                {
                    stmt.executeUpdate("insert into student (Id,Email) values('"+username+"','"+emailid+"')");
                    stmt.executeUpdate("insert into course_student  values('"+username+"','"+courseid+"')");
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
        protected void onPostExecute(Void aVoid) {

            if (b)
            {
                Toast.makeText(Add_User.this, "User Successfully Registered", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Add_User.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}