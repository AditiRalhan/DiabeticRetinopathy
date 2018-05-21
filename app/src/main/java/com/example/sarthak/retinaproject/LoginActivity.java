package com.example.sarthak.retinaproject;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity
{
    TextView reg;
    FirebaseAuth mAuth;
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
        email.setPaintFlags(email.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        password=(EditText)findViewById(R.id.loginPassword);
        password.setPaintFlags(password.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
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
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
              if(task.isSuccessful())
              {
                  Intent i = new Intent(LoginActivity.this,MainActivity.class);
                  i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(i);
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
