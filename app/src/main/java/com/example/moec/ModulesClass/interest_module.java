package com.example.moec.ModulesClass;

public class interest_module {
    int image;
    String text;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public interest_module(int image, String text) {
        this.image = image;
        this.text = text;
    }
}
