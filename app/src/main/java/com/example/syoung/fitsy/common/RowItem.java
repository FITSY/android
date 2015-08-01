package com.example.syoung.fitsy.common;

/**
 * Created by HyunJoo on 2015. 7. 29..
 */
public class RowItem {
    private int imageId;
    private String image_name;

    public RowItem(int imageId/*, String image_name*/) {
        this.imageId = imageId;
        //this.image_name = image_name;
    }

    public RowItem(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return image_name;
    }

    public void setImageName(String _image_name) {
        this.image_name = _image_name;
    }

    @Override
    public String toString() {
        return image_name;
    }
}
