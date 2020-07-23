package com.example.testing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quake {

    @SerializedName("mag")
    @Expose
    private double magnitude;

    @SerializedName("place")
    @Expose
    private String cityName;

    @SerializedName("time")
    @Expose
    private long timeInMilliseconds;

    public Quake() {
    }

    public Quake(double  magnitude, String cityName, long timeInMilliseconds){
        this.magnitude=magnitude;
        this.cityName=cityName;
        this.timeInMilliseconds=timeInMilliseconds;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getCityName(){
        return cityName;
    }

    public long getTimeInMilliseconds(){
        return timeInMilliseconds;
    }



}
