package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Upload_Details extends AppCompatActivity {
    private  static final String CHANNEL_ID="MY NOTIFICATION";
    Button btnsubmit;
    EditText inputUsername,inputContact,inputfoodtype,inputfoodquantity,inputcity,inputother;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_details);

        btnsubmit=findViewById(R.id.btnSubmit);
        inputUsername=findViewById(R.id.inputUsername);
        inputContact=findViewById(R.id.inputContact);
        inputfoodtype=findViewById(R.id.inputfoodtype);
        inputfoodquantity=findViewById(R.id.inputfoodquantity);
        inputcity=findViewById(R.id.inputcity);
        databaseUsers= FirebaseDatabase.getInstance().getReference();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel CHANNEL = new NotificationChannel("MY NOTIFICATION","MY NOTIFICATION", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(CHANNEL);
        }



        btnsubmit.setOnClickListener(new OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(inputUsername.getText().toString())){
                    inputUsername.setError("Username cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(inputContact.getText().toString())){
                    inputContact.setError("Contact Number cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(inputContact.getText().toString())){
                    inputContact.setError("Contact Number cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(inputfoodtype.getText().toString())){
                    inputfoodtype.setError("Food type cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(inputfoodquantity.getText().toString())){
                    inputfoodquantity.setError("Food Quantity cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(inputcity.getText().toString())){
                    inputcity.setError("City cannot be empty");
                    return;
                }

                InsertData();

                NotificationCompat.Builder builder= new NotificationCompat.Builder(Upload_Details.this,"MY NOTIFICATION");
                builder.setContentTitle("Food Save");
                builder.setContentText("New donor available");
                builder.setSmallIcon(R.drawable.notification);
                builder.setAutoCancel(true);
                builder.setChannelId(CHANNEL_ID);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Upload_Details.this);
                notificationManagerCompat.notify(1,builder.build());

            }
        });
    }

    public void InsertData() {
        String name=inputUsername.getText().toString();
        String contact=inputContact.getText().toString();
        String food=inputfoodtype.getText().toString();
        String quantity=inputfoodquantity.getText().toString();
        String city=inputcity.getText().toString();
        String id = databaseUsers.push().getKey();

        details user= new details(name,contact,food,quantity,city);
        databaseUsers.child("users").child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Upload_Details.this, "Food Details Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}