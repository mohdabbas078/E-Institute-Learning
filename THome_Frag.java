package com.example.online_examination_system.Teacher;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.online_examination_system.Teacher_Operation.ChangePassword;
import com.example.online_examination_system.Teacher_Operation.Course_Batch_View_Operation;
import com.mysql.fabric.xmlrpc.base.Params;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.transform.Result;

public class THome_Frag extends Fragment {
    Context context;
    SessionManage sessionManage;
    LayoutInflater layoutInflater;
    Animation animation,animation1;
    Button logout,batches,courses,changePass,currentQuiz;
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    String nPass="",oPass="";
    ChangePassword changePassword;
    String result;
    public THome_Frag(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_t_home_, container, false);
        sessionManage=new SessionManage(context);
        changePassword=new ChangePassword();
        animation= AnimationUtils.loadAnimation(context,R.anim.entrance);
        animation1=AnimationUtils.loadAnimation(context,R.anim.fade);
        logout=(Button)view.findViewById(R.id.tbutton6);
        batches=(Button)view.findViewById(R.id.tbutton3);
        courses=(Button)view.findViewById(R.id.tbutton4);
        currentQuiz=(Button)view.findViewById(R.id.tbutton1);
        changePass=(Button)view.findViewById(R.id.tbutton5);
        textView=(TextView)view.findViewById(R.id.teachernametitle);
        textView1=(TextView)view.findViewById(R.id.tquiz1);
        textView2=(TextView)view.findViewById(R.id.tallotedquiz1);
        textView3=(TextView)view.findViewById(R.id.tbatches1);
        textView4=(TextView)view.findViewById(R.id.tcourses1);
        textView5=(TextView)view.findViewById(R.id.tchngepass1);
        textView6=(TextView)view.findViewById(R.id.tlogout1);

        imageView1=(ImageView)view.findViewById(R.id.timg1);
        imageView2=(ImageView)view.findViewById(R.id.timg2);
        imageView3=(ImageView)view.findViewById(R.id.timg3);
        imageView4=(ImageView)view.findViewById(R.id.timg4);
        imageView5=(ImageView)view.findViewById(R.id.timg5);
        imageView6=(ImageView)view.findViewById(R.id.timg6);

        textView.setText("Welcome! "+sessionManage.getTeachername());
        textView.setAnimation(animation);
        imageView1.setAnimation(animation);
        imageView2.setAnimation(animation);
        imageView3.setAnimation(animation);
        imageView4.setAnimation(animation);
        imageView5.setAnimation(animation);
        imageView6.setAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView1.setText("CURRENT QUIZ");
                textView1.setAnimation(animation1);
                textView2.setText("ALLOTED QUIZ");
                textView2.setAnimation(animation1);
                textView3.setText("BATCHES");
                textView3.setAnimation(animation1);
                textView4.setText("COURSES");
                textView4.setAnimation(animation1);
                textView5.setText("CHANGE PASSWORD");
                textView5.setAnimation(animation1);
                textView6.setText("LOGOUT");
                textView6.setAnimation(animation1);
            }
        },3000);






        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Logout").setIcon(R.drawable.tlogout1).setMessage("Do You Really Want To Logout ?")
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

        batches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Course_Batch_View.class));
            }
        });

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Course_View.class));
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

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

        currentQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(context,Current_Quiz_View.class));
            }
        });
        return view;
    }
     private void changeTeacherPassword(String op,String np)
     {
        oPass=op;
        nPass=np;
        new Async().execute();
        context.startActivity(new Intent(context,Teacher_Home_Page.class));
         ((Activity)context).finish();
     }
    class Async extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {

            try
            {
                result=changePassword.changePass(oPass,nPass,sessionManage);
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