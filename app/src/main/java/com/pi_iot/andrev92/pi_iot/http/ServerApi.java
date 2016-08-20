package com.pi_iot.andrev92.pi_iot.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by avlad92 on 8/20/2016.
 */
public class ServerApi {

    private static Retrofit retrofit;
    private static ApiService apiService;


    public static ApiService getServerApi(String baseURL){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
