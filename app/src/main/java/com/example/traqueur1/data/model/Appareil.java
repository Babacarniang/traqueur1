package com.example.traqueur1.data.model;

public class Appareil {

    private String id;
    private String proprietaire;
    private double code;


    public Appareil(String proprietaire, double code) {
        this.proprietaire = proprietaire;
        this.code = code;

    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        return;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        return;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

