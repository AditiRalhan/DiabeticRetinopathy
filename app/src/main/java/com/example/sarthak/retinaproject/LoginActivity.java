package com.example.sarthak.retinaproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    TextView reg;
    FirebaseAuth mAuth;
    FirebaseUser user1;
    EditText email,password;
    String Email,Password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.loginEmail);
        password=(EditText)findViewById(R.id.loginPassword);
        reg=(TextView)findViewById(R.id.reg);
        reg.setPaintFlags(reg.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }

    public void SignIn(View view)
    {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (Email.isEmpty()) {
            email.setError("Email Required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a valid Email");
            email.requestFocus();
            return;
        }

        if (Password.isEmpty())
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }

        if (Password.length() < 6)
        {
            password.setError("Enter Atleast 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressBar.setVisibility(View.GONE);
              if(task.isSuccessful())
              {
                  user1 = mAuth.getCurrentUser();
                  if(user1!=null)
                  {
                      if (user1.isEmailVerified())
                      {
                          Intent i = new Intent(LoginActivity.this,DashboardActivity.class);
                          i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                          startActivity(i);
                          finish();
                      }
                      else
                      {
                          AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
                          ab.setMessage("Please Verify Your Email!");

                          ab.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(final DialogInterface dialog, int which) {
                                  user1.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          dialog.cancel();
                                          Toast.makeText(getApplicationContext(), "Verification Email Sent. Check Your Email.", Toast.LENGTH_SHORT).show();
                                      }
                                  });
                              }
                          });

                          AlertDialog ad = ab.create();
                          ad.setCanceledOnTouchOutside(false);
                          ad.show();
                      }

                  }
              }
              else
              {
                  Toast.makeText(getApplicationContext(),"Check your email or password",Toast.LENGTH_LONG).show();
              }
            }
        });
    }

    public void Register(View view)
    {
        Intent i1 =  new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i1);
    }
}
