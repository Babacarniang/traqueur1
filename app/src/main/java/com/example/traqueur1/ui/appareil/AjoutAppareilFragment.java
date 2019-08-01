package com.example.traqueur1.ui.appareil;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.traqueur1.R;
import com.example.traqueur1.data.MySingleton;
import com.example.traqueur1.data.SessionHandler;
import com.example.traqueur1.network.APIService;
import com.example.traqueur1.network.RetrofitClient;
import com.example.traqueur1.network.ServerResponse;
import com.example.traqueur1.ui.HostActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AjoutAppareilFragment extends Fragment {

    private static final String TAG = "AjoutAppareilFragment";
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PROPRIETAIRE = "proprietaire";
    private static final String KEY_CODE = "code";
    private static final String KEY_EMPTY = "";
    private static final String TAG1 = "AjoutAppareilFragment";
    private Context mContext;
    private EditText etproprietaire;
    private EditText etcode;
    private String proprietaire;
    private String code;
    private ProgressDialog pDialog;
    private String appareil_url = "http://192.168.1.16/traqueur-api/appareil.php";
    private SessionHandler session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_ajout_appareil, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etproprietaire = etproprietaire.findViewById(R.id.etproprietaire);
        etcode = etcode.findViewById(R.id.etcode);
        Button valider = view.findViewById(R.id.btnvalider);

// Ici j'essai de recuperer le code de l'appareil envoyé dans la liste appareil
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                proprietaire = etproprietaire.getText().toString().toLowerCase().trim();
                code = etcode.getText().toString().trim();
                if (validateInputs()) {
                    //valider();
                    validerWithRetrofit(proprietaire, code);
                }

            }
        });
    }

    private void validerWithRetrofit(final String proprietaire, String code) {
        displayLoader();
        Retrofit retrofitClient = RetrofitClient.getRetrofitClient();
        APIService service = retrofitClient.create(APIService.class);
        Call<ServerResponse> call = service.valider(proprietaire, code);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getMessage());
                ServerResponse serverResponse = response.body();
                pDialog.dismiss();
                //Check if user got validered successfully
                if (serverResponse.getStatus() == 0) {

                } else if (serverResponse.getStatus() == 1) {
                    //Display error message if proprietaire is already existsing
                    etproprietaire.setError("proprietaire already taken!");
                    etcode.requestFocus();

                } else {
                    Toast.makeText(mContext.getApplicationContext(),
                            serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(mContext.getApplicationContext(),
                        "Echec de l'enregistrement", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });


    }

    /**
     * Display Progress bar while validering
     */
    private void displayLoader() {
        pDialog.setMessage("Validation.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    /**
     * Launch Host Activity on Successful Sign Up
     */
    private void HostActivity() {
        Intent i = new Intent(mContext.getApplicationContext(), HostActivity.class);
        startActivity(i);
        finish();

    }

    private void finish() {
    }

    private void valider() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_PROPRIETAIRE, proprietaire);
            request.put(KEY_CODE, code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, appareil_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        pDialog.dismiss();
                        try {
                            //Check if user got validered successfully
                            if (response.getInt(KEY_STATUS) == 0) {

                            } else if (response.getInt(KEY_STATUS) == 1) {
                                //Display error message if proprietaire is already existsing
                                etproprietaire.setError("proprietaire already taken!");
                                etproprietaire.requestFocus();

                            } else {
                                Toast.makeText(mContext.getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(mContext.getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     *
     * @return
     */
    private boolean validateInputs() {

        if (KEY_EMPTY.equals(proprietaire)) {
            etproprietaire.setError("proprietaire cannot be empty");
            etproprietaire.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(code)) {
            etcode.setError("code cannot be empty");
            etcode.requestFocus();
            return false;
        }
        return true;
    }
}
