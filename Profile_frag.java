package com.example.online_examination_system.Student;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.util.Log;
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

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Student_Adapter.Student_Unit_View_Adapter;
import com.example.online_examination_system.Student_Bean.Student_Profile_Bean;
import com.example.online_examination_system.Student_Operation.Student_Profile_Operation;

import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile_frag extends Fragment
{
    Context context;
    LayoutInflater layoutInflater;
    SessionManage sessionManage;
    Animation animation;
    AlertDialog dialog;

   // CircularDotsLoader circularDotsLoader;
    Student_Profile_Bean student_profile_bean;
    Student_Profile_Operation student_profile_operation;
    public Profile_frag(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        sessionManage=new SessionManage(context);
        student_profile_operation=new Student_Profile_Operation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view=layoutInflater.inflate(R.layout.fragment_profile_frag, container, false);
       TextView ProfileName=(TextView)view.findViewById(R.id.studentprofilename);
       TextView courseName=(TextView)view.findViewById(R.id.studentcoursename);
       CircleImageView circleImageView=(CircleImageView)view.findViewById(R.id.mohdabbasprofilepic);
       ProfileName.setText(sessionManage.getStudentname());
       courseName.setText("Course Name :- "+sessionManage.getCoursename());
       Button profilepop=(Button)view.findViewById(R.id.sbtn1);
       Button shareButton=(Button)view.findViewById(R.id.sbtn2);
       Button feedbackbutton=(Button)view.findViewById(R.id.sbtn11);
       Button aboutUs=(Button)view.findViewById(R.id.sbtn3);
       Button linkedIn=(Button)view.findViewById(R.id.sbtn4);
       Button facebook=(Button)view.findViewById(R.id.sbtn5);
       Button twitter=(Button)view.findViewById(R.id.sbtn6);
       Button youtube=(Button)view.findViewById(R.id.sbtn7);
       Button googleplus=(Button)view.findViewById(R.id.sbtn8);
       Button instagram=(Button)view.findViewById(R.id.sbtn9);
       Button setting=(Button)view.findViewById(R.id.sbtn10);
       animation= AnimationUtils.loadAnimation(context,R.anim.rotate2);
        //circularDotsLoader=(CircularDotsLoader)view.findViewById(R.id.circleloader1);
        if(sessionManage.getI().equals("0"))
        {
            viewProfileData();
        }
       circleImageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {
             AlertDialog.Builder builder = new AlertDialog.Builder(context);
             View customLayout= layoutInflater.inflate(R.layout.studentprofiepicpopup, null);
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

            popup.getMenuInflater().inflate(R.menu.studentprofilepopupmenu, popup.getMenu());
            popup.setGravity(Gravity.RIGHT);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
               public boolean onMenuItemClick(MenuItem item)
               {
                  switch (item.getItemId())
                  {
                     case R.id.sviewprofile:
                         sessionManage.setI("1");
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("About Information").setIcon(R.drawable.eye);
                        View customLayout= layoutInflater.inflate(R.layout.studentviewprofilecustompopup, null);
                        TextView name=(TextView)customLayout.findViewById(R.id.sname1);
                        TextView UserName=(TextView)customLayout.findViewById(R.id.sid1);
                         TextView dob=(TextView)customLayout.findViewById(R.id.sdob1);
                         TextView gender=(TextView)customLayout.findViewById(R.id.sgender1);
                         TextView mobileno=(TextView)customLayout.findViewById(R.id.smobile1);
                         TextView email=(TextView)customLayout.findViewById(R.id.semail1);
                         TextView cast=(TextView)customLayout.findViewById(R.id.scast1);
                         TextView aadhar=(TextView)customLayout.findViewById(R.id.saadhaar1);
                         TextView session=(TextView)customLayout.findViewById(R.id.scurrentsession1);
                         TextView fname=(TextView)customLayout.findViewById(R.id.sfathername1);
                         TextView foccup=(TextView)customLayout.findViewById(R.id.sfatheroccu1);
                         TextView fmobile=(TextView)customLayout.findViewById(R.id.sfathermobile1);
                         TextView mname=(TextView)customLayout.findViewById(R.id.smothername1);
                         TextView moccup=(TextView)customLayout.findViewById(R.id.smotheroccu1);

                         TextView xbname=(TextView)customLayout.findViewById(R.id.stenthboard1);
                         TextView xbper=(TextView)customLayout.findViewById(R.id.stenthper1);
                         TextView xbpassing=(TextView)customLayout.findViewById(R.id.stenthyop1);
                         TextView xibname=(TextView)customLayout.findViewById(R.id.stboardname1);
                         TextView xibper=(TextView)customLayout.findViewById(R.id.stper1);
                         TextView xibpassing=(TextView)customLayout.findViewById(R.id.styop1);

                         TextView phousen=(TextView)customLayout.findViewById(R.id.sphouse1);
                         TextView pstreetn=(TextView)customLayout.findViewById(R.id.spstreet1);
                         TextView pcountry=(TextView)customLayout.findViewById(R.id.spcountry1);
                         TextView pcity=(TextView)customLayout.findViewById(R.id.spcity1);
                         TextView pdistrict=(TextView)customLayout.findViewById(R.id.spdistrict1);
                         TextView plocality=(TextView)customLayout.findViewById(R.id.splocality1);
                         TextView pcode=(TextView)customLayout.findViewById(R.id.sppincode1);


                         TextView lhousen=(TextView)customLayout.findViewById(R.id.slhouse1);
                         TextView lstreetn=(TextView)customLayout.findViewById(R.id.slstreet1);
                         TextView lcountry=(TextView)customLayout.findViewById(R.id.slcountry1);
                         TextView lcity=(TextView)customLayout.findViewById(R.id.slcity1);
                         TextView ldistrict=(TextView)customLayout.findViewById(R.id.sldistrict1);
                         TextView llocality=(TextView)customLayout.findViewById(R.id.sllocality1);
                         TextView lcode=(TextView)customLayout.findViewById(R.id.slpincode1);


                        name.setText("Name :- "+student_profile_bean.getFname()+""+student_profile_bean.getMname()+""+student_profile_bean.getLname());
                           UserName.setText("User Name :- "+student_profile_bean.getSid());
                           dob.setText("Date Of Birth :- "+student_profile_bean.getDob());
                           gender.setText("Gender :- "+student_profile_bean.getGender());
                           mobileno.setText("Mobile No. :- "+student_profile_bean.getPhoneNo());
                           email.setText("E-Mail :- "+student_profile_bean.getEmail());
                           cast.setText("Cast :- "+student_profile_bean.getCast());
                           aadhar.setText("Aadhar No. :- "+student_profile_bean.getAadhaarno());
                           session.setText("Current Session :- "+student_profile_bean.getSession1());
                           fname.setText("Father's Name :- "+ student_profile_bean.getFathername());
                           foccup.setText("Father's Occupation :- "+student_profile_bean.getFoccopation());
                           fmobile.setText("Father's Mobile :- "+student_profile_bean.getFathernumber());
                           mname.setText("Mother's Name :- "+ student_profile_bean.getMotherName());
                           moccup.setText("Mother's Occupation :- "+student_profile_bean.getMoccopation());

                         xbname.setText("Board Name :- "+student_profile_bean.getBoardx());
                         xbper.setText("Percentage :- "+student_profile_bean.getPercentagex());
                         xbpassing.setText("Year Of Passing :- "+ student_profile_bean.getYearofpassingx());

                         xibname.setText("Board Name :- "+student_profile_bean.getBoardxi());
                         xibper.setText("Percentage :- "+student_profile_bean.getPercentagexi());
                         xibpassing.setText("Year Of Passing :- "+ student_profile_bean.getYearofpassingxi());

                         phousen.setText("House No. :- "+student_profile_bean.getPhouseno());
                         pstreetn.setText("Street No. :- "+student_profile_bean.getParea());
                         pcountry.setText("Country :- "+student_profile_bean.getPcountry());
                         pcity.setText("City :- "+student_profile_bean.getPcity());
                         pdistrict.setText("District :- "+student_profile_bean.getPdistrict());
                         plocality.setText("Locality :- "+student_profile_bean.getPlocality());
                         pcode.setText("Pin-Code :- "+student_profile_bean.getPcode());


                         lhousen.setText("House No. :- "+student_profile_bean.getChouseno());
                         lstreetn.setText("Street No. :- "+student_profile_bean.getCarea());
                         lcountry.setText("Country :- "+student_profile_bean.getCcountry());
                         lcity.setText("City :- "+student_profile_bean.getCcity());
                         ldistrict.setText("District :- "+student_profile_bean.getCdistrict());
                         llocality.setText("Locality :- "+student_profile_bean.getClocality());
                         lcode.setText("Pin-Code :- "+student_profile_bean.getCcode());


                        builder.setView(customLayout);
                        builder.setCancelable(false)
                                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which)
                                   {
                                      Toast.makeText(context, "Profile Details Closed", Toast.LENGTH_SHORT).show();
                                       sessionManage.setI("0");
                                      dialog.dismiss();
                                   }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;

                     case R.id.seditprofile:
                         sessionManage.setI("1");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setTitle("Update Your Profile").setIcon(R.drawable.writing);
                        View customLayout1= layoutInflater.inflate(R.layout.studentupdateprofilecustompopup, null);
                        builder1.setView(customLayout1);
                        builder1.setCancelable(false)
                                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which)
                                   {
                                      Toast.makeText(context, "Working in Progress", Toast.LENGTH_SHORT).show();
                                       sessionManage.setI("0");
                                      dialog.dismiss();
                                   }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               sessionManage.setI("0");
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
          @Override
          public void onClick(View v) {
             Intent intent=new Intent(Intent.ACTION_SEND);
             intent.setType("text/plain");
             String sharebody="https://play.google.com/store/apps/details?id=com.jgianveshana";
             String sharesubject="E-Institute Learning";
             intent.putExtra(Intent.EXTRA_TEXT,sharebody);
             intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
             context.startActivity(Intent.createChooser(intent,"Share By"));
          }
       });

       feedbackbutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {
            sessionManage.setI("1");
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
                            sessionManage.setI("0");
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
                    sessionManage.setI("0");
                    dialog.dismiss();
                }
             });
             AlertDialog dialog = builder.create();
             dialog.show();

          }
       });


       aboutUs.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
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
          }
       });

       linkedIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/mohd-abbas-75092a18b/"));
             context.startActivity(intent);
          }
       });
       facebook.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/profile.php?id=100009526823549"));
             context.startActivity(intent);
          }
       });
       twitter.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Toast.makeText(context, "Account Not Available", Toast.LENGTH_SHORT).show();
          }
       });
       youtube.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCOMBvcWge_DX0WRM2nDYh8A"));
             context.startActivity(intent);
          }
       });
       googleplus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Toast.makeText(context, "Account Not Available", Toast.LENGTH_SHORT).show();
          }
       });
       instagram.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/mohdabbas078/"));
             context.startActivity(intent);
          }
       });
       setting.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Toast.makeText(context, "Work in Progress..", Toast.LENGTH_SHORT).show();
          }
       });
        return view;
    }
  public void viewProfileData()
  {

      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      View customLayout= layoutInflater.inflate(R.layout.studentprofiledialougecustompopup, null);
      builder.setView(customLayout);
      builder.setCancelable(false);
      dialog = builder.create();
      dialog.show();
      new Async().execute();
  }
   class Async extends AsyncTask<Void, Void, Void>
   {
      @Override
      protected Void doInBackground(Void... voids) {
         try
         {
             student_profile_bean=student_profile_operation.getData(sessionManage.getUsername());

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
         if(student_profile_bean==null)
         {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            //circularDotsLoader.setVisibility(View.INVISIBLE);
             dialog.dismiss();
         }
         else
         {
            Toast.makeText(context, "Data Successfully fetched", Toast.LENGTH_SHORT).show();
            //circularDotsLoader.setVisibility(View.INVISIBLE);
             dialog.dismiss();
         }
         super.onPostExecute(aVoid);
      }

   }
}