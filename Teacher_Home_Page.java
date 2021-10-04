package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.online_examination_system.Home;
import com.example.online_examination_system.R;
import com.example.online_examination_system.Student.Home_frag;
import com.example.online_examination_system.Student.Notification_Frag;
import com.example.online_examination_system.Student.Profile_frag;
import com.example.online_examination_system.Student.Student_Home_Page;

public class Teacher_Home_Page extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__home__page);
        meowBottomNavigation=(MeowBottomNavigation)findViewById(R.id.btm2);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_person_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_home_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_notifications_active_24));


        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment=null;

                switch (item.getId())
                {
                    case 1:
                        fragment=new TProfile_Frag(Teacher_Home_Page.this);
                        break;
                    case 2:
                        fragment=new THome_Frag(Teacher_Home_Page.this);
                        break;
                    case 3:
                        fragment=new TNotification_Frag();
                        break;


                }

                loadFragment(fragment);


            }

        });

        meowBottomNavigation.setCount(3,"10");

        meowBottomNavigation.show(2,true);

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

               // Toast.makeText(Teacher_Home_Page.this, "You clicked"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

               // Toast.makeText(Teacher_Home_Page .this, "You Reselect"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.TframeLayout,fragment)
                .commit();

    }


}