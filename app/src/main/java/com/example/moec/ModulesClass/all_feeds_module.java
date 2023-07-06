package com.example.moec.ModulesClass;


public class all_feeds_module {

    String clientname, descripation, time, comments, helpful;
    int image, menubutton, like_icon;

    public all_feeds_module(String clientname, String descripation) {
        this.clientname = clientname;
        this.descripation = descripation;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getDescripation() {
        return descripation;
    }

    public void setDescripation(String descripation) {
        this.descripation = descripation;
    }
}