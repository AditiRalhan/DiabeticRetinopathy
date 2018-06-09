package com.example.sarthak.retinaproject;

/**
 * Created by ADITI on 6/9/2018.
 */

public class Patients
{
    String patientId;
    String patientName;
    String patientAge;
    String patientGender;
    String patientBloodGroup;
    String patientPhone;
    String patientAddress;
    String date;

    public Patients()
    {

    }

    public Patients(String patientId, String patientName, String patientAge, String patientGender, String patientBloodGroup, String patientPhone, String patientAddress, String date)
    {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientBloodGroup = patientBloodGroup;
        this.patientPhone = patientPhone;
        this.patientAddress = patientAddress;
        this.date = date;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public String getPatientAge()
    {
        return patientAge;
    }

    public String getPatientGender()
    {
        return patientGender;
    }

    public String getPatientBloodGroup()
    {
        return patientBloodGroup;
    }

    public String getPatientPhone()
    {
        return patientPhone;
    }

    public String getPatientAddress()
    {
        return patientAddress;
    }

    public String getDate()
    {
        return date;
    }
}

