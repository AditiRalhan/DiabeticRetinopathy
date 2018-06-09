package com.example.sarthak.retinaproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PatientFormActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    FirebaseUser user1;
    EditText pName,pAge,pPhone,pAddress;
    ProgressBar progressBar;
    Spinner gender,bloodtype;
    String name,age,address,phone,gen,bloodgroup,date,id;
    DatabaseReference patientProfile;
    TextView d;
    static int year,month,day;
    Calendar calendar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        pName=(EditText)findViewById(R.id.PatientName);
        pAge=(EditText)findViewById(R.id.PatientAge);
        pAddress=(EditText)findViewById(R.id.PatientAddress);
        pPhone=(EditText)findViewById(R.id.PatientPhoneNumber);

        gender=(Spinner)findViewById(R.id.PatientGender);
        bloodtype=(Spinner)findViewById(R.id.PatientBlood);
        progressBar=(ProgressBar)findViewById(R.id.Patientprogressbar);
        progressBar.setVisibility(View.GONE);

        d = (TextView) findViewById(R.id.tvdate);
        calendar= Calendar.getInstance();
        year= calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year,month,day);

        database = FirebaseDatabase.getInstance();
    }

    public void done(View view)
    {
        name = pName.getText().toString().trim();
        age = pAge.getText().toString().trim();
        phone = pPhone.getText().toString().trim();
        address=pAddress.getText().toString().trim();

        gen = gender.getSelectedItem().toString();
        bloodgroup = bloodtype.getSelectedItem().toString();
        date=d.getText().toString();

        if (name.isEmpty()) {
            pName.setError("Name Required");
            pName.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            pAge.setError("Name Required");
            pAge.requestFocus();
            return;
        }



        if (phone.isEmpty()) {
            pPhone.setError("Name Required");
            pPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            pPhone.setError("Enter a valid number");
            pPhone.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            pAddress.setError("Name Required");
            pAddress.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        patientProfile = database.getReference("PatientProfile");

        if(name!=null && age!=null && phone!=null && address!=null)
        {
            id= patientProfile.push().getKey();
            Patients patient = new Patients(id,name,age,gen,bloodgroup,phone,address,date);
            patientProfile.child(id).setValue(patient);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Data Successfully Saved!",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(i);
        }



    }

    public void setdate(View view)
    {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }



    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth)
        {
            showDate(year1,month1,dayOfMonth);
        }
    };

    public void showDate(int Year,int Month,int Day)
    {
        d.setText(new StringBuilder().append(Day).append("/").append(Month+1).append("/").append(Year));
    }
}
