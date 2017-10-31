package com.example.ftrani.apiclimademo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ftrani on 19/10/17.
 */

public class City {
    private int id;
    private String name;
    @SerializedName("main")
    private Clima clima;

    public City(int id, String name, Clima clima) {
        this.id = id;
        this.name = name;
        this.clima = clima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public static Clima parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        Clima clima = gson.fromJson(response,Clima.class);
        return clima;
    }

}
