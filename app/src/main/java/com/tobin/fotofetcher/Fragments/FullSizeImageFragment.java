package com.tobin.fotofetcher.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
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

import org.w3c.dom.Text;

/**
 * Created by Tobin on 1/18/16.
 */
public class FullSizeImageFragment extends Fragment {

    LinearLayout layout;

    private static String LOG_TAG = "FullSizeImageFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_size_photo, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("imageInfo", Context.MODE_PRIVATE);
        String imageName  = sharedPref.getString("image name", "null");
        String tags = sharedPref.getString("tags", "null");
        String url = sharedPref.getString("url", "null");
        Log.i(LOG_TAG, " urlllllll: " + url);

        Picasso.with(getActivity()).load(url).into((ImageView) view.findViewById(R.id.fullSizePhotoImageView));

        layout = (LinearLayout) view.findViewById(R.id.tagContainer);
        displayTags(tags);

        return view;
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
                layout.setWeightSum(12.0f);
                btn.setLayoutParams(tr);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn.setBackground(this.getResources().getDrawable(R.drawable.button_shape));
                }
                btn.setTextColor(Color.LTGRAY);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                btn.setText(tagArray[counter]);
                counter++;
                row.addView(btn);
            }
            layout.addView(row);
        }
    }
}
