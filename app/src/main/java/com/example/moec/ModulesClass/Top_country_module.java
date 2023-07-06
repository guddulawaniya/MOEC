package com.example.moec.ModulesClass;

public class Top_country_module {
    int countryimage;
    String countryname;

    public Top_country_module(int countryimage, String countryname) {
        this.countryimage = countryimage;
        this.countryname = countryname;
    }

    public int getCountryimage() {
        return countryimage;
    }

    public void setCountryimage(int countryimage) {
        this.countryimage = countryimage;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
}
