package com.example.online_examination_system.Teacher_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.online_examination_system.R;

import java.util.ArrayList;

public class CourseBatchViewListAdapter extends BaseAdapter
{
   Context context;
   //int[] img;
   ArrayList<String> coursename,coursecode;
   LayoutInflater layoutInflater;

    public CourseBatchViewListAdapter(Context context,  ArrayList<String> coursename, ArrayList<String> coursecode)
    {
        this.context = context;

        this.coursename = coursename;
        this.coursecode = coursecode;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return coursename.size();
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
        View view=layoutInflater.inflate(R.layout.coursebatchviewcustomlayout,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.timage1);
        TextView coursename1=(TextView)view.findViewById(R.id.tcoursename1);
        TextView coursecode1=(TextView)view.findViewById(R.id.tcoursecode1);
        TextView clikme=(TextView)view.findViewById(R.id.tclickme1);


        coursename1.setText("Course Name :- "+coursename.get(position));
        coursecode1.setText("Course Code :- "+coursecode.get(position));
        clikme.setText("Show Batches");

        return view;
    }
}
