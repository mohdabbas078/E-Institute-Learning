package com.example.online_examination_system.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.R;
import com.example.online_examination_system.SessionManage;
import com.example.online_examination_system.Teacher_Adapter.Batch_View_Adapter;
import com.example.online_examination_system.Teacher_Adapter.Current_Quiz_View_Adapter;
import com.example.online_examination_system.Teacher_Bean.Current_Quiz_View_Bean;
import com.example.online_examination_system.Teacher_Operation.Current_Quiz_View_Operation;

public class Current_Quiz_View extends AppCompatActivity {

    ListView listView;
    TextView totalQuiz;
    ImageView insertQuiz;
    CircularDotsLoader circularDotsLoader;

    Current_Quiz_View_Operation current_quiz_view_operation;
    SessionManage sessionManage;
    Current_Quiz_View_Bean current_quiz_view_bean;
    Current_Quiz_View_Adapter current_quiz_view_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current__quiz__view);
        sessionManage=new SessionManage(Current_Quiz_View.this);
        current_quiz_view_operation=new Current_Quiz_View_Operation();

        listView=(ListView)findViewById(R.id.tcvlt1);
        totalQuiz=(TextView)findViewById(R.id.ttqv1);
        insertQuiz=(ImageView)findViewById(R.id.tqva1);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.tqvcdl1);

        new Async().execute();
    }

    class Async extends AsyncTask<Void, Void, Void>
    {

        @Override

        protected Void doInBackground(Void... voids) {

            try
            {
               current_quiz_view_bean=current_quiz_view_operation.getQuiz();
            }
            catch (Exception e)
            {

                Log.d("myapp", String.valueOf(e));

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(current_quiz_view_bean!=null)
            {
                current_quiz_view_adapter=new Current_Quiz_View_Adapter(Current_Quiz_View.this,sessionManage,current_quiz_view_bean);
                totalQuiz.setText("Total Quiz :- "+current_quiz_view_bean.getTotalQuiz());
                listView.setAdapter(current_quiz_view_adapter);
                circularDotsLoader.setVisibility(View.INVISIBLE);
            }
            else
            {
                Toast.makeText(Current_Quiz_View.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);

        }

    }

    }