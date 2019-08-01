package com.example.traqueur1.network;

import com.example.traqueur1.data.model.Appareil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppareilResponse {

    @SerializedName("appareils")
    private List<Appareil> appareils;
    @SerializedName("success")
    private int success;

    public List<Appareil> getAppareil() {
        return appareils;
    }

    public void setAppareils(List<Appareil> positions) {
        this.appareils = appareils;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}


