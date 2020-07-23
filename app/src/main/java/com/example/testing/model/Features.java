package com.example.testing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Features {

    @SerializedName("features")
    @Expose
    private List<Properties> Quakes;

    public List<Properties> getQuakes() {
        return Quakes;
    }

    public void setQuakes(List<Properties> quakes) {
        Quakes = quakes;
    }
}
