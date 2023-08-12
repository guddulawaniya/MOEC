package com.example.moec.ModulesClass;

import android.net.Uri;

public class Univerity_Course_Module {
    String iconimage;
    String universityname, totalcourse;

    public Univerity_Course_Module(String iconimage, String universityname, String totalcourse) {
        this.iconimage = iconimage;
        this.universityname = universityname;
        this.totalcourse = totalcourse;
    }

    public String getIconimage() {
        return iconimage;
    }

    public void setIconimage(String iconimage) {
        this.iconimage = iconimage;
    }

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname;
    }

    public String getTotalcourse() {
        return totalcourse;
    }

    public void setTotalcourse(String totalcourse) {
        this.totalcourse = totalcourse;
    }
}