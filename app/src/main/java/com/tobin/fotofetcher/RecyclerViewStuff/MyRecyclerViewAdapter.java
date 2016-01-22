package com.tobin.fotofetcher.RecyclerViewStuff;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobin.fotofetcher.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter <MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView imageName;
        TextView tags;
        TextView imageURL;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imageName = (TextView) itemView.findViewById(R.id.imageNameTextView);
            tags = (TextView) itemView.findViewById(R.id.tagNameTextView);
            imageURL = (TextView) itemView.findViewById(R.id.imageURLTextView);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v, imageName, tags, imageURL);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//        holder.imageName.setText(mDataset.get(position).getImageName());
//        holder.tags.setText(mDataset.get(position).getTags());
        holder.imageURL.setText(mDataset.get(position).getURL());
        String fullTags = mDataset.get(position).getTags();
        String fullName = mDataset.get(position).getImageName();

        // abbreviates the image name
        if (fullName.length() > 9){
            fullName = fullName.substring(0, 9);
            fullName += "...";
        }
        // abreviates the tags
        if (fullTags.length() > 8) {
            fullTags = fullTags.substring(0, 8);
            fullTags += "...";
        }

        holder.imageName.setText(fullName);
        holder.tags.setText(fullTags);
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
         void onItemClick(int position, View v, TextView label, TextView tagsView, TextView imageURL);
    }
}