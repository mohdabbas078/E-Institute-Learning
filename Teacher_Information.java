package com.example.online_examination_system.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.example.online_examination_system.Admin_Adapter.Student_Information_Adapter;
import com.example.online_examination_system.Admin_Adapter.Teacher_Information_Adapter;
import com.example.online_examination_system.Admin_Bean.Teacher_Information_Personal_Bean;
import com.example.online_examination_system.Admin_Operation.Teacher_Information_Operation;
import com.example.online_examination_system.R;

import java.util.ArrayList;

public class Teacher_Information extends AppCompatActivity {

    Teacher_Information_Personal_Bean teacher_information_personal_bean;
    Teacher_Information_Adapter teacher_information_adapter;
    Teacher_Information_Operation teacher_information_operation;

    ListView listView;
    TextView totalTea;
    CircularDotsLoader circularDotsLoader;
    EditText editText;
    TextView title;
    ArrayList<String> fullName;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__information);

        teacher_information_operation=new Teacher_Information_Operation();

        listView=(ListView)findViewById(R.id.listview);
        totalTea=(TextView)findViewById(R.id.totalTea);
        circularDotsLoader=(CircularDotsLoader)findViewById(R.id.tqvcdl1);
        editText=(EditText)findViewById(R.id.tsearchView);
        imageView=(ImageView)findViewById(R.id.tsearchby);
        title=(TextView)findViewById(R.id.tititle1) ;
        title.setSelected(true);
        fullName=new ArrayList<>();
        Toast.makeText(this, "!Please Wait Data is fetching in background", Toast.LENGTH_SHORT).show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        new TeacherInfo().execute();
    }

    class TeacherInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try

            {

                teacher_information_personal_bean=teacher_information_operation.getPersonalData();
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
            if(teacher_information_personal_bean!=null)
            {
                for (int i=0; i<teacher_information_personal_bean.getTeacherId().size();i++)
                {
                    fullName.add(teacher_information_personal_bean.getFirstName().get(i)+" "+teacher_information_personal_bean.getMiddleName().get(i)+
                            " "+teacher_information_personal_bean.getLastName().get(i));
                }

                teacher_information_adapter=new Teacher_Information_Adapter(Teacher_Information.this,teacher_information_personal_bean.getTeacherId(),fullName,teacher_information_personal_bean);
                listView.setAdapter(teacher_information_adapter);
                totalTea.setText("(Total Teachers :-"+teacher_information_personal_bean.getTotalTeacher()+")");
                circularDotsLoader.setVisibility(View.INVISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            else
            {
                Toast.makeText(Teacher_Information.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                circularDotsLoader.setVisibility(View.INVISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            super.onPostExecute(aVoid);

        }

    }
}