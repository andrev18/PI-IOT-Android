package com.pi_iot.andrev92.pi_iot.http;

import com.pi_iot.andrev92.pi_iot.model.GPIO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by avlad92 on 8/20/2016.
 */
public interface ApiService {
    @POST("changeGpio")
    Call<Object> changeGpio(@Body GPIO gpio);
}
