package com.example.traqueur1.network;

import com.example.traqueur1.data.model.Appareil;
import com.example.traqueur1.data.model.Position;
import com.example.traqueur1.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * TOUTES les methodes qui communiquent avec mon api seront creees ici
 * dans cette interface
 */


public interface APIService {


    @POST("login.php")
    @FormUrlEncoded
    Call<User> login(@Field("username") String username,
                     @Field("password") String password);

    @POST("register.php")
    @FormUrlEncoded
    Call<ServerResponse> registerUser(@Field("username") String username,
                                      @Field("full_name") String fullname,
                                      @Field("password") String password);

    @POST("position.php")
    Call<ServerResponse> sendPosition(@Body Position position);

    @GET("getPositions.php")
    Call<PositionResponse> getPosition();


    @POST("appareil.php")
    Call<ServerResponse> sendAppareil(@Body Appareil appareil);

    @GET("getAppareils.php")
    Call<AppareilResponse> getAppareil();

    Call<ServerResponse> valider(String proprietaire, String code);


    /**
     * cette methode je l'appelles dans ma Classe RegisterActivity
     *en donnant comme parametre une instance d'un utilisateur
     * @param user
     * @return
     */
}
