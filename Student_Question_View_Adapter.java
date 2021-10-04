package com.example.online_examination_system.Student_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.online_examination_system.R;
import com.example.online_examination_system.Student_Bean.Student_Question_View_Bean;

public class Student_Question_View_Adapter extends BaseAdapter
{
     Context context;
     LayoutInflater layoutInflater;
     Student_Question_View_Bean student_question_view_bean;

    public Student_Question_View_Adapter(Context context, Student_Question_View_Bean student_question_view_bean)
    {
        this.context = context;
        this.student_question_view_bean = student_question_view_bean;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return student_question_view_bean.getQuestionName().size();
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
        View view=layoutInflater.inflate(R.layout.studentquestionviewcustomrow,null);
        TextView quesNum=(TextView)view.findViewById(R.id.sqvqn1);
        TextView quesName=(TextView)view.findViewById(R.id.sqvqname1);
        TextView optionA=(TextView)view.findViewById(R.id.sqvoa1);
        TextView optionB=(TextView)view.findViewById(R.id.sqvob1);
        TextView optionC=(TextView)view.findViewById(R.id.sqvoc1);
        TextView optionD=(TextView)view.findViewById(R.id.sqvod1);
        Button Show=(Button)view.findViewById(R.id.sqvsa1);

        quesNum.setText("Ques. No. :- "+student_question_view_bean.getQuestionNumber().get(position));
        quesName.setText(student_question_view_bean.getQuestionName().get(position));
        optionA.setText("a) "+student_question_view_bean.getOptionA().get(position));
        optionB.setText("b) "+student_question_view_bean.getOptionB().get(position));
        optionC.setText("c) "+student_question_view_bean.getOptionC().get(position));
        optionD.setText("d) "+student_question_view_bean.getOptionD().get(position));

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Question Information").setIcon(R.drawable.education).setMessage("Question Number :- "+student_question_view_bean.getQuestionNumber().get(position)+"\n"+
                        "Question :- "+"\n"+student_question_view_bean.getQuestionName().get(position)+"\n\n"+
                        "Correct Option :- "+student_question_view_bean.getAnswer().get(position)+"\n")
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
}
