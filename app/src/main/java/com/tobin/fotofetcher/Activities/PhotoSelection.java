package com.tobin.fotofetcher.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tobin.fotofetcher.Activities.CameraActivity;
import com.tobin.fotofetcher.Activities.GalleryActivity;
import com.tobin.fotofetcher.R;

public class PhotoSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selection);
    }

    public void goToTheCamera(View view) {
        Intent cameraIntent = new Intent(getApplicationContext(), CameraActivity.class);
        startActivity(cameraIntent);
    }

    /*
    Not Implemented yet
     */
    public void goToTheGallery(View view) {
        Intent galleryIntent = new Intent(getApplicationContext(), GalleryActivity.class);
        startActivity(galleryIntent);
    }

    public void uploadPhoto(View view) {
//        Intent galleryIntent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(galleryIntent);
    }
}
