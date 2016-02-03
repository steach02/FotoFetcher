package com.tobin.fotofetcher.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tobin.fotofetcher.Fragments.FullSizeImageFragment;
import com.tobin.fotofetcher.Fragments.ListViewFragment;

import com.tobin.fotofetcher.LoginCred.TwitterCustomVolleyRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import com.tobin.fotofetcher.R;

public class HomeActivity extends AppCompatActivity implements ListViewFragment.onItemClickedListener {
    FragmentManager fragManager = getSupportFragmentManager();
    FullSizeImageFragment fullFrag;
    ListViewFragment listFrag;

    //TextView object
    private TextView textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listFrag = new ListViewFragment();
        fullFrag = new FullSizeImageFragment();
        fragManager.beginTransaction().replace(R.id.list_fragment_container, listFrag, "listFrag").commit();
        fragManager.beginTransaction().replace(R.id.photo_fragment_container, fullFrag, "photoFrag").commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragManager.beginTransaction().show(listFrag).commit();
            fragManager.beginTransaction().hide(fullFrag).commit();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragManager.beginTransaction().show(listFrag).commit();
            fragManager.beginTransaction().show(fullFrag).commit();

        }

        if (textViewUsername != null)
            retrieveTwitterLogin();
    }


    public void retrieveTwitterLogin() {

        //Initializing views
        NetworkImageView profileImage = (NetworkImageView) findViewById(R.id.home_twitter_profile_image);
        textViewUsername = (TextView) findViewById(R.id.home_twitter_username_text_view);

        //Getting the intent
        Intent intent = getIntent();

        //Getting values from intent
        String username = intent.getStringExtra(LoginActivity.KEY_USERNAME);
        String profileImageUrl = intent.getStringExtra(LoginActivity.KEY_PROFILE_IMAGE_URL);

        //Loading image
        ImageLoader imageLoader = TwitterCustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(profileImageUrl, ImageLoader.getImageListener(profileImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        profileImage.setImageUrl(profileImageUrl, imageLoader);

        //Setting the username in textview
        textViewUsername.setText("Welcome, " + username);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.plusButton) {
            Intent uploadIntent = new Intent(getApplicationContext(), PhotoSelection.class);
            startActivity(uploadIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragManager.beginTransaction().show(listFrag).commit();
            fragManager.beginTransaction().hide(fullFrag).commit();
        }
    }


    @Override
    public void onItemClicked (String position, String name, String tags, String url){
        fullFrag.setImageAttributes(name, tags, url);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragManager.beginTransaction().hide(listFrag).commit();
            fragManager.beginTransaction().show(fullFrag).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragManager.beginTransaction().show(listFrag).commit();
            fragManager.beginTransaction().show(fullFrag).commit();
        }
    }

}
