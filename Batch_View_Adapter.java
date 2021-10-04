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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher.Batch_View;
import com.example.online_examination_system.Teacher.Student_List;
import com.example.online_examination_system.Teacher_Bean.Batch_View_Bean;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Batch_View_Adapter extends BaseAdapter
{
    Context context;
    AlertDialog.Builder builder;
    ArrayList<String> batchname,batchcode,totalstudents;
    Batch_View_Bean batch_view_bean;
    LayoutInflater layoutInflater;
    View view;
   String s1,s2,s3,s4,s5,s6,s7,batchcode1;
   SessionManage sessionManage;
    public Batch_View_Adapter(Context context, ArrayList<String> batchname, ArrayList<String> batchcode, ArrayList<String> totalstudents, Batch_View_Bean batch_view_bean,SessionManage sessionManage)
    {
        this.context = context;
        this.batch_view_bean=batch_view_bean;
        this.batchname = batchname;
        this.batchcode = batchcode;
        this.totalstudents=totalstudents;
        this.sessionManage=sessionManage;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return batchname.size();
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
        view=layoutInflater.inflate(R.layout.batchviewcustomrow,null);
        TextView coursename1=(TextView)view.findViewById(R.id.tbatchname1);
        TextView coursecode1=(TextView)view.findViewById(R.id.tbatchcode1);
        TextView totalStudents1=(TextView)view.findViewById(R.id.ttotalstudents1);
        Button info=(Button)view.findViewById(R.id.tbatchinfo1);
        Button students=(Button)view.findViewById(R.id.tbatcstudents1);
        Button update=(Button)view.findViewById(R.id.tbatchupdate1);
        Button delete=(Button)view.findViewById(R.id.tbatchdelete1);

        coursename1.setText("Batch Name :- "+batchname.get(position));
        coursecode1.setText("Batch Code :- "+batchcode.get(position));
        totalStudents1.setText("Total Students :- "+totalstudents.get(position));

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(context);
                builder.setMessage("Batch Name :- "+batch_view_bean.getBatchName().get(position)+"\n"+
                        "Batch Code :- "+batch_view_bean.getBatchCode().get(position)+"\n"+
                        "Batch Start Date :- "+batch_view_bean.getStartDate().get(position)+"\n"+
                        "Batch End Date :- "+batch_view_bean.getEndDate().get(position)+"\n"+
                        "Batch Start Time :- "+batch_view_bean.getStartTime().get(position)+"\n"+
                        "Batch End Time :- "+batch_view_bean.getEndTime().get(position)+"\n"+
                        "Total Students :- "+batch_view_bean.getTotalStudents().get(position)+"\n")
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("Batch Information");
                alert.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Update Batch").setIcon(R.drawable.updte1);

                View customLayout= layoutInflater.inflate(R.layout.batchviewupdatepopupcustom, null);
                builder.setView(customLayout);
                EditText bn=customLayout.findViewById(R.id.tubn1);
                EditText bsd=customLayout.findViewById(R.id.tbsd1);
                EditText bed=customLayout.findViewById(R.id.tbed1);
                EditText bst=customLayout.findViewById(R.id.tbst1);
                EditText bet=customLayout.findViewById(R.id.tbet1);
                EditText bts=customLayout.findViewById(R.id.tts1);
                bn.setText(batch_view_bean.getBatchName().get(position));
                bsd.setText(batch_view_bean.getStartDate().get(position));
                bed.setText(batch_view_bean.getEndDate().get(position));
                bst.setText(batch_view_bean.getStartTime().get(position));
                bet.setText(batch_view_bean.getEndTime().get(position));
                bts.setText(batch_view_bean.getTotalStudents().get(position));
                batchcode1=batch_view_bean.getBatchCode().get(position);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {


                        fetchData(bn.getText().toString(),bsd.getText().toString(),bed.getText().toString(),bst.getText().toString(),
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batchcode1=batch_view_bean.getBatchCode().get(position);
                builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Batch").setIcon(R.drawable.del2).setMessage("Do You Really Want To Delete This Batch ?")
                        .setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
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

        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sessionManage.setBatchCode(batch_view_bean.getBatchCode().get(position));
                 context.startActivity(new Intent(context, Student_List.class));
            }
        });
        return view;
    }

    private void fetchData(String toString,  String toString2, String toString3, String toString4, String toString5, String toString6)
    {
        s1=toString;
        s3=toString2;
        s4=toString3;
        s5=toString4;
        s6=toString5;
        s7=toString6;
        new Async().execute();
        context.startActivity(new Intent(context,Batch_View.class));
    }

    class Async extends AsyncTask<Void, Void, Void> {

        int i = 0;
        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                 i=statement.executeUpdate("UPDATE batch_detail SET Batch_Name='"+s1+"',Batch_Start_Date='"+s3+"', Batch_End_Date= '"+s4+"',Batch_Start_Time='"+s5+"',Batch_End_Time='"+s6+"',Total_Student='"+s7+"' WHERE Batch_Code='"+batchcode1+"' ");

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {
            if(i==1)
            {
                Toast.makeText(context, "Batch SucessFully Updated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);

        }

    }

    private void delete()
    {
      new Delete().execute();
      context.startActivity(new Intent(context,Batch_View.class));
    }

    class Delete extends AsyncTask<Void, Void, Void> {

        int i = 0;
        @Override

        protected Void doInBackground(Void... voids) {

            try
            {

                Connection connection= ConnectionP.getCon();
                Statement statement=connection.createStatement();
                i=statement.executeUpdate("DELETE FROM batch_detail WHERE Batch_Code='"+batchcode1+"' ");

            }

            catch(Exception e)

            {

                Log.d("myapp",String.valueOf(e));

            }

            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {
            if(i==1)
            {
                Toast.makeText(context, "Batch SucessFully Deleted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(aVoid);

        }

    }
}
