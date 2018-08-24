package com.example.sarthak.retinaproject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ADITI on 6/9/2018.
 */

public class PatientList extends ArrayAdapter<Patients>
{
   private Activity context;
   private List<Patients> patientlist;

    public PatientList(@NonNull Activity context, List<Patients> patientlist)
    {
        super(context,R.layout.list_patients_layout,patientlist);
        this.context=context;
        this.patientlist=patientlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater= context.getLayoutInflater();
              View listviewitem =  inflater.inflate(R.layout.list_patients_layout,null,true);

        TextView Tname=(TextView)listviewitem.findViewById(R.id.listName);
        TextView Tage=(TextView)listviewitem.findViewById(R.id.listAge);
        TextView Tgender=(TextView)listviewitem.findViewById(R.id.listGender);
        TextView Tbloodtype=(TextView)listviewitem.findViewById(R.id.listBloodType);
        TextView Tphone=(TextView)listviewitem.findViewById(R.id.listPhone);
       TextView Tdate=(TextView)listviewitem.findViewById(R.id.listDate);

        Patients patients=patientlist.get(position);
        Tname.setText(patients.getPatientName());
        Tage.setText(patients.getPatientAge());
        Tgender.setText(patients.getPatientGender());
        Tbloodtype.setText(patients.getPatientBloodGroup());
        Tphone.setText(patients.getPatientPhone());
        Tdate.setText(patients.getDate());

        return listviewitem;
    }
}
