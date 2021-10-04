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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.Admin.Course_View;
import com.example.online_examination_system.Admin.Student_Information;
import com.example.online_examination_system.Admin_Bean.Student_Information_Institute_Bean;
import com.example.online_examination_system.Admin_Bean.Student_Information_Personal_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;


public class Student_Information_Adapter extends BaseAdapter implements Filterable
{
  Context context;
  LayoutInflater layoutInflater;
  ArrayList<String> arrayList,fullName;
  Student_Information_Personal_Bean student_information_personal_bean;
    ArrayList<String> mStringFilterList;
    ValueFilter valueFilter;
    String type,student_id="";
    public Student_Information_Adapter(Context context, ArrayList<String> arrayList,ArrayList<String> fullName,Student_Information_Personal_Bean student_information_personal_bean, String type)
    {

        this.context = context;
        this.type=type;
        this.arrayList = arrayList;
        this.fullName=fullName;
        this.student_information_personal_bean=student_information_personal_bean;
        layoutInflater=LayoutInflater.from(context);
        if(type.equalsIgnoreCase("id"))
        {
            mStringFilterList = arrayList;
        }
        if (type.equalsIgnoreCase("name"))
        {
            mStringFilterList = fullName;
        }
    }

    @Override
    public int getCount() {
        if (type.equalsIgnoreCase("id")) {
            return arrayList.size();
        }
        else
        {
            return fullName.size(); 
        }
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
        View view=layoutInflater.inflate(R.layout.astudentinformationcustomrow,null);
        Student_Information_Institute_Bean student_information_institute_bean;
        String courseNameFinal=" NA";
        TextView sid=(TextView)view.findViewById(R.id.asisid);
        TextView sname=(TextView)view.findViewById(R.id.asisn);
        TextView scourse=(TextView)view.findViewById(R.id.asisc);
        Button Info=(Button)view.findViewById(R.id.asinfo1);
        Button Remove=(Button)view.findViewById(R.id.asupdate1);
        sid.setText("Student Id :- "+arrayList.get(position));
        sname.setText("Student Name :- "+fullName.get(position));
       student_information_institute_bean=student_information_personal_bean.getInstitute_beans().get(position);
       if(student_information_institute_bean!=null)
       {
           courseNameFinal="";
           for (int i = 0; i < student_information_institute_bean.getCourseName().size(); i++)
           {
              courseNameFinal=courseNameFinal+student_information_institute_bean.getCourseName().get(i)+",";
           }
       }
       else
       {
           courseNameFinal="NA";
       }

       scourse.setText("Course Name :- "+courseNameFinal);

       Info.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Student_Information_Institute_Bean student_information_institute_bean1,student_information_institute_bean2;
               String fullcourses="",fullBatches="";
               student_information_institute_bean1=student_information_personal_bean.getInstitute_beans().get(position);
               if(student_information_institute_bean1!=null)
               {
                   for (int i = 0; i < student_information_institute_bean1.getCourseName().size(); i++)
                   {
                       fullcourses=fullcourses+student_information_institute_bean1.getCourseName().get(i)+",";
                   }
               }
               else
               {
                   fullcourses="NA";
               }


               student_information_institute_bean2=student_information_personal_bean.getStudent_information_institute_beans_batch().get(position);

               if (student_information_institute_bean2!=null)
               {
                   for (int i = 0; i < student_information_institute_bean2.getBatchName().size(); i++)
                   {
                       fullBatches=fullBatches+student_information_institute_bean2.getBatchName().get(i)+",";
                   }
               }
               else
               {
                   fullBatches="NA";
               }

               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setTitle("Student Information").setIcon(R.drawable.eye);
               View customLayout= layoutInflater.inflate(R.layout.admin_student_info_custom_popup, null);
               builder.setView(customLayout);
               TextView sid=(TextView)customLayout.findViewById(R.id.asid);
               TextView scorses=(TextView)customLayout.findViewById(R.id.ascourse);
               TextView sbatch=(TextView)customLayout.findViewById(R.id.asbatch);
               TextView fName=(TextView)customLayout.findViewById(R.id.asfname);
               TextView mName=(TextView)customLayout.findViewById(R.id.asmname);
               TextView lName=(TextView)customLayout.findViewById(R.id.aslname);
               TextView mobile=(TextView)customLayout.findViewById(R.id.asmobileno);
               TextView email=(TextView)customLayout.findViewById(R.id.asemail);

               sid.setText("Student's User ID :- "+student_information_personal_bean.getsID().get(position));
               scorses.setText("Student's Courses :- "+fullcourses);
               sbatch.setText("Student's Batches :- "+fullBatches);
               fName.setText("First Name :- "+student_information_personal_bean.getFirstName().get(position));
               mName.setText("Middle Name :- "+student_information_personal_bean.getMiddleName().get(position));
               lName.setText("Last Name :- "+student_information_personal_bean.getLastName().get(position));
               mobile.setText("Mobile Number :- "+student_information_personal_bean.getMobileNo().get(position));
               email.setText("E-Mail ID :- "+student_information_personal_bean.getEmail().get(position));
               builder.setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
               AlertDialog dialog = builder.create();
               dialog.show();
           }
       });

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 student_id=student_information_personal_bean.getsID().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remove Student").setIcon(R.drawable.del2).setMessage("Do You Really Want To Remove This Student From Records ??")
                        .setCancelable(false).setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
        return view;
    }

    @Override
    public Filter getFilter()
    {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<String> filterList = new ArrayList<String>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        String bean=mStringFilterList.get(i);
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            if(type.equalsIgnoreCase("id")) {
                arrayList = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
            if(type.equalsIgnoreCase("name")) {
                fullName = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        }

    }

    private void delete()
    {
        Toast.makeText(context, "Please! Wait", Toast.LENGTH_SHORT).show();
        new RemoveStudent().execute();
        context.startActivity(new Intent(context, Student_Information.class));
        ((Activity)context).finish();
    }
    class RemoveStudent extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Connection connection= ConnectionP.getCon();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("delete from student where Id='"+student_id+"'");
                stmt.executeUpdate("delete from batch_student where Id='"+student_id+"'");
                stmt.executeUpdate("delete from course_student where Id='"+student_id+"'");
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
                Toast.makeText(context, "Student Successfully Removed", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
