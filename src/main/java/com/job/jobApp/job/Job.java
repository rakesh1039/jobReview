package com.job.jobApp.job;

import jakarta.persistence.*;

@Entity
//@Table(name="job_table")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String minimumSalry;
    private String maximumSalary;
    private String location;

    public Job() {

    }

    public Job(Long id, String title, String description, String minimumSalry, String maximumSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minimumSalry = minimumSalry;
        this.maximumSalary = maximumSalary;
        this.location = location;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinimumSalry() {
        return minimumSalry;
    }

    public void setMinimumSalry(String minimumSalry) {
        this.minimumSalry = minimumSalry;
    }

    public String getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(String maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
