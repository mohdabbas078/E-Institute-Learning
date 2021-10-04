package com.example.online_examination_system.Admin;

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

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Admin_Adapter.Dept_Batch_View_Adapter;
import com.example.online_examination_system.Admin_Bean.Dept_Batch_View_Bean;
import com.example.online_examination_system.Admin_Operation.Dept_Batch_View_Operation;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher.Batch_View;
import com.example.online_examination_system.Teacher_Adapter.Batch_View_Adapter;

import java.sql.Connection;
import java.sql.Statement;

public class Dept_Batch_View extends AppCompatActivity {
SessionManage sessionManage;
 Dept_Batch_View_Bean dept_batch_view_bean;
 Dept_Batch_View_Adapter dept_batch_view_adapter;
 Dept_Batch_View_Operation dept_batch_view_operation;
    ListView listView;
    CircularDotsLoader circularDotsLoader;
    TextView totalBatch;
    ImageView add;
    String bname,bcode,bstartdate,benddate,bstarttime,bendtime,btotalstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept__batch__view);
        sessionManage=new SessionManage(Dept_Batch_View.this);
        dept_batch_view_operation=new Dept_Batch_View_Operation();

        listView=(ListView)findViewById(R.id.tlt2);
        totalBatch=(TextView)findViewById(R.id.ttotalbatch1);
        add=(ImageView)findViewById(R.id.taddbatch1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.tqvcdl1);
        new ViewBatch().execute();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dept_Batch_View.this);

                builder.setTitle("Add Batch").setIcon(R.drawable.addcourse);

                View customLayout= getLayoutInflater().inflate(R.layout.batchviewcustompopup, null);
                builder.setView(customLayout).setCancelable(false);

                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        EditText bn=customLayout.findViewById(R.id.tbn1);
                        EditText bc=customLayout.findViewById(R.id.tbc1);
                        EditText bsd=customLayout.findViewById(R.id.tbsd1);
                        EditText bed=customLayout.findViewById(R.id.tbed1);
                        EditText bst=customLayout.findViewById(R.id.tbst1);
                        EditText bet=customLayout.findViewById(R.id.tbet1);
                        EditText bts=customLayout.findViewById(R.id.tts1);
                        fetchData(bn.getText().toString(),bc.getText().toString(),bsd.getText().toString(),bed.getText().toString(),bst.getText().toString(),
                                bet.getText().toString(),bts.getText().toString());
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

    }

    public void deptbatchhome(View view)
    {
        startActivity(new Intent(Dept_Batch_View.this,Admin_Home_Page.class));
        finish();
    }


    class ViewBatch extends AsyncTask<Void, Void, Void> {

        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

                dept_batch_view_bean=dept_batch_view_operation.getBatchList(sessionManage.getCoursecode());


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
            if(dept_batch_view_bean!=null)
            {
                 dept_batch_view_adapter=new Dept_Batch_View_Adapter(Dept_Batch_View.this,sessionManage,dept_batch_view_bean);

                totalBatch.setText("(Total Batches :- "+dept_batch_view_bean.getTotalBatch()+")");
                listView.setAdapter(dept_batch_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                totalBatch.setText("(Total Batches :- 0)");
                circularDotsLoader.setVisibility(View.INVISIBLE);
                Toast.makeText(Dept_Batch_View.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }

    void fetchData(String cn,String cc,String csd,String ced,String cst,String cet,String cts)
    {
        bname=cn;
        bcode=cc;
        bstartdate=csd;
        benddate=ced;
        bstarttime=cst;
        bendtime=cet;
        btotalstudent=cts;
        new AddBatch().execute();
        startActivity(new Intent(Dept_Batch_View.this,Dept_Batch_View.class));
        finish();
    }
    class AddBatch extends AsyncTask<Void, Void, Void>
    {
        int i=10;
        @Override

        protected Void doInBackground(Void... voids) {

            try
            {
                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                i=statement.executeUpdate("INSERT into batch_detail values('"+bname+"','"+bcode+"','"+sessionManage.getCoursecode()+"','"+bstartdate+"','"+benddate+"','"+bstarttime+"','"+bendtime+"','"+btotalstudent+"','"+sessionManage.getDeptCode()+"')");
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

            if(i==1)
            {
                Toast.makeText(Dept_Batch_View.this,"Batch Sucessfully Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Dept_Batch_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}