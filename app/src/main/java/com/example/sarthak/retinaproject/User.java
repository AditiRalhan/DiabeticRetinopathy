
package com.example.sarthak.retinaproject;
/**
 * Created by ADITI on 5/20/2018.
 */

public class User
{
  public String name,email,password,age,gender,clinicName,clinicType,phoneNumber;

    public User()
    {

    }

    public User(String name, String email,String password, String age, String gender, String clinicName, String clinicType, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.age = age;
        this.gender = gender;
        this.clinicName = clinicName;
        this.clinicType = clinicType;
        this.phoneNumber = phoneNumber;
    }
}
