package com.example.testing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("properties")
    @Expose
    private Quake quake;

    public Quake getQuake() {
        return quake;
    }

    public void setQuakes(Quake quake) {
        this.quake = quake;
    }
}