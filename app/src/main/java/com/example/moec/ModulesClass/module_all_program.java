package com.example.moec.ModulesClass;

public class module_all_program {
    String coursename, duration, fees, countryname, collegename,universityimage,intake,link,criteria;

    public module_all_program(String coursename, String duration, String fees, String countryname, String collegename, String universityimage, String intake, String link, String criteria) {
        this.coursename = coursename;
        this.duration = duration;
        this.fees = fees;
        this.countryname = countryname;
        this.collegename = collegename;
        this.universityimage = universityimage;
        this.intake = intake;
        this.link = link;
        this.criteria = criteria;
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

    public void setFees(String fees) {
        this.fees = fees;
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

    public String getUniversityimage() {
        return universityimage;
    }

    public void setUniversityimage(String universityimage) {
        this.universityimage = universityimage;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
