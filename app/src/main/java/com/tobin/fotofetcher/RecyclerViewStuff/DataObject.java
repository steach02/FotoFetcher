package com.tobin.fotofetcher.RecyclerViewStuff;

public class DataObject {
    private String imageName;
    private String tags;
    private String imageURL;

    public DataObject (String text1, String text2, String text3){
        imageName = text1;
        tags = text2;
        imageURL = text3;

    }

    public String getImageName() {
        return imageName;
    }

    public String getTags() {
        return tags;
    }

    public String getURL(){
        return imageURL;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
}