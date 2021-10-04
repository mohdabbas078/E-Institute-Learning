package com.example.online_examination_system.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Admin_Adapter.Student_Information_Adapter;
import com.example.online_examination_system.Admin_Bean.Student_Information_Personal_Bean;
import com.example.online_examination_system.Admin_Operation.Student_Information_Operation;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Student_Information extends AppCompatActivity {
    EditText searchView;
  TextView title1,totalStudent;
  ListView listView;
  ImageView searchBy;
   ArrayList<String> fullName;
  CircularDotsLoader circularDotsLoader;
  Student_Information_Adapter student_information_adapter;
  SessionManage sessionManage;
  Student_Information_Operation student_information_operation;
  Student_Information_Personal_Bean student_information_personal_bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__information);
        searchView=(EditText)findViewById(R.id.searchView);
        title1=(TextView)findViewById(R.id.sititle1);
        listView=(ListView)findViewById(R.id.listview);
        totalStudent=(TextView)findViewById(R.id.totalStu);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.tqvcdl1) ;
         searchBy=(ImageView)findViewById(R.id.searchby);
         fullName=new ArrayList<>();
        title1.setSelected(true);
        sessionManage=new SessionManage(Student_Information.this);
        student_information_operation=new Student_Information_Operation();

        Toast.makeText(this, "Students List", Toast.LENGTH_SHORT).show();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        new StudentInfo().execute();

       searchBy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(Student_Information.this, searchBy);

                popup.getMenuInflater().inflate(R.menu.studentfilteritem, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.sfid:
                                student_information_adapter=new Student_Information_Adapter(Student_Information.this,student_information_personal_bean.getsID(),fullName,student_information_personal_bean,"id");
                                listView.setAdapter(student_information_adapter);
                                break;

                            case R.id.sfname:

                                student_information_adapter=new Student_Information_Adapter(Student_Information.this,student_information_personal_bean.getsID(),fullName,student_information_personal_bean,"name");
                                listView.setAdapter(student_information_adapter);
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

      searchView.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence query, int start, int before, int count) {
          student_information_adapter.getFilter().filter(query);
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
  });
    }

    class StudentInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try

            {

                student_information_personal_bean=student_information_operation.getData();
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
           if(student_information_personal_bean!=null)
           {
               for (int i=0; i<student_information_personal_bean.getsID().size();i++)
               {
                   fullName.add(student_information_personal_bean.getFirstName().get(i)+" "+student_information_personal_bean.getMiddleName().get(i)+
                           " "+student_information_personal_bean.getLastName().get(i));
               }

               student_information_adapter=new Student_Information_Adapter(Student_Information.this,student_information_personal_bean.getsID(),fullName,student_information_personal_bean,"id");
               listView.setAdapter(student_information_adapter);
               totalStudent.setText("(Total Students :-"+student_information_personal_bean.getTotalStudents()+")");
               circularDotsLoader.setVisibility(View.INVISIBLE);
               getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
           }
           else
           {
               Toast.makeText(Student_Information.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
               circularDotsLoader.setVisibility(View.INVISIBLE);
               getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
           }
            super.onPostExecute(aVoid);

        }

    }
}