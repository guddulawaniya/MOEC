package com.example.moec.ModulesClass;

public class studentline_module {
    public studentline_module(String stepcout, String stepname, String completdate, int image) {
        this.stepcout = stepcout;
        this.stepname = stepname;
        this.completdate = completdate;
        this.image = image;
    }


    public String getStepcout() {
        return stepcout;
    }

    public void setStepcout(String stepcout) {
        this.stepcout = stepcout;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public String getCompletdate() {
        return completdate;
    }

    public void setCompletdate(String completdate) {
        this.completdate = completdate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    String stepcout, stepname, completdate;

    int image;

}
