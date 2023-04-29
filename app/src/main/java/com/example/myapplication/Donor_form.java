package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donor_form extends AppCompatActivity {
    Button btnsubmit,img_btn;
    EditText inputUsername,inputContact,inputfoodtype,inputfoodquantity,inputcity,inputother;
    DatabaseReference databaseUsers;
    ImageView donot_img;
    Uri imageUri;
    ActivityDonor_formBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);

        btnsubmit=findViewById(R.id.btnSubmit);
        inputUsername=findViewById(R.id.inputUsername);
        inputContact=findViewById(R.id.inputContact);
        inputfoodtype=findViewById(R.id.inputfoodtype);
        inputfoodquantity=findViewById(R.id.inputfoodquantity);
        inputcity=findViewById(R.id.inputcity);
        img_btn = findViewById(R.id.img_btn);
        databaseUsers= FirebaseDatabase.getInstance().getReference();

//        contactngo.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  startActivity(new Intent(Upload_Details.this,NGO.class));
//            }
//        });
        img_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        btnsubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //InsertData();
            }
        });
    }
    private void selectImage(){
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            databaseUsers.firebaseimage.setImageUri(imageUri);
        }
    }

//    public void InsertData() {
//        String name=inputUsername.getText().toString();
//        String contact=inputContact.getText().toString();
//        String food=inputfoodtype.getText().toString();
//        String quantity=inputfoodquantity.getText().toString();
//        String city=inputcity.getText().toString();
//        String other=inputother.getText().toString();
//        String id = databaseUsers.push().getKey();
//
//        details user= new details(name,contact,food,quantity,city,other);
//        databaseUsers.child("users").child(id).setValue(user)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(Upload_Details.this, "Food Details Inserted", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//
//
//
//    }
}