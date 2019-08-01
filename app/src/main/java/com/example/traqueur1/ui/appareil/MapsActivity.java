package com.example.traqueur1.ui.appareil;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.traqueur1.R;
import com.example.traqueur1.data.model.Position;
import com.example.traqueur1.network.APIService;
import com.example.traqueur1.network.PositionResponse;
import com.example.traqueur1.network.RetrofitClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Accees aux service de localisation
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//Fournisseur de Position
        ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
        ArrayList<String> names = (ArrayList<String>) locationManager.getProviders(true);

        for (String name : names)
            providers.add(locationManager.getProvider(name));

        Criteria critere = new Criteria();

// Pour indiquer la précision voulue
// On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
        critere.setAccuracy(Criteria.ACCURACY_FINE);

// Est-ce que le fournisseur doit être capable de donner une altitude ?
        critere.setAltitudeRequired(true);

// Est-ce que le fournisseur doit être capable de donner une direction ?
        critere.setBearingRequired(true);

// Est-ce que le fournisseur peut être payant ?
        critere.setCostAllowed(false);

// Pour indiquer la consommation d'énergie demandée
// Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
        critere.setPowerRequirement(Criteria.POWER_HIGH);

// Est-ce que le fournisseur doit être capable de donner une vitesse ?
        critere.setSpeedRequired(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(16.028, -16.50490000000002), 5.2f));

        Retrofit retrofitClient = RetrofitClient.getRetrofitClient();
        APIService service = retrofitClient.create(APIService.class);
        Call<PositionResponse> call = service.getPosition();
        call.enqueue(new Callback<PositionResponse>() {
            @Override
            public void onResponse(Call<PositionResponse> call, Response<PositionResponse> response) {

                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getSuccess());
                    List<Position> positionList = response.body().getPositions();
                    for (Position position : positionList) {
                        LatLng latLng = new LatLng(position.getLatitude(), position.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng));//.title("Marker in Sydney"));
                    }
                } else
                    Log.d(TAG, "onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<PositionResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });


//        LatLng sydney0 = new LatLng(16.028, -16.50490000000002);
//        LatLng sydney1 = new LatLng(14.7948093, -16.9529272);
//        LatLng sydney2 = new LatLng(15.2401304, -15.3441834);
//        LatLng sydney3 = new LatLng(13.9631756, -15.7888834);
//        LatLng sydney4 = new LatLng(12.5634929, -16.2724609);
//        LatLng sydney5 = new LatLng(60.9666299, 7.0626786);
//        LatLng sydney6 = new LatLng(13.9406041, -16.4024021);
//        LatLng sydney7 = new LatLng(13.7692, -13.668300000000045);
//        LatLng sydney8 = new LatLng(15.0028849, -13.9240088);
//        LatLng sydney9 = new LatLng(12.8856865, -12.286425);
//        LatLng sydney10 = new LatLng(12.8935348, -15.5560695);
//        LatLng sydney11 = new LatLng(14.7043898, -16.0511737);
//        LatLng sydney12 = new LatLng(14.2824927, -15.0247364);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney0).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney1).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney2).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney3).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney4).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney5).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney6).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney7).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney8).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney9).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney10).title("Marker in Sydney"));
//        mMap.addMarker(new MarkerOptions().position(sydney11).title("Marker in Sydney"));


    }


}
