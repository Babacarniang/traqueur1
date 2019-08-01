package com.example.traqueur1.network;

import com.example.traqueur1.data.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("user")
    private List<User> users;
    @SerializedName("success")
    private int success;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> positions) {
        this.users = users;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}


