package com.example.sarthak.retinaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user1;
    Spinner etgen, etclinicType;
    EditText etname, etemail, etpassword, etage, etclinicName, etPhoneNumber;
    String name, email, password, age, clinicName, PhoneNumber , gender, clinicType ;
     ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        etgen = (Spinner) findViewById(R.id.regGender);
        etclinicType = (Spinner) findViewById(R.id.regClinicType);

        etname = (EditText) findViewById(R.id.regName);
        etemail = (EditText) findViewById(R.id.regEmail);
        etpassword = (EditText) findViewById(R.id.regPassword);
        etage = (EditText) findViewById(R.id.regAge);
        etclinicName = (EditText) findViewById(R.id.regClinicName);
        etPhoneNumber = (EditText) findViewById(R.id.regPhoneNumber);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }


    /*@Override
    protected void onStart()
    {
        super.onStart();

        if(mAuth.getCurrentUser()!= null)
        {
            Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_LONG).show();
        }
    }*/

    public void Registered(View view) {
        name = etname.getText().toString().trim();
        email = etemail.getText().toString().trim();
        password = etpassword.getText().toString().trim();
        age = etage.getText().toString().trim();
        clinicName = etclinicName.getText().toString().trim();
        PhoneNumber = etPhoneNumber.getText().toString().trim();

        gender = etgen.getSelectedItem().toString();
        clinicType = etclinicType.getSelectedItem().toString();

        if (name.isEmpty()) {
            etname.setError("Name Required");
            etname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etemail.setError("Email Required");
            etemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("Enter a valid Email");
            etemail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            etpassword.setError("Password Required");
            etpassword.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            etpassword.setError("Enter Atleast 6 characters");
            etpassword.requestFocus();
            return;
        }

        if (age.isEmpty())
        {
            etage.setError("Age Required");
            etage.requestFocus();
            return;
        }

        if (clinicName.isEmpty())
        {
            etclinicName.setError("Clinic Name Required");
            etclinicName.requestFocus();
            return;
        }

        if (PhoneNumber.isEmpty())
        {
            etPhoneNumber.setError("Phone Number Required");
            etPhoneNumber.requestFocus();
            return;
        }
        if (PhoneNumber.length() != 10) {
            etPhoneNumber.setError("Enter a valid number");
            etPhoneNumber.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    User user = new User(name,email,password,age,gender,clinicName,clinicType,PhoneNumber  );

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful())
                            {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                user1 = mAuth.getCurrentUser();
                                if(user1!=null)
                                {
                                    user1.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            Toast.makeText(getApplicationContext(), "Please Verify your Email ", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                Intent i =new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(i);
                                finish();

                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });









    }

}