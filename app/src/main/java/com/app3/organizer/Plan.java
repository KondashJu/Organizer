package com.app3.organizer;

public class Plan {
    private long id;
    private String name, date, time, details;

    public Plan(String name, String date, String time, String details) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.details = details;
    }

    public long getId(){
        return id;
    }

    public void setId(long planId){
        this.id = planId;
    }

    public String getName(){
        return name;
    }

    public void setName(String planName){
        name = planName;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String planDate){
        date = planDate;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String planTime){
        time = planTime;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String planDetails){
        details = planDetails;
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + ", name=" + name + ", date=" + date
                + ", time=" + time + ", details=" + details +"]";
    }

}
