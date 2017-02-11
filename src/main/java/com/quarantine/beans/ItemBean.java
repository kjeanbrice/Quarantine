package com.quarantine.beans;

import java.io.Serializable;

/**
 * Created by Karl on 2/8/2017.
 */
public class ItemBean implements Serializable{
    private String category;
    private String description;
    private String startDate;
    private String endDate;
    private String completed;



    public ItemBean(String category, String description, String startDate, String endDate, String completed) {
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
    }


    public ItemBean(){
        this("default", "default","default","default","default");
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }
}