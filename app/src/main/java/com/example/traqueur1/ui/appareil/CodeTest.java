package com.example.traqueur1.ui.appareil;

/*public class CodeTest extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PROPRIETAIRE = "proprietaire";
    private static final String KEY_CODE = "code";
    private static final String KEY_EMPTY = "";
    private EditText etProprietaire;
    private EditText etCode;
    private String Proprietaire;
    private String code;
    private ProgressDialog pDialog;
    private String appareil_url = "http://192.168.1.16/traqueur-api/appareil.php";
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.fragment_ajout_appareil);
        etProprietaire = findViewById(R.id.etproprietaire);
        etCode = findViewById(R.id.etcode);


        Button valider = findViewById(R.id.btnvalider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                Proprietaire = etProprietaire.getText().toString().toLowerCase().trim();
                code = etCode.getText().toString().trim();
                if (validateInputs()) {
                    //validerappareil();
                    validerWithRetrofit(Proprietaire, code);
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
                //Check if user got valider successfully
                if (serverResponse.getStatus() == 0) {

                } else if (serverResponse.getStatus() == 1) {
                    //Display error message if proprietaire is already existsing
                    etProprietaire.setError("Proprietaire already taken!");
                    etProprietaire.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(),
                            serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Echec de l'enregistrement", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
    private static final String TAG = "AjoutAppareilFragment";

    /**
     * Display Progress bar while validering
     */
  /*  private void displayLoader() {
        pDialog = new ProgressDialog(CodeTest.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    /**
     * Launch Host Activity on Successful Valider Appareil
     */
   /* private void HostActivity() {
        Intent i = new Intent(getApplicationContext(), HostActivity.class);
        startActivity(i);
        finish();

    }

    private void valider() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_PROPRIETAIRE, Proprietaire);
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

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if proprietaire is already existsing
                                etProprietaire.setError("Proprietaire already taken!");
                                etProprietaire.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
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
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
   /* private boolean validateInputs() {
        if (KEY_EMPTY.equals(Proprietaire)) {
            etProprietaire.setError("Proprietaire cannot be empty");
            etProprietaire.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(code)) {
            etCode.setError("Code cannot be empty");
            etCode.requestFocus();
            return false;
        }

        return true;
    }
}

*/