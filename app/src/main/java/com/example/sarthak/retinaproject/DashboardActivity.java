package com.example.sarthak.retinaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity
{
    ViewFlipper v_flipper;
    FirebaseUser user;
    FirebaseAuth mAuth;
    Toolbar toolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.captureRetina:
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth=FirebaseAuth.getInstance();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        v_flipper = (ViewFlipper) findViewById(R.id.view_flipper);
        int images[] = {R.drawable.lens, R.drawable.eye, R.drawable.eyedoctorpatient};

        for (int image : images)
        {
            flipperImages(image);
        }


    }

    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void cardCheck(View view)
    {
        Intent i=new Intent(getApplicationContext(),TestActivity.class);
        startActivity(i);
    }

    public void Record(View view)
    {
        Intent i=new Intent(getApplicationContext(),PatientRecordActivity.class);
        startActivity(i);
    }
}
