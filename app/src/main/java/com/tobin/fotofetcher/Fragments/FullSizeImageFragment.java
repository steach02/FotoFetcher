package com.tobin.fotofetcher.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tobin.fotofetcher.R;


/**
 * Created by Tobin on 1/18/16.
 */
public class FullSizeImageFragment extends Fragment {

    LinearLayout tagLayout;

    // variables for saved instance state
//    String savedPosition;
//    String savedName;
//    String savedURL;
//    String savedTags;


    private static String LOG_TAG = "FullSizeImageFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_size_photo, container, false);


//        displayTags(tags);
        return view;
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save the user's current game state
//        savedInstanceState.putString("NAME", savedName);
//        savedInstanceState.putString("TAGS", savedTags);
//        savedInstanceState.putString("URL", savedURL);
//
//        // Always call the superclass so it can save the view hierarchy state
//        super.onSaveInstanceState(savedInstanceState);
//    }

    public void setImageAttributes(String name, String tags, String url){
//        savedPosition = position;
//        savedName = name;
//        savedTags = tags;
//        savedURL = url;

        setName(name);
        setTags(tags);
        setURL(url);
    }

    public void setName(String name){
        TextView nameTextView = (TextView) getActivity().findViewById(R.id.image_name);
        nameTextView.setText(name);
    }

    public void setTags(String tags){
        tagLayout = (LinearLayout) getActivity().findViewById(R.id.tagContainer);
        TextView tagsTextView = new TextView(getActivity());
        tagsTextView.setText(tags);
        tagsTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        tagLayout.addView(tagsTextView);

    }

    public void setURL(String url){
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.fullSizePhotoImageView);
        Picasso.with(getActivity()).load(url).into(imageView);
    }


    public void displayTags(String tags){
        String[] tagArray = tags.split(",");
        Button btn;
        int counter = 0;


        for (int i = 0; i < tagArray.length / 3; i++) {
            TableRow row = new TableRow(getActivity());

            for (int k = 1; k < 4; k++) {
                btn = new Button(getActivity());
                TableRow.LayoutParams tr = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tagLayout.setWeightSum(12.0f);
//                tr.weight = 0;
                btn.setLayoutParams(tr);
                btn.setHeight(150);

                btn.setWidth(225);
                btn.setText(tagArray[counter]);
                counter++;
                row.addView(btn);
            }
            tagLayout.addView(row);
        }

    }
}
