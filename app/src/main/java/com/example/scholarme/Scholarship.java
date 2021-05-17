package com.example.scholarme;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Scholarship {

    private String id;
    private String name;
    private Date create_time;
    private String description;
    private List<String> links;
    private Set<String> tags;
    private double matchingPercentage;

    public Scholarship(){

    }

    public Scholarship(String id, String name, Date create_time, String description, List<String> links, Set<String> tags, double matchingPercentage) {
        this.id = id;
        this.name = name;
        this.create_time = create_time;
        this.description = description;
        this.links = links;
        this.tags = tags;
        this.matchingPercentage = matchingPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public double getMatchingPercentage() {
        return matchingPercentage;
    }

    public void setMatchingPercentage(double matchingPercentage) {
        this.matchingPercentage = matchingPercentage;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", create_time=" + create_time +
                ", description='" + description + '\'' +
                ", links=" + links +
                ", tags=" + tags +
                ", matchingPercentage=" + matchingPercentage +
                '}';
    }
}
