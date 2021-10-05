package com.example.f8_weatherapi;

public class Artist {
    private String name;

    public Artist(){

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
