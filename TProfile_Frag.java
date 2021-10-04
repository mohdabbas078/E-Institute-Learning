package com.example.online_examination_system.Teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;

import de.hdodenhof.circleimageview.CircleImageView;

public class TProfile_Frag extends Fragment
{
  Context context;
  LayoutInflater layoutInflater;
  SessionManage sessionManage;
  Animation animation;
    public TProfile_Frag(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=layoutInflater.inflate(R.layout.fragment_t_profile_,container,false);
         sessionManage=new SessionManage(context);
        TextView ProfileName=(TextView)view.findViewById(R.id.teacherprofilename);
        TextView ProfileUserName=(TextView)view.findViewById(R.id.teacherprofileusername);
        CircleImageView circleImageView=(CircleImageView)view.findViewById(R.id.raghusir1);
         ProfileName.setText(sessionManage.getTeachername());
         ProfileUserName.setText("User Name :- "+sessionManage.getUsername());
         Button profilepop=(Button)view.findViewById(R.id.btn1);
        Button shareButton=(Button)view.findViewById(R.id.btn2);
        Button feedbackbutton=(Button)view.findViewById(R.id.btn11);
        Button aboutUs=(Button)view.findViewById(R.id.btn3);
        Button linkedIn=(Button)view.findViewById(R.id.btn4);
        Button facebook=(Button)view.findViewById(R.id.btn5);
        Button twitter=(Button)view.findViewById(R.id.btn6);
        Button youtube=(Button)view.findViewById(R.id.btn7);
        Button googleplus=(Button)view.findViewById(R.id.btn8);
        Button instagram=(Button)view.findViewById(R.id.btn9);
        Button setting=(Button)view.findViewById(R.id.btn10);
         animation= AnimationUtils.loadAnimation(context,R.anim.rotate2);
         circleImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 //builder.setTitle("Your Profile").setIcon(R.drawable.profile2);
                 View customLayout= layoutInflater.inflate(R.layout.teacherprofilepicpopup, null);
                 customLayout.setAnimation(animation);
                 builder.setView(customLayout);
                 builder.setCancelable(true);
                 AlertDialog dialog = builder.create();
                 dialog.show();
             }
         });

        profilepop.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.M)
             @Override
             public void onClick(View v) {

                 PopupMenu popup = new PopupMenu(context, profilepop);

                 popup.getMenuInflater().inflate(R.menu.teacherprofilepopupmenu, popup.getMenu());
                 popup.setGravity(Gravity.RIGHT);
                 //registering popup with OnMenuItemClickListener
                 popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     public boolean onMenuItemClick(MenuItem item)
                     {
                         switch (item.getItemId())
                         {
                             case R.id.tviewprofile:
                                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                 builder.setTitle("About Information").setIcon(R.drawable.eye);
                                 View customLayout= layoutInflater.inflate(R.layout.teacherviewprofilecustompopup, null);
                                 builder.setView(customLayout);
                                 builder.setCancelable(false)
                                 .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialog, int which)
                                     {
                                         Toast.makeText(context, "Profile Closed", Toast.LENGTH_SHORT).show();
                                         dialog.dismiss();
                                     }
                                 });
                                 AlertDialog dialog = builder.create();
                                 dialog.show();
                                 break;

                             case R.id.teditprofile:
                                 AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                 builder1.setTitle("Update Your Profile").setIcon(R.drawable.writing);
                                 View customLayout1= layoutInflater.inflate(R.layout.teacherupdateprofilecustompopup, null);
                                 builder1.setView(customLayout1);
                                 builder1.setCancelable(false)
                                         .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which)
                                             {
                                                 Toast.makeText(context, "Profile Closed", Toast.LENGTH_SHORT).show();
                                                 dialog.dismiss();
                                             }
                                         }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialog, int which) {

                                     }
                                 });
                                 AlertDialog dialog1 = builder1.create();
                                 dialog1.show();
                                 break;
                         }
                         return true;
                     }
                 });

                 popup.show(); //showing popup menu
             }
         });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, shareButton);

                popup.getMenuInflater().inflate(R.menu.teachersharepopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.shareview :
                                Intent intent=new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                String sharebody="https://play.google.com/store/apps/details?id=com.jgianveshana";
                                String sharesubject="E-Institute Learning";
                                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
                                context.startActivity(Intent.createChooser(intent,"Share By"));
                                break;
                            case R.id.shareedit :
                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, aboutUs);

                popup.getMenuInflater().inflate(R.menu.teacheraboutuspopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.aboutusview :
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Contact Us").setIcon(R.drawable.faq);
                                View customLayout= layoutInflater.inflate(R.layout.studentaboutuscustompopup, null);
                                TextView link1 = (TextView)customLayout.findViewById(R.id.link1);
                                TextView link2 = (TextView)customLayout.findViewById(R.id.link2);
                                link1.setMovementMethod(LinkMovementMethod.getInstance());
                                link2.setMovementMethod(LinkMovementMethod.getInstance());
                                builder.setView(customLayout);
                                builder.setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {

                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                break;
                            case R.id.aboutusedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        feedbackbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, feedbackbutton);

                popup.getMenuInflater().inflate(R.menu.teacherfeedbackpopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.feedbackview :
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Give Us Feedback").setIcon(R.drawable.rate);
                                View customLayout= layoutInflater.inflate(R.layout.studentfeedbackcustompopup, null);
                                builder.setView(customLayout);
                                EditText name=(EditText)customLayout.findViewById(R.id.feedbackname1);
                                EditText query=(EditText)customLayout.findViewById(R.id.feedbackquery1);
                                builder.setCancelable(false)
                                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                if (name.getText().toString().equals("") || query.getText().toString().equals(""))
                                                {
                                                    Toast.makeText(context, "Fill All Fields", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {
                                                    Intent intent=new Intent(Intent.ACTION_SENDTO);
                                                    intent.setType("text/plain");
                                                    intent.setData(Uri.parse("mailto:"));
                                                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mohdabbas078@gmail.com"});
                                                    intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback From Application");
                                                    intent.putExtra(Intent.EXTRA_TEXT,"Name: "+name.getText().toString()+"\n Message: "+query.getText().toString());
                                                    try
                                                    {
                                                        context.startActivity(Intent.createChooser(intent,"Email via..."));
                                                    }
                                                    catch (android.content.ActivityNotFoundException ex)
                                                    {
                                                        Toast.makeText(context, "There Are No E-mail Client", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();

                                break;
                            case R.id.feedbackedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, linkedIn);

                popup.getMenuInflater().inflate(R.menu.teacherlinkedinpopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.linkedinview :
                                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/mohd-abbas-75092a18b/"));
                                context.startActivity(intent);
                                break;
                            case R.id.linkedinedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, facebook);

                popup.getMenuInflater().inflate(R.menu.teacherfacebookpopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.facebookview :
                                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/profile.php?id=100009526823549"));
                                context.startActivity(intent);
                                break;
                            case R.id.facebookedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, twitter);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.twitterview :
                                Toast.makeText(context, "Account Not Available", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.twitteredit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.getMenuInflater().inflate(R.menu.teachertwitterpopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener

                popup.show(); //showing popup menu
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, youtube);

                popup.getMenuInflater().inflate(R.menu.teacheryoutubepopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.youtubeview :
                                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCOMBvcWge_DX0WRM2nDYh8A"));
                                context.startActivity(intent);
                                break;
                            case R.id.youtubeedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        googleplus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, googleplus);

                popup.getMenuInflater().inflate(R.menu.teachergooglepluspopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.googleplusview :
                                Toast.makeText(context, "Account Not Available", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.googleplusedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, instagram);

                popup.getMenuInflater().inflate(R.menu.teacherinstagrampopup, popup.getMenu());
                popup.setGravity(Gravity.RIGHT);
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        switch (item.getItemId())
                        {
                            case R.id.instagramview :
                                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/mohdabbas078/"));
                                context.startActivity(intent);
                                break;
                            case R.id.instagramedit :

                                Toast.makeText(context, "Work In Progress", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}