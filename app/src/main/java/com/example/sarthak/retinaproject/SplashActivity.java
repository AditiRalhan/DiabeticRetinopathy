package com.example.sarthak.retinaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;

public class SplashActivity extends AppCompatActivity {

    Animation anim1;
    private static final String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if(!OpenCVLoader.initDebug()){
            //Log.d(TAG, "OpenCV not loaded");
            Toast.makeText(SplashActivity.this,"OpenCV not loaded",Toast.LENGTH_LONG).show();
        } else {
            //Log.d(TAG, "OpenCV loaded");
            Toast.makeText(SplashActivity.this,"OpenCV loaded !!! ",Toast.LENGTH_LONG).show();
        }

        TextView logo11=(TextView)findViewById(R.id.logo1);
        ImageView logoimage=(ImageView)findViewById(R.id.logoimg);
        anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        logo11.startAnimation(anim1);



        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                Intent i=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },2500);

    }
}
