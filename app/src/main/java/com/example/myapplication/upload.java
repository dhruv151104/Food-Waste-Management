package com.example.myapplication;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class upload {
    private String mName;
    private String mImageUrl;

    private String minputUsername;
    private String minputContact;
    private String minputfoodtype;
    private String minputfoodquantity;
    private String minputcity;

    public  upload(Uri mImageUri, String username, String contact, String foodtype, String foodquantity, String foodcity){

    }
    public upload(String name,String imageUrl,String inputUsername,String inputContact, String inputfoodtype, String inputfoodquantity, String inputcity){
//        this.inputUsername = inputUsername;
//        this.inputContact = inputContact;
//        this.inputfoodtype = inputfoodtype;
//        this.inputfoodquantity = inputfoodquantity;
//        this.inputcity = inputcity;
        if (name.trim().equals("")) {
            name =
                    "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
        minputUsername=inputUsername;
        minputcity=inputcity;
        minputContact=inputContact;
        minputfoodtype=inputfoodtype;
        minputfoodquantity=inputfoodquantity;
    }

    public upload(String trim, Task<Uri> downloadUrl) {

    }

//    public String getInputUsername() {
//        return inputUsername;
//    }
//
//    public String getInputContact() {
//        return inputContact;
//    }
//
//    public String getInputfoodtype() {
//        return inputfoodtype;
//    }
//
//    public String getInputfoodquantity() {
//        return inputfoodquantity;
//    }
//
//    public String getInputcity() {
//        return inputcity;
//    }

    public String getName(){
        return mName;
    }

    public void setName (String name) {
        mName = name;
    }
    public String getImageUrl(){
            return mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;

    }
    public String getinputUsername(){
        return minputUsername;
    }

    public void setinputUsername (String inputUsername) {
        minputUsername = inputUsername;
    }

    public String getinputContact(){
        return minputContact;
    }

    public void setInputContact (String inputContact) {
        minputContact = inputContact;
    }


//    public  void setMinputUsername(String inputUsername){
//        minputUsername=inputUsername;
//    }
//
//    public  void setM
}




