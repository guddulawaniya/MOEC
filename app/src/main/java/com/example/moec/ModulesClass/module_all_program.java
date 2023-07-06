package com.example.moec.ModulesClass;

public class module_all_program {
    String coursename, duration, fees, countryname, collegename;
    int universityimage;

    public module_all_program(String coursename, String duration, String fees, String countryname, String collegename) {
        this.coursename = coursename;
        this.duration = duration;
        this.fees = fees;
        this.countryname = countryname;
        this.collegename = collegename;

    }

    public int getUniversityimage() {
        return universityimage;
    }

    public void setUniversityimage(int universityimage) {
        this.universityimage = universityimage;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String location) {
        this.fees = location;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }
}
