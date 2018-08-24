package com.example.sarthak.retinaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientRecordActivity extends AppCompatActivity
{
    ListView pListview;
    DatabaseReference patientProfile;
    FirebaseDatabase database;
    List<Patients> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_record);
        pListview=(ListView)findViewById(R.id.Patientlistview);

        database = FirebaseDatabase.getInstance();
        patientProfile = database.getReference("PatientProfile");
        patientList=new ArrayList<>();
    }

    @Override
    protected void onStart()
    {
        patientProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
              patientList.clear();
               for(DataSnapshot patientSnapshot : dataSnapshot.getChildren() )
               {
                   Patients patients=patientSnapshot.getValue(Patients.class);
                   patientList.add(patients);
               }
               PatientList adapter= new PatientList(PatientRecordActivity.this,patientList);
               pListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
