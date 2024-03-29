package com.example.traqueur1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.20/traqueur-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;

    }
}

