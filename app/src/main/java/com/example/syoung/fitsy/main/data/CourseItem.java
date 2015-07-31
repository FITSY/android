package com.example.syoung.fitsy.main.data;

public class CourseItem {
    private int imageId;
    private String imageName;

    public CourseItem(int imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }

    public CourseItem(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String _image_name) {
        this.imageName = _image_name;
    }

    @Override
    public String toString() {
        return imageName;
    }
}
