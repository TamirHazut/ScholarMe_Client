package com.example.scholarme;

public class ScholarshipAdapterItem {
    private Integer image;
    private String name;
    private String description;
    private double matchingPercentage;

    public ScholarshipAdapterItem() {
    }

    public ScholarshipAdapterItem(Integer image, String name, String description, double matchingPercentage) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.matchingPercentage = matchingPercentage;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(double matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }
}
