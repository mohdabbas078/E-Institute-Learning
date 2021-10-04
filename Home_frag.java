package com.example.online_examination_system.Student;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.Home;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student_Operation.SchangePassword;
import com.example.online_examination_system.Teacher.THome_Frag;
import com.example.online_examination_system.Teacher.Teacher_Home_Page;


public class Home_frag extends Fragment {

    Context context;
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6;
    Button logoutBtn,course,QuestionBank,changePass;
    Animation animation1,animation;
    LayoutInflater layoutInflater;
    String oPass="",nPass="",result="";
    SchangePassword schangePassword;
    SessionManage sessionManage;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    public Home_frag(Context context)
    {
       this.context=context;
       layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_frag,container,false);
         sessionManage=new SessionManage(context);

         schangePassword=new SchangePassword();
         animation1= AnimationUtils.loadAnimation(getContext(),R.anim.entrance);
        animation= AnimationUtils.loadAnimation(getContext(),R.anim.fade);
         logoutBtn=(Button)view.findViewById(R.id.goalbutton);
         course=(Button)view.findViewById(R.id.comfbutton);
         QuestionBank=(Button)view.findViewById(R.id.financebutton);
         changePass=(Button)view.findViewById(R.id.healthbutton);
         textView=(TextView)view.findViewById(R.id.studentnametitle);
         textView1=(TextView)view.findViewById(R.id.scourse1);
        textView2=(TextView)view.findViewById(R.id.squiz1);
        textView3=(TextView)view.findViewById(R.id.sresult1);
        textView4=(TextView)view.findViewById(R.id.squestionbank1);
        textView5=(TextView)view.findViewById(R.id.slogout1);
        textView6=(TextView)view.findViewById(R.id.schngepass1);
        imageView1=(ImageView)view.findViewById(R.id.img1);
        imageView2=(ImageView)view.findViewById(R.id.img2);
        imageView3=(ImageView)view.findViewById(R.id.img3);
        imageView4=(ImageView)view.findViewById(R.id.img4);
        imageView5=(ImageView)view.findViewById(R.id.img5);
        imageView6=(ImageView)view.findViewById(R.id.img6);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Logout").setIcon(R.drawable.logout2).setMessage("Do You Really Want To Logout ?")
                        .setCancelable(false).setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sessionManage.remove();
                        startActivity(new Intent(getContext(), Home.class));
                        getActivity().finish();
                        Toast.makeText(context, "You Are Logout", Toast.LENGTH_SHORT).show();
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
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManage.setTwoinone("one");
                startActivity(new Intent(getContext(), Student_Subject_View.class));
            }
        });
        QuestionBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManage.setTwoinone("two");
                startActivity(new Intent(getContext(), Student_Question_View.class));
            }
        });
        textView.setText("Welcome! "+sessionManage.getStudentname());
        textView.setAnimation(animation1);
        imageView1.setAnimation(animation1);
        imageView2.setAnimation(animation1);
        imageView3.setAnimation(animation1);
        imageView4.setAnimation(animation1);
        imageView5.setAnimation(animation1);
        imageView6.setAnimation(animation1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                textView1.setText(sessionManage.getCoursename());
                textView1.setAnimation(animation);
                textView2.setText("Quiz");
                textView2.setAnimation(animation);
                textView3.setText("RESULT");
                textView3.setAnimation(animation);
                textView4.setText("QUESTION BANK");
                textView4.setAnimation(animation);
                textView5.setText("LOGOUT");
                textView5.setAnimation(animation);
                textView6.setText("CHANGE PASSWORD");
                textView6.setAnimation(animation);
            }
        },3000);


    changePass.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Change Password").setIcon(R.drawable.tpass1);

            View customLayout= layoutInflater.inflate(R.layout.teacherchangepasswordpopup, null);
            builder.setView(customLayout);
            EditText oldPass=(EditText)customLayout.findViewById(R.id.toldpass);
            EditText newPass=(EditText)customLayout.findViewById(R.id.tnewpass);
            EditText confirmPass=(EditText)customLayout.findViewById(R.id.tconfirmpass);

            oldPass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (oldPass.getText().toString().equals(""))
                    {
                        oldPass.setError("Field Can't be Empty");
                        oldPass.requestFocus();
                        return;
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (oldPass.getText().toString().equals("") )
                    {
                        oldPass.setError("Field Can't be Empty");
                        oldPass.requestFocus();
                        return;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (oldPass.getText().toString().equals("") )
                    {
                        oldPass.setError("Field Can't be Empty");
                        oldPass.requestFocus();
                        return;
                    }
                }
            });

            newPass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {
                    if (oldPass.getText().toString().equals("") )
                    {
                        oldPass.setError("Field Can't be Empty");
                        oldPass.requestFocus();
                        return;
                    }



                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if (newPass.getText().toString().equals("") )
                    {
                        newPass.setError("Field Can't be Empty");
                        newPass.requestFocus();
                        return;
                    }

                }

                @Override
                public void afterTextChanged(Editable s)
                {
                    if (newPass.getText().toString().equals("") )
                    {
                        newPass.setError("Field Can't be Empty");
                        newPass.requestFocus();
                        return;
                    }

                }
            });
            confirmPass.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {
                    if (oldPass.getText().toString().equals("")  )
                    {
                        oldPass.setError("Field Can't be Empty");
                        oldPass.requestFocus();
                        return;
                    }
                    if (newPass.getText().toString().equals("") )
                    {
                        newPass.setError("Field Can't be Empty");
                        newPass.requestFocus();
                        return;
                    }
                }

                @SuppressLint("ResourceAsColor")
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    if (confirmPass.getText().toString().equals("") )
                    {
                        confirmPass.setError("Field Can't be Empty");
                        confirmPass.requestFocus();
                        return;
                    }
                    if (!confirmPass.getText().toString().equals(newPass.getText().toString()) )
                    {
                        confirmPass.setError("Not Match With New Pass");
                        confirmPass.requestFocus();
                        return;
                    }
                    if (confirmPass.getText().toString().equals(newPass.getText().toString()) )
                    {
                        confirmPass.setBackgroundColor(R.color.colorAccent);

                    }
                }

                @SuppressLint("ResourceAsColor")
                @Override
                public void afterTextChanged(Editable s)
                {
                    if (confirmPass.getText().toString().equals("") )
                    {
                        confirmPass.setError("Field Can't be Empty");
                        confirmPass.requestFocus();
                        return;
                    }
                    if (!confirmPass.getText().toString().equals(newPass.getText().toString()) )
                    {
                        confirmPass.setError("Not Match With New Pass");
                        confirmPass.requestFocus();
                        return;
                    }
                    if (confirmPass.getText().toString().equals(newPass.getText().toString()) )
                    {
                        confirmPass.setBackgroundColor(R.color.colorAccent);

                    }

                }
            });
            builder.setCancelable(false).setPositiveButton("Change", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    if(oldPass.getText().toString().equals("") || oldPass.getText().toString().equals(" ") ||
                            newPass.getText().toString().equals("") || newPass.getText().toString().equals(" ")
                            || confirmPass.getText().toString().equals("") || confirmPass.getText().toString().equals(" "))
                    {
                        Toast.makeText(context, "Please! fill all the fields", Toast.LENGTH_SHORT).show();
                    }
                    else if (!confirmPass.getText().toString().equals(newPass.getText().toString()) )
                    {
                        Toast.makeText(context, "Confirm Pass Must Be Same As New Pass", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context, "Please! Wait...", Toast.LENGTH_SHORT).show();
                        changeTeacherPassword(oldPass.getText().toString(),newPass.getText().toString());
                    }
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
        return view;
    }

    private void changeTeacherPassword(String op,String np)
    {
        oPass=op;
        nPass=np;
        new Async().execute();
        context.startActivity(new Intent(context, Student_Home_Page.class));
        ((Activity)context).finish();
    }
    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                result=schangePassword.changePass(oPass,nPass,sessionManage);
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
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);

        }

    }
}