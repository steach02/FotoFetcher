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

import com.tobin.fotofetcher.RecyclerViewStuff.DataObject;
import com.tobin.fotofetcher.RecyclerViewStuff.DividerItemDecoration;
import com.tobin.fotofetcher.RecyclerViewStuff.MyRecyclerViewAdapter;
import com.tobin.fotofetcher.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("imageInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();

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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            editor.putString("image name", "imageName01.jpg");
            editor.putString("tags", "tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13");
            editor.putString("url", "https://pbs.twimg.com/profile_images/3330998786/d2f9f1edcde0f7e070f443d3fb04af6b_400x400.jpeg");
            editor.commit();
        }

        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
              MyRecyclerViewAdapter.MyClickListener() {
                  @Override
                  public void onItemClick(int position, View v, TextView imageName, TextView tags, TextView imageURL) {
                      Log.i(LOG_TAG, " url: " + imageURL.getText().toString());

                      editor.putString("image name", imageName.getText().toString());
                      editor.putString("tags", tags.getText().toString());
                      editor.putString("url", imageURL.getText().toString());
                      editor.commit();



                      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                      Log.i(LOG_TAG, imageName.getText().toString());
                          Intent intent = new Intent(getApplicationContext(), FullSizePhotoActivity.class);
                          intent.putExtra("image name", imageName.getText().toString());
                          intent.putExtra("tags", tags.getText().toString());
                          startActivity(intent);
                      }

                      }
                  });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
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
//                    "tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13 ", "url");
//            imageDataList.add(i, obj);
//        }

        DataObject obj0 = new DataObject("hankbob", "cantlookaway,faceonlyamothercouldlove", "https://pbs.twimg.com/profile_images/3330998786/d2f9f1edcde0f7e070f443d3fb04af6b_400x400.jpeg");
        DataObject obj1 = new DataObject("lisaburns", "creepy,mothercantevenlovethisface", "http://40.media.tumblr.com/8f7c3c67186e4d1940987a6b84f80d1b/tumblr_nnc2bu1lRE1qgepy0o1_250.jpg");

        imageDataList.add(0, obj0);
        imageDataList.add(1, obj1);

        return imageDataList;
    }
}
