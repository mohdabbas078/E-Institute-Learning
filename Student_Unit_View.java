package com.example.online_examination_system.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student_Adapter.Student_Subject_View_Adapter;
import com.example.online_examination_system.Student_Adapter.Student_Unit_View_Adapter;
import com.example.online_examination_system.Student_Bean.Student_Unit_View_Bean;
import com.example.online_examination_system.Student_Operation.Student_Unit_View_Operation;

import java.util.List;

public class Student_Unit_View extends AppCompatActivity {
    Student_Unit_View_Bean student_unit_view_bean;
    Student_Unit_View_Adapter student_unit_view_adapter;
    Student_Unit_View_Operation student_unit_view_operation;
    SessionManage sessionManage;

    ListView listView;
    TextView totalUnits;
    CircularDotsLoader circularDotsLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__unit__view);
        student_unit_view_operation=new Student_Unit_View_Operation();
        sessionManage=new SessionManage(Student_Unit_View.this);
        listView=(ListView)findViewById(R.id.suvlt1);
        totalUnits=(TextView)findViewById(R.id.stuv1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.suvcdl1);
        new Async().execute();
    }


    public void sulHome(View view)
    {
        startActivity(new Intent(Student_Unit_View.this,Student_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {

                student_unit_view_bean=student_unit_view_operation.getUnits(sessionManage.getTsubjectcode());

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
            if(student_unit_view_bean==null)
            {
                Toast.makeText(Student_Unit_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                student_unit_view_adapter=new Student_Unit_View_Adapter(Student_Unit_View.this, sessionManage, student_unit_view_bean);
                totalUnits.setText("(Total Units :- "+student_unit_view_bean.getTotalunits()+")");
                listView.setAdapter(student_unit_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);
        }

    }
}