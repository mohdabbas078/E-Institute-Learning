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
import com.example.online_examination_system.Teacher_Adapter.Batch_View_Adapter;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Bean.Batch_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Batch_View_Operation;

import java.sql.Connection;
import java.sql.Statement;

public class Batch_View extends AppCompatActivity {
   ListView listView;
   Batch_View_Adapter batch_view_adapter;
   Batch_View_Operation batch_view_operation;
   Batch_View_Bean batch_view_bean;
   SessionManage sessionManage;
   PullInLoader pullInLoader;
   TextView totalBatch;
   ImageView add;
   String bname,bcode,bstartdate,benddate,bstarttime,bendtime,btotalstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch__view);
        listView=(ListView)findViewById(R.id.tlt2);
        batch_view_operation=new Batch_View_Operation();
        sessionManage=new SessionManage(Batch_View.this);
        pullInLoader=(PullInLoader)findViewById(R.id.pio2);
        totalBatch=(TextView)findViewById(R.id.ttotalbatch1);
        add=(ImageView)findViewById(R.id.taddbatch1);
         new Async().execute();

         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(Batch_View.this);

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

    public void blHome(View view)
    {
        startActivity(new Intent(Batch_View.this,Teacher_Home_Page.class));
        finish();
    }

    class Async extends AsyncTask<Void, Void, Void> {

        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

                batch_view_bean=batch_view_operation.getBatchList(sessionManage.getTcoursecode());


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
            if(batch_view_bean!=null)
            {
                batch_view_adapter=new Batch_View_Adapter(Batch_View.this,batch_view_bean.getBatchName(),batch_view_bean.getBatchCode(),
                        batch_view_bean.getTotalStudents(),batch_view_bean,sessionManage);
                totalBatch.setText("(Total Batches :- "+batch_view_bean.getTotalBatch()+")");
                listView.setAdapter(batch_view_adapter);
                pullInLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                totalBatch.setText("(Total Batches :- 0)");
                pullInLoader.setVisibility(View.INVISIBLE);
                Toast.makeText(Batch_View.this, "No Data Found", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(Batch_View.this,Batch_View.class));
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
                 i=statement.executeUpdate("INSERT into batch_detail values('"+bname+"','"+bcode+"','"+sessionManage.getTcoursecode()+"','"+bstartdate+"','"+benddate+"','"+bstarttime+"','"+bendtime+"','"+btotalstudent+"')");
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
                Toast.makeText(Batch_View.this,"Batch Sucessfully Added", Toast.LENGTH_SHORT).show();
            }
         else
            {
                Toast.makeText(Batch_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}