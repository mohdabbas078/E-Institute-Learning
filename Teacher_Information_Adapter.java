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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.Admin.Student_Information;
import com.example.online_examination_system.Admin.Teacher_Information;
import com.example.online_examination_system.Admin_Bean.Teacher_Information_Institute_Bean;
import com.example.online_examination_system.Admin_Bean.Teacher_Information_Personal_Bean;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.R;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Teacher_Information_Adapter extends BaseAdapter implements Filterable
{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> arrayList,fullName;
    Teacher_Information_Personal_Bean teacher_information_personal_bean;
    Teacher_Information_Institute_Bean teacher_information_institute_bean_batch,teacher_information_institute_bean_course;
    ArrayList<String> mStringFilterList;
    ValueFilter valueFilter;
    String type,student_id="",tId="";

    public Teacher_Information_Adapter(Context context, ArrayList<String> arrayList, ArrayList<String> fullName, Teacher_Information_Personal_Bean teacher_information_personal_bean) {
        this.context = context;
        this.arrayList = arrayList;
        this.fullName = fullName;
        layoutInflater=LayoutInflater.from(context);
        this.teacher_information_personal_bean = teacher_information_personal_bean;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        View view=layoutInflater.inflate(R.layout.teacher_informaion_custom_row,null);
        String finalDept="";
        Teacher_Information_Institute_Bean teacher_information_institute_bean;
        teacher_information_institute_bean=teacher_information_personal_bean.getTeacher_information_institute_beans_dept().get(position);
        TextView tid=(TextView)view.findViewById(R.id.atitid);
        TextView tname=(TextView)view.findViewById(R.id.atitn);
        TextView tdept=(TextView)view.findViewById(R.id.atitd) ;
        TextView dept=(TextView)view.findViewById(R.id.atitd);
        Button Info=(Button)view.findViewById(R.id.atinfo1);
        Button Remove=(Button)view.findViewById(R.id.atupdate1);
        tid.setText("Teacher's Id :- "+arrayList.get(position));
        tname.setText("Teacher's Name :- "+fullName.get(position));
        if(teacher_information_institute_bean!=null)
        {
            for(int i=0;i<teacher_information_institute_bean.getDeptName().size();i++)
            {
                finalDept=finalDept+teacher_information_institute_bean.getDeptName().get(i)+",";
            }
        }
        else
        {
            finalDept="NA";
        }

        tdept.setText("Alloted Dept :- "+finalDept);

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher_information_institute_bean_batch=teacher_information_personal_bean.getTeacher_information_institute_beans_batch().get(position);
                teacher_information_institute_bean_course=teacher_information_personal_bean.getTeacher_information_institute_beans_course().get(position);
                String fullCourse="",fullBatche="";
                if(teacher_information_institute_bean_batch!=null) {
                    for (int i = 0; i < teacher_information_institute_bean_batch.getBatchName().size(); i++)
                    {
                       fullBatche=fullBatche+teacher_information_institute_bean_batch.getBatchName().get(i)+",";
                    }
                }
                else
                {
                    fullBatche="NA";
                }


                if(teacher_information_institute_bean_course!=null)
                {
                    for (int i=0;i<teacher_information_institute_bean_course.getCourseName().size();i++)
                    {
                        fullCourse=fullCourse+teacher_information_institute_bean_course.getCourseName().get(i)+",";
                    }
                }
                else
                {
                    fullCourse="NA";
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Teacher Information").setIcon(R.drawable.eye);
                View customLayout= layoutInflater.inflate(R.layout.admin_tec_info_custom_popup, null);
                builder.setView(customLayout);
                TextView sid=(TextView)customLayout.findViewById(R.id.atid);
                TextView scorses=(TextView)customLayout.findViewById(R.id.atcourse);
                TextView sbatch=(TextView)customLayout.findViewById(R.id.atbatch);
                TextView fName=(TextView)customLayout.findViewById(R.id.atfname);
                TextView mName=(TextView)customLayout.findViewById(R.id.atmname);
                TextView lName=(TextView)customLayout.findViewById(R.id.atlname);
                TextView mobile=(TextView)customLayout.findViewById(R.id.atmobileno);
                TextView email=(TextView)customLayout.findViewById(R.id.atemail);

                sid.setText("Teachers's User ID :- "+teacher_information_personal_bean.getTeacherId().get(position));
                scorses.setText("Alloted Courses :- "+fullCourse);
                sbatch.setText("Alloted Batches :- "+fullBatche);
                fName.setText("First Name :- "+teacher_information_personal_bean.getFirstName().get(position));
                mName.setText("Middle Name :- "+teacher_information_personal_bean.getMiddleName().get(position));
                lName.setText("Last Name :- "+teacher_information_personal_bean.getLastName().get(position));
                mobile.setText("Mobile Number :- "+teacher_information_personal_bean.getMobileNo().get(position));
                email.setText("E-Mail ID :- "+teacher_information_personal_bean.getEmail().get(position));
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
                tId=teacher_information_personal_bean.getTeacherId().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remove Teacher").setIcon(R.drawable.del2).setMessage("Do You Really Want To Remove This Teacher From Records ??")
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
        new RemoveTeacher().execute();
        context.startActivity(new Intent(context, Teacher_Information.class));
        ((Activity)context).finish();
    }
    class RemoveTeacher extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
                Connection connection= ConnectionP.getCon();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("delete from teacher where Id='"+tId+"'");
                stmt.executeUpdate("delete from batch_teacher where Id='"+tId+"'");
                stmt.executeUpdate("delete from course_teacher where Id='"+tId+"'");
                stmt.executeUpdate("delete from dept_teacher where Id='"+tId+"'");
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
                Toast.makeText(context, "Teacher Successfully Removed", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }
}
