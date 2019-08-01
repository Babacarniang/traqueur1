package com.example.traqueur1.ui.appareil;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.example.traqueur1.data.model.Position;
import com.example.traqueur1.network.APIService;
import com.example.traqueur1.network.RetrofitClient;
import com.example.traqueur1.network.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppareilPositionService extends IntentService implements LocationListener {
    private static final String TAG = "MapsActivity";

    public AppareilPositionService(String name) {
        super(name);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");

        Retrofit retrofitClient = RetrofitClient.getRetrofitClient();
        APIService service = retrofitClient.create(APIService.class);
        service.sendPosition(new Position(location.getLatitude(), location.getLongitude()))
                .enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.d(TAG, "onResponse: " + response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.d(TAG, "onResponse: " + t.getMessage());
                    }
                });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
