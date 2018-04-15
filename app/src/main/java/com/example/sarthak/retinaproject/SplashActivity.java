package com.example.sarthak.retinaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation anim1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView logo11=(TextView)findViewById(R.id.logo1);
        ImageView logoimage=(ImageView)findViewById(R.id.logoimg);
        anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        logo11.startAnimation(anim1);


        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                Intent i=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },2500);

    }
}
