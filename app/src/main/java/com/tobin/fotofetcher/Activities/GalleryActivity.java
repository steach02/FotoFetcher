package com.tobin.fotofetcher.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tobin.fotofetcher.R;

public class GalleryActivity extends AppCompatActivity {


    public static final int IMAGE_GALLERY_REQUEST = 2;
    ImageView imgFromGallery;//Attempt to grab image from the gallery

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imgFromGallery = (ImageView) findViewById(R.id.ImageView1);
    }

    public void goBackHome(View view) {
        Intent photoSelectIntent = new Intent(getApplicationContext(),PhotoSelection.class);
        startActivity(photoSelectIntent);

    }

    public void goToTheGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,IMAGE_GALLERY_REQUEST);

    }

    /* Attempt to save the image from the Gallery */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case IMAGE_GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap myImage = BitmapFactory.decodeFile(filePath);
                    Drawable draw = new BitmapDrawable(myImage);

                    imgFromGallery.setBackground(draw);
                }
        }
    }
}
