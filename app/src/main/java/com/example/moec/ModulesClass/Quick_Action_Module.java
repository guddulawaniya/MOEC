package com.example.moec.ModulesClass;

public class Quick_Action_Module {
    int iconimage;
    String titletext;

    public Quick_Action_Module(int iconimage, String titletext) {
        this.iconimage = iconimage;
        this.titletext = titletext;
    }

    public int getIconimage() {
        return iconimage;
    }

    public void setIconimage(int iconimage) {
        this.iconimage = iconimage;
    }

    public String getTitletext() {
        return titletext;
    }

    public void setTitletext(String titletext) {
        this.titletext = titletext;
    }
}
