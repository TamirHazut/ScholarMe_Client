package com.example.scholarme;

public class Search_Scholarship {

    private String institute;
    private String degree;
    private String location;
    private String sectors;
    private String study_year;
    private String graduation_year;
    private Gender gender;
   private Contribution contribution;
    public Search_Scholarship(){};

   public Search_Scholarship(String institute, String degree, String location, String sectors, String study_year, String graduation_year, Gender gender, Contribution contribution){
       this.institute = institute;
       this.degree = degree;
       this.location = location;
       this.sectors = sectors;
       this.study_year = study_year;
       this.graduation_year = graduation_year;
       this.gender = gender;
       this.contribution = contribution;
   };

    public String getInstitute() {
        return institute;
    }


    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSectors() {
        return sectors;
    }

    public void setSectors(String sectors) {
        this.sectors = sectors;
    }

    public String getStudy_year() {
        return study_year;
    }

    public void setStudy_year(String study_year) {
        this.study_year = study_year;
    }

    public String getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year = graduation_year;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    @Override
    public String toString() {
        return "Search_Scholarship{" +
                "institute='" + institute + '\'' +
                ", degree='" + degree + '\'' +
                ", location='" + location + '\'' +
                ", sectors='" + sectors + '\'' +
                ", study_year='" + study_year + '\'' +
                ", graduation_year='" + graduation_year + '\'' +
                ", gender=" + gender +
                ", contribution=" + contribution +
                '}';
    }
}
