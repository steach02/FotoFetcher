package com.tobin.fotofetcher.RecyclerViewStuff;

public class DataObject {
    private String imageName;
    private String tags;

    public DataObject (String text1, String text2){
        imageName = text1;
        tags = text2;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}