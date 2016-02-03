package com.tobin.fotofetcher.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tobin.fotofetcher.Activities.CameraActivity;
import com.tobin.fotofetcher.Activities.GalleryActivity;
import com.tobin.fotofetcher.LoginCred.TwitterCustomVolleyRequest;
import com.tobin.fotofetcher.R;

public class PhotoSelection extends AppCompatActivity {

    //TextView object
    private TextView textViewUsername;

    //Image Loader object
    private ImageLoader imageLoader;

    //NetworkImageView Ojbect
    private NetworkImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selection);
        loadTwitterCreds();
        uploadPhotoAndReturnHome();
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

    public void loadTwitterCreds() {

        //Initializing views
        profileImage = (NetworkImageView) findViewById(R.id.photo_select_twitter_profile_image);
        textViewUsername = (TextView) findViewById(R.id.photo_select_twitter_username_text_view);

        SharedPreferences sharedPreferences = getSharedPreferences("twitter_creds", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(LoginActivity.KEY_USERNAME, "user_name");
        String profileImageUrl = sharedPreferences.getString(LoginActivity.KEY_PROFILE_IMAGE_URL, "user_profile_image_url");

        //Loading image
        imageLoader = TwitterCustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(profileImageUrl, ImageLoader.getImageListener(profileImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        profileImage.setImageUrl(profileImageUrl, imageLoader);

        //Setting the username in textview
        textViewUsername.setText("Welcome, " + username);
    }

    public void uploadPhotoAndReturnHome() {
        Button uploadButton = (Button) findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goes to HomeActivity temporarily until we implement the upload functionality
                Intent galleryIntent = new Intent(getApplicationContext(), HomeActivity.class);
                galleryIntent.putExtra(LoginActivity.KEY_USERNAME, "user_name");
                galleryIntent.putExtra(LoginActivity.KEY_PROFILE_IMAGE_URL, "user_profile_image_url");
                startActivity(galleryIntent);
            }
        });
    }
}
