package com.tobin.fotofetcher;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Tobin on 1/18/16.
 */
public class FullSizeImageFragment extends Fragment {

    LinearLayout layout;


    private static String LOG_TAG = "FullSizeImageFragment";
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("imageInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        String image  = sharedPref.getString("image name", "null");
        String tags= sharedPref.getString("tags", "null");
        Log.i(LOG_TAG, "is null");

        if (image == null){
            Log.i(LOG_TAG, "is null");
        } else {
            Log.i(LOG_TAG, image);
        }

        View view = inflater.inflate(R.layout.fragment_full_size_photo, container, false);


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
//                tr.weight = 0;
                btn.setLayoutParams(tr);
                btn.setHeight(150);

                btn.setWidth(225);
                btn.setText(tagArray[counter]);
                counter++;
                row.addView(btn);
            }
            layout.addView(row);
        }

    }
}
