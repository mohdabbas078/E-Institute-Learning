package com.example.online_examination_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.online_examination_system.Teacher_Adapter.SliderAdp;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Home extends AppCompatActivity {
    SliderView sliderView;
    int images[]={R.drawable.colorfulback,R.drawable.backg1,R.drawable.backg2};
    SliderAdp sliderAdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sliderView=(SliderView)findViewById(R.id.sl1);
        sliderAdp=new SliderAdp(images);
        sliderView.setSliderAdapter(sliderAdp);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }

    public void Login(View view)
    {
        startActivity(new Intent(Home.this,Login.class));
    }
}