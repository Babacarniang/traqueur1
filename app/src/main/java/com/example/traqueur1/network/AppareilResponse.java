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

    private String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    private int status;

    public void setAppareil(List<Appareil> appareils) {
        this.appareils = appareils;
    }

    public List<Appareil> sendAppareil() {
        return appareils;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}



