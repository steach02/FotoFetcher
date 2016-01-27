package com.tobin.fotofetcher.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tobin.fotofetcher.R;

public class CameraActivity extends AppCompatActivity {

    Button btnTakePhoto;
    ImageView imgTakenPhoto;
    private static final int CAM_REQUEST = 1313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnTakePhoto = (Button) findViewById(R.id.Button1);
        imgTakenPhoto = (ImageView) findViewById(R.id.ImageView1);

        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST) {
            if(resultCode == RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                imgTakenPhoto.setImageBitmap(thumbnail);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }

    public void goToPhotoSelection(View view) {
        Intent photoSelectIntent = new Intent(getApplicationContext(),PhotoSelection.class);
        startActivity(photoSelectIntent);
    }

    class btnTakePhotoClicker implements View.OnClickListener {

        @Override
        public void onClick(View v){
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAM_REQUEST);
        }
    }
}
