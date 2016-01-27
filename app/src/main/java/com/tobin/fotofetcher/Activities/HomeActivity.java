package com.tobin.fotofetcher.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tobin.fotofetcher.Fragments.FullSizeImageFragment;
import com.tobin.fotofetcher.RecyclerViewStuff.DataObject;
import com.tobin.fotofetcher.RecyclerViewStuff.DividerItemDecoration;
import com.tobin.fotofetcher.RecyclerViewStuff.MyRecyclerViewAdapter;
import com.tobin.fotofetcher.R;
import com.tobin.fotofetcher.LoginCred.TwitterCustomVolleyRequest;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "HomeActivity";

    //TextView object
    private TextView textViewUsername;

    //Image Loader object
    private ImageLoader imageLoader;

    //NetworkImageView Ojbect
    private NetworkImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("imageInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
//        editor.commit();

        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        // Code to Add an item with default animation
//        ((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);


        // this will be where we pull an image from the DB by default

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(LOG_TAG, " in landscape mode ");
            editor.putString("image name", "imageName01.jpg");
            editor.putString("tags", "tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13");
            editor.commit();
        }


        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
      MyRecyclerViewAdapter.MyClickListener() {
          @Override
          public void onItemClick(int position, View v, TextView imageName, TextView tags) {
//                      Log.i(LOG_TAG, " Clicked on Item " + position);

//                      editor.putString("image name", imageName.toString());
//                      editor.putString("tags", tags.toString());
//                      editor.commit();
              Bundle bundle = new Bundle();
              bundle.putString("test text", "from HomeActivity");
              FullSizeImageFragment fragmentObj = new FullSizeImageFragment();
              fragmentObj.setArguments(bundle);
              if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                  Log.i(LOG_TAG, imageName.getText().toString());
                  Intent intent = new Intent(getApplicationContext(), FullSizePhotoActivity.class);
                  intent.putExtra("image name", imageName.getText().toString());
                  intent.putExtra("tags", tags.getText().toString());
                  startActivity(intent);
              } else {
                  // Change FullSizePhoto Fragment in landscape
                  // Fragment fragment = (FullSizeImageFragment) getFragmentManager().findFragmentById(R.id.fullSizePhotoFragment)
              }


          }
      });
        retrieveTwitterLogin();
        //retrieveFacebookLogin();
    }

    public void retrieveTwitterLogin() {

        //Initializing views
        profileImage = (NetworkImageView) findViewById(R.id.home_twitter_profile_image);
        textViewUsername = (TextView) findViewById(R.id.home_twitter_username_text_view);

        //Getting the intent
        Intent intent = getIntent();

        //Getting values from intent
        String username = intent.getStringExtra(LoginActivity.KEY_USERNAME);
        String profileImageUrl = intent.getStringExtra(LoginActivity.KEY_PROFILE_IMAGE_URL);

        //Loading image
        imageLoader = TwitterCustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(profileImageUrl, ImageLoader.getImageListener(profileImage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        profileImage.setImageUrl(profileImageUrl, imageLoader);

        //Setting the username in textview
        textViewUsername.setText("Welcome, " + username);
    }

    public void retrieveFacebookLogin() {
        // FB Login
//        Intent fbIntent = getIntent();
//        String fbUserName = fbIntent.getStringExtra(LoginActivity.KEY_USERNAME);
//        textViewUsername.setText("Welcome, " + fbUserName);
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

    private ArrayList<DataObject> getDataSet() {
        
        ArrayList<DataObject> imageDataList = new ArrayList<>();
//        for (int i = 0; i < 25; i++) {
//            DataObject obj = new DataObject("ImageName" + i + ".jpg",
//                    "tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13");
//            imageDataList.add(i, obj);
//        }
        DataObject obj1 = new DataObject("name1", "1tag1, 1tag2, 1tag3, 1tag4, 1tag5, 1tag6, 1tag7, 1tag8, 1tag9, 1tag10, 1tag11, 1tag12, 1tag13");
        DataObject obj2 = new DataObject("name2", "2tag1, 2tag2, 2tag3, 2tag4, 2tag5, 2tag6, 2tag7, 2tag8, 2tag9, 2tag10, 2tag11, 2tag12, 2tag13");
        DataObject obj3 = new DataObject("name3", "3tag1, 3tag2, 3tag3, 3tag4, 3tag5, 3tag6, 3tag7, 3tag8, 3tag9, 3tag10, 3tag11, 3tag12, 3tag13");
        DataObject obj4 = new DataObject("name4", "4tag1, 4tag2, 4tag3, 4tag4, 4tag5, 4tag6, 4tag7, 4tag8, 4tag9, 4tag10, 4tag11, 4tag12, 4tag13");
        DataObject obj5 = new DataObject("name5", "5tag1, 5tag2, 5tag3, 5tag4, 5tag5, 5tag6, 5tag7, 5tag8, 5tag9, 5tag10, 5tag11, 5tag12, 5tag13");
        imageDataList.add(0, obj1);
        imageDataList.add(1, obj2);
        imageDataList.add(2, obj3);
        imageDataList.add(3, obj4);
        imageDataList.add(4, obj5);
        return imageDataList;
    }
}
