package com.example.online_examination_system.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Admin_Bean.Course_Dao_Bean;
import com.example.online_examination_system.Admin_Bean.Deaprtment_Dao_Bean;
import com.example.online_examination_system.Admin_Operation.Course_Dao_Operation;
import com.example.online_examination_system.Admin_Operation.Department_Dao_Operation;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Home;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class Admin_Home_Page extends AppCompatActivity {
  CircleMenu circleMenu;
  SessionManage sessionManage;
  TextView username;
  Animation animation;
  Timer timer;
  Course_Dao_Bean course_dao_bean;
  Course_Dao_Operation course_dao_operation;
  Deaprtment_Dao_Bean deaprtment_dao_bean;
  Department_Dao_Operation department_dao_operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home__page);
        Toast.makeText(this, "!Please Wait Data is fetching in background", Toast.LENGTH_SHORT).show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        animation= AnimationUtils.loadAnimation(Admin_Home_Page.this,R.anim.rotate4);
        timer=new Timer();
        circleMenu=(CircleMenu)findViewById(R.id.ciclemenu);

        sessionManage=new SessionManage(Admin_Home_Page.this);
        course_dao_operation=new Course_Dao_Operation();
        department_dao_operation=new Department_Dao_Operation();
        username=(TextView)findViewById(R.id.ausername);
        username.setText("User Name :- "+sessionManage.getUsername());
             new GetCourse().execute();
        circleMenu.setMainMenu(Color.parseColor("#C1FD0773"),R.drawable.open,R.drawable.close).openMenu();
        circleMenu.setAnimation(animation);
        circleMenu .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.adduser).setAnimation(animation);
        circleMenu  .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.teacher).setAnimation(animation);
        circleMenu  .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.tbatch1).setAnimation(animation);
        circleMenu  .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.changepass1).setAnimation(animation);
        circleMenu  .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.lo2).setAnimation(animation);
        circleMenu  .addSubMenu(Color.parseColor("#C1FD0773"),R.drawable.course).setAnimation(animation);
                 circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
                     @Override
                     public void onMenuSelected(int i)
                     {

                         switch (i)
                         {
                             case 0:
                                     timer.schedule(new TimerTask() {
                                         @Override
                                         public void run() {
                                             Intent intent=new Intent(Admin_Home_Page.this,Add_User.class);
                                             intent.putExtra("cid",course_dao_bean.getCourseId());
                                             intent.putExtra("cname",course_dao_bean.getCourseName());
                                             intent.putExtra("did",deaprtment_dao_bean.getdId());
                                             intent.putExtra("dname",deaprtment_dao_bean.getdName());

                                                startActivity(intent);
                                         }
                                     },1300);
                                 break;
                             case 1:
                                 timer.schedule(new TimerTask() {
                                     @Override
                                     public void run() {
                                         startActivity(new Intent(Admin_Home_Page.this,Teacher_Information.class));
                                     }
                                 },1300);
                                 break;
                             case 2:
                                 timer.schedule(new TimerTask() {
                                     @Override
                                     public void run() {
                                         Intent intent=new  Intent(Admin_Home_Page.this,Student_Information.class);

                                         startActivity(intent);
                                     }
                                 },1300);
                                 break;
                             case 4:
                                 AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Home_Page.this);
                                 builder.setTitle("Logout").setIcon(R.drawable.lo2).setMessage("Do You Really Want To Logout ?")
                                         .setCancelable(false).setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialog, int which)
                                     {
                                         sessionManage.remove();
                                         startActivity(new Intent(Admin_Home_Page.this, Home.class));
                                         finish();
                                         Toast.makeText(Admin_Home_Page.this, "You Are Logout", Toast.LENGTH_SHORT).show();
                                     }
                                 }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                     public void onClick(DialogInterface dialog, int id) {
                                         //  Action for 'NO' Button
                                         dialog.cancel();
                                     }
                                 });

                                 AlertDialog alert = builder.create();
                                 alert.show();
                                 break;
                             case 5:
                                 timer.schedule(new TimerTask() {
                                     @Override
                                     public void run() {
                                         startActivity(new Intent(Admin_Home_Page.this,Department_Course_View.class));
                                     }
                                 },1300);
                                 break;

                         }

                     }
                 });


    }



    @Override
    protected void onResume() {
        circleMenu.openMenu();
        super.onResume();
    }

    class GetCourse extends AsyncTask<Void, Void, Void> {
        boolean b=false;
        @Override
        protected Void doInBackground(Void... voids) {

            try

            {
               course_dao_bean=course_dao_operation.getCourse_dao_bean();
               deaprtment_dao_bean=department_dao_operation.getDepart();
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
                Toast.makeText(Admin_Home_Page.this, "Data Sucessfully Fateched", Toast.LENGTH_SHORT).show();

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            else
            {
                Toast.makeText(Admin_Home_Page.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            super.onPostExecute(aVoid);

        }

    }
}