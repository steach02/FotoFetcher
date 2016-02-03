package com.tobin.fotofetcher.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tobin.fotofetcher.R;

public class FullSizeImageFragment extends Fragment {

    LinearLayout tagLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_size_photo, container, false);
    }


    public void setImageAttributes(String name, String tags, String url) {
        setName(name);
        displayTags(tags);
        setURL(url);
    }

    public void setName(String name) {
        TextView nameTextView = (TextView) getActivity().findViewById(R.id.image_name);
        nameTextView.setText(name);
    }

    public void setURL(String url) {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.fullSizePhotoImageView);
        Picasso.with(getActivity()).load(url).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here if we are in horizontal then go to a full size popup picture
            }
        });
    }


    public void displayTags(String tags) {
        String[] tagArray = tags.split(",");

        Button btn;
        LinearLayout ll = (LinearLayout) getActivity().findViewById(R.id.tagContainer);
        ll.removeAllViews();
        int counter = 0;

// clear priviouse tags
        for (int i = 0; i < tagArray.length; i++) {
            btn = new Button(getActivity());
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            btn.setId(i);
            btn.setText(tagArray[counter]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(),)
                    //call popup menu and pass tag
                }
            });
            ll.addView(btn);
            counter++;
        }
        btn = new Button(getActivity());
        btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        btn.setText("Add Text");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(),)
                //call popup menu this has no tag data to pass to in so the hint should say add new tags
            }
        });
        ll.addView(btn);


    }
}