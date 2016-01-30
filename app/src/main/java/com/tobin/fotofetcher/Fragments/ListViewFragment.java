package com.tobin.fotofetcher.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobin.fotofetcher.R;
import com.tobin.fotofetcher.RecyclerViewStuff.DataObject;
import com.tobin.fotofetcher.RecyclerViewStuff.DividerItemDecoration;
import com.tobin.fotofetcher.RecyclerViewStuff.MyRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by Tobin on 1/18/16.
 */
public class ListViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static String LOG_TAG = "ListFragment";

    private onItemClickedListener listener;

    public interface onItemClickedListener{
        void onItemClicked(String position, String name, String tags, String url);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
              MyRecyclerViewAdapter.MyClickListener() {
                  @Override
                  public void onItemClick(int position, View v, TextView imageName, TextView tags, TextView url) {
//                      Log.i(LOG_TAG, " Clicked on Item " + position);
                      listener.onItemClicked("" + position, imageName.getText().toString(), tags.getText().toString(), url.getText().toString());
                  }
              });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onItemClickedListener) {
            listener = (onItemClickedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList<DataObject> imageDataList = new ArrayList<>();
//        for (int i = 0; i < 25; i++) {
//            DataObject obj = new DataObject("ImageName" + i + ".jpg",
//                    "tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9, tag10, tag11, tag12, tag13");
//            imageDataList.add(i, obj);
//        }
        DataObject obj1 = new DataObject(
                "name1",
                "1tag1, 1tag2, 1tag3, 1tag4, 1tag5, 1tag6, 1tag7, 1tag8, 1tag9, 1tag10, 1tag11, 1tag12, 1tag13",
                "http://images7.memedroid.com/avatars/AVATAR65/55768b80b15aa.jpeg");
        DataObject obj2 = new DataObject(
                "name2",
                "2tag1, 2tag2, 2tag3, 2tag4, 2tag5, 2tag6, 2tag7, 2tag8, 2tag9, 2tag10, 2tag11, 2tag12, 2tag13",
                "http://img.ifcdn.com/images/b991ea55f357101d895250308987b00356df44affb126810bc9e9099f5612747_1.jpg");
        DataObject obj3 = new DataObject(
                "name3",
                "3tag1, 3tag2, 3tag3, 3tag4, 3tag5, 3tag6, 3tag7, 3tag8, 3tag9, 3tag10, 3tag11, 3tag12, 3tag13",
                "http://api.ning.com/files/kV4MbYiv7oRidf3AaaNXC1F7274JDTgNC7q4lO6oYUO7abgYq0aCWql2h7nSYfvxm4itcQrYRGJ8mqZ2GEvPND4z847eV1aX/1082034191.jpeg");
        DataObject obj4 = new DataObject(
                "name4",
                "4tag1, 4tag2, 4tag3, 4tag4, 4tag5, 4tag6, 4tag7, 4tag8, 4tag9, 4tag10, 4tag11, 4tag12, 4tag13",
                "http://api.ning.com/files/kV4MbYiv7oRLu*fzoHWERwweMGG3KsFu0Huyl8DEXs8NYcjPhS1o-9FRWPsa9xovOxDgB9k0-dS59hv*6hx-fB86JdUkP1-Y/1082034107.jpeg");
        DataObject obj5 = new DataObject(
                "name5",
                "5tag1, 5tag2, 5tag3, 5tag4, 5tag5, 5tag6, 5tag7, 5tag8, 5tag9, 5tag10, 5tag11, 5tag12, 5tag13",
                "http://media.creativebloq.futurecdn.net/sites/creativebloq.com/files/images/2015/07/bart10.jpg");
        imageDataList.add(0, obj1);
        imageDataList.add(1, obj2);
        imageDataList.add(2, obj3);
        imageDataList.add(3, obj4);
        imageDataList.add(4, obj5);
        return imageDataList;
    }
}
