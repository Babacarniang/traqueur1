package com.example.traqueur1.data.model;

public class Position {

    private String id;
    private double latitude;
    private double longitude;


    public Position(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
