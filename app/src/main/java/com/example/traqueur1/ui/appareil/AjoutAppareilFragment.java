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
import com.example.traqueur1.data.model.Appareil;
import com.example.traqueur1.network.APIService;
import com.example.traqueur1.network.AppareilResponse;
import com.example.traqueur1.network.RetrofitClient;
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
    private Context mContext;
    private EditText etproprietaire;
    private EditText etcode;
    private String proprietaire;
    private double code;
    private ProgressDialog pDialog;
    private String appareil_url = "http://192.168.1.20/traqueur-api/appareil.php";
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
        etproprietaire = view.findViewById(R.id.etproprietaire);
        etcode = view.findViewById(R.id.etcode);
        Button valider = view.findViewById(R.id.btnvalider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Récupérer les données saisies dans les textes d'édition
                proprietaire = etproprietaire.getText().toString().toLowerCase().trim();
                code = Double.parseDouble(etcode.getText().toString().trim());

                //valider();
                if (validateInputs()) validerWithRetrofit(proprietaire, code);

            }
        });
    }

    private void validerWithRetrofit(final String proprietaire, double code) {
        displayLoader();
        Retrofit retrofitClient = RetrofitClient.getRetrofitClient();
        APIService service = retrofitClient.create(APIService.class);
        service.sendAppareil(new Appareil(proprietaire, code))
                .enqueue(new Callback<AppareilResponse>() {
            @Override
            public void onResponse(Call<AppareilResponse> sendAppareil, retrofit2.Response<AppareilResponse> response) {
                Log.d(TAG, "onResponse:" + response.body().getMessage());
                AppareilResponse AppareilResponse = response.body();
                pDialog.dismiss();
                //Vérifier si l'utilisateur a été validé avec succès

                if (AppareilResponse.getStatus() == 0) {
                    //Afficher le message d'erreur si le propriétaire existe déjà
                    etproprietaire.setError("propriétaire déjà pris!");
                    etcode.requestFocus();
                } else {
                    Toast.makeText(mContext.getApplicationContext(),
                            AppareilResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AppareilResponse> sendAppareil, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(mContext.getApplicationContext(),
                        "Echec de l'enregistrement", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
    /**
     * Affichage de la barre de progression pendant la validation
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Validation.. Veuillez patienter...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     * Launch Host Activity on Successful Valider
     */
    private void HostActivity() {
        Intent i = new Intent(mContext.getApplicationContext(), HostActivity.class);
        startActivity(i);
        finish();
    }

    private void finish() {
    }

    private void sendAppareil() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Remplir les paramètres de la requête
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
                            //Vérifier si l'utilisateur a été validé avec succès
                            if (response.getInt(KEY_STATUS) == 0) {

                            } else if (response.getInt(KEY_STATUS) == 1) {
                                //Afficher le message d'erreur si le propriétaire existe déjà
                                etproprietaire.setError("propriétaire déjà pris!");
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

                        //Afficher le message d'erreur chaque fois qu'une erreur se produit
                        Toast.makeText(mContext.getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Accédez à RequestQueueue via votre classe singleton.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Valide les entrées et affiche les erreurs éventuelles
     * @return
     */
    private boolean validateInputs() {

        if (KEY_EMPTY.equals(proprietaire)) {
            etproprietaire.setError("le propriétaire ne peut pas être vide");
            etproprietaire.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(code)) {
            etcode.setError("le code ne peut pas être vide");
            etcode.requestFocus();
            return false;
        }
        return true;
    }
}
