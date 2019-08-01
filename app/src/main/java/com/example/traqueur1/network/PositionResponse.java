package com.example.traqueur1.network;

import com.example.traqueur1.data.model.Position;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PositionResponse {
    @SerializedName("positions")
    private List<Position> positions;
    @SerializedName("success")
    private int success;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
