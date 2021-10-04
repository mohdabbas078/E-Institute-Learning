package com.example.online_examination_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.Admin.Admin_Home_Page;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student.Student_Home_Page;
import com.example.online_examination_system.Teacher.Teacher_Home_Page;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {
TextView textView,textView2,textView3,textView4;
LinearLayout linearLayout;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.titlewelcome);
        imageView=(ImageView)findViewById(R.id.logo1);
        textView2=(TextView)findViewById(R.id.powerbyabbas);
        textView3=(TextView)findViewById(R.id.titleinstitute);
        textView4=(TextView)findViewById(R.id.titlto);
        linearLayout=(LinearLayout)findViewById(R.id.linearlt);
        Animation animation1= AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.zoom);
        textView.startAnimation(animation1);
        Animation animation2= AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.rotate);
        imageView.startAnimation(animation2);
        Animation animation3= AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.entrance);
        linearLayout.startAnimation(animation3);
        textView3.setAnimation(animation3);
        textView4.setAnimation(animation3);
        Animation animation4= AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.fade);
        textView2.startAnimation(animation4);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView4.setAnimation(animation2);
            }
        },3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                makeDecision();

                finish();
            }
        },6000);


    }

    public void makeDecision()
    {
        SessionManage sessionManage=new SessionManage(Splash_Screen.this);
        if(sessionManage.getUsername().equals("") ||   sessionManage.getStatus().equals(""))
        {
            startActivity(new Intent(Splash_Screen.this,Home.class));
        }
        else if(sessionManage.getStatus().equalsIgnoreCase("s"))
        {
            startActivity(new Intent(Splash_Screen.this, Student_Home_Page.class));
        }
        else if(sessionManage.getStatus().equalsIgnoreCase("t"))
        {
            startActivity(new Intent(Splash_Screen.this, Teacher_Home_Page.class));
        }
        else if(sessionManage.getStatus().equalsIgnoreCase("a"))
        {
            startActivity(new Intent(Splash_Screen.this, Admin_Home_Page.class));
        }
        else
        {
            Toast.makeText(this, "Something Went Wrong! Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onStop()
    {
        Toast.makeText(Splash_Screen.this, "Service Started....", Toast.LENGTH_LONG).show();
        super.onStop();
    }
}