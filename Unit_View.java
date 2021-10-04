package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.PullInLoader;
import com.example.online_examination_system.Teacher_Adapter.Unit_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Unit_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Unit_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Unit_View extends AppCompatActivity {
 Unit_View_Bean unit_view_bean;
 Unit_View_Operation unit_view_operation;
 SessionManage sessionManage;
 Unit_View_Adapter unit_view_adapter;
 TextView totalUnits;
  ImageView addUnit;
  ListView listView;
  PullInLoader pullInLoader;

  String s0="",s1="",s2="",s3="",s4="",s5="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit__view);
        unit_view_operation=new Unit_View_Operation();
        sessionManage=new SessionManage(Unit_View.this);
        totalUnits=(TextView)findViewById(R.id.ttuv1);
        addUnit=(ImageView)findViewById(R.id.tuva1);
        listView=(ListView)findViewById(R.id.tuvlt1);
        pullInLoader=(PullInLoader)findViewById(R.id.tuvpio1);
        new Async().execute();
        addUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s0=sessionManage.getTcoursecode();
                s1=sessionManage.getTsubjectcode();
                AlertDialog.Builder builder = new AlertDialog.Builder(Unit_View.this);
                builder.setTitle("Add Unit").setIcon(R.drawable.addcourse);
                View customLayout= getLayoutInflater().inflate(R.layout.unitviewcustominsertpopup, null);
                builder.setView(customLayout);
                EditText un=customLayout.findViewById(R.id.tuviun1);
                EditText uc=customLayout.findViewById(R.id.tuviuc1);
                EditText ttopic=customLayout.findViewById(R.id.tuvitt1);
                EditText tlecture=customLayout.findViewById(R.id.tuvitlr1);

                builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insert(un.getText().toString(),uc.getText().toString(),ttopic.getText().toString(),tlecture.getText().toString());

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

    public void ulHome(View view)
    {
        startActivity(new Intent(Unit_View.this,Teacher_Home_Page.class));
        finish();
    }


    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                  unit_view_bean=unit_view_operation.getUnits(sessionManage.getTsubjectcode());
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
            if(unit_view_bean==null)
            {
                Toast.makeText(Unit_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                pullInLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                unit_view_adapter=new Unit_View_Adapter(Unit_View.this,unit_view_bean,sessionManage);
                totalUnits.setText("(Total Units :- "+unit_view_bean.getTotalunits()+")");
                listView.setAdapter(unit_view_adapter);
                pullInLoader.setVisibility(View.INVISIBLE);
            }
            super.onPostExecute(aVoid);

        }

    }

    private void insert(String toString, String toString1, String toString2, String toString3)
    {
       s3=toString;
       s2=toString1;
       s4=toString2;
       s5=toString3;
       new InsertUnit().execute();
       startActivity(new Intent(Unit_View.this,Unit_View.class));
       finish();
    }

    class InsertUnit extends AsyncTask<Void, Void, Void>
    {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                Connection connection= ConnectionP.getCon();
                Statement stmt=connection.createStatement();
                stmt.executeUpdate("INSERT into unit values('"+s0+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')");
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
                Toast.makeText(Unit_View.this, "Unit Successfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Unit_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}