package com.example.admin.hvacapp;

public class UserData {
     String total_capacity,site,address,area_city,rating;

     public String getTotal_capacity () {
        return "total_capacity:   "+total_capacity;
    }

    public void setTotal_capacity(String total_capacity) {
        this.total_capacity = total_capacity;
    }

    public String getAddress() {
        return "address:  "+address;
    }

    public void setArea_city(String area_city) {
        this.area_city = area_city;
    }
    public String getArea_city() {
        return "area and city:     "+area_city;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRating() {
        return "area and city:     "+rating;
    }

    public void setSite(String address) {
        this.site = address;
    }
    public String getSite() {
        return "project_ site:     "+     site;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    UserData(){

    }
}


