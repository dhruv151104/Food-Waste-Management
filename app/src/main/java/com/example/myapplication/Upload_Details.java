package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;

public class Upload_Details extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    Button btnsubmit,contactngo,img_btn;
    EditText inputUsername,inputContact,inputfoodtype,inputfoodquantity,inputcity,inputother;
    ImageView donate_img;
    private Uri mImageUri;
    StorageReference mStorageRef;
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
        img_btn=findViewById(R.id.img_btn);
        donate_img=findViewById(R.id.donate_img);

        mStorageRef= FirebaseStorage.getInstance().getReference("Upload");
        databaseUsers=FirebaseDatabase.getInstance().getReference("Upload");


//        inputother=findViewById(R.id.inputother);
//        contactngo=findViewById(R.id.contactngo);

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver ();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
//            String name=inputUsername.getText().toString();
//            String contact=inputContact.getText().toString();
//            String food=inputfoodtype.getText().toString();
//            String quantity=inputfoodquantity.getText().toString();
//            String city=inputcity.getText().toString();
//            String id = databaseUsers.push().getKey();

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef. child(System. currentTimeMillis ()
                    + "." + getFileExtension(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String username=inputUsername.getText().toString().trim();
                            String contact=inputContact.getText().toString().trim();
                            String foodtype=inputfoodtype.getText().toString().trim();
                            String foodquantity=inputfoodquantity.getText().toString().trim();
                            String foodcity=inputcity.getText().toString().trim();

                            Toast.makeText(Upload_Details.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                           // Upload_Details upload=new Upload_Details(mImageUri,username,contact,foodtype,foodquantity,foodcity,taskSnapshot.getUploadSessionUri().toString());
//                            username,contact,foodtype,foodquantity,foodcity,taskSnapshot.getUploadSessionUri().toString()

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0 * taskSnapshot.getBytesTransferred() /taskSnapshot.getTotalByteCount());
                        }
                    });
        } else {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
        }

    private void openFileChooser() {
        Intent intent = new Intent ();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        mImageUri=data.getData();
        Picasso.get().load(mImageUri).into(donate_img);
    }



}