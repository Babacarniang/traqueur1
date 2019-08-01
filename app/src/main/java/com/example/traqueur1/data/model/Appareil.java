package com.example.traqueur1.data.model;

public class Appareil {

    private String id;
    private double proprietaire;
    private double code;


    public Appareil(double proprietaire, double code) {
        this.proprietaire = proprietaire;
        this.code = code;

    }

    public double getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(double proprietaire) {
        this.proprietaire = proprietaire;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

