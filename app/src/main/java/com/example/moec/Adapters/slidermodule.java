package com.example.moec.Adapters;

public class slidermodule {
    int Images;
    String title;

    public slidermodule(int images, String title) {
        Images = images;
        this.title = title;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
