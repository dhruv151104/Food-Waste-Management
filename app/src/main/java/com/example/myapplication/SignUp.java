package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;


import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    public static final String TAG="TAG";
    private FirebaseAuth auth;
    EditText signup_email,signup_password,signup_phn,signup_name;
    Button signup_btn;
    TextView login_redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();
        signup_email=findViewById(R.id.signup_email);
        signup_password=findViewById(R.id.signup_password);
        signup_btn=findViewById(R.id.signup_btn);
        login_redirect=findViewById(R.id.login_redirect);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=signup_email.getText().toString().trim();
                String pass=signup_password.getText().toString().trim();

                if (email.isEmpty()){
                    signup_email.setError("Email Cannot Be Empty");
                }
                if (pass.isEmpty()){
                    signup_password.setError("Password Cannot Be Empty");
                }
                else {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            if(auth.getCurrentUser().isEmailVerified()){
                                                startActivity(new Intent(SignUp.this,Homepage.class));
                                            }
                                            Toast.makeText(SignUp.this, "SignUp Successfull.Verify your email address...", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Toast.makeText(SignUp.this, "SignUp Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,Login.class));
                            }
                        }
                    });
                }
            }
        });

        login_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(SignUp.this, Login.class));
        finish();
        finishAffinity();
    }
}