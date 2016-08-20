package com.pi_iot.andrev92.pi_iot;

import android.app.Application;
import android.content.SharedPreferences;

import com.pi_iot.andrev92.pi_iot.http.ApiService;
import com.pi_iot.andrev92.pi_iot.http.ServerApi;
import com.pi_iot.andrev92.pi_iot.utils.SPManager;

/**
 * Created by avlad92 on 8/20/2016.
 */
public class IOTApp extends Application {

    private static SPManager spManager;
    private static ApiService apiService;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;


    @Override
    public void onCreate() {
        super.onCreate();
        spManager = new SPManager(this);
        buildService();
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                buildService();
            }
        };
        spManager.getPreferences().registerOnSharedPreferenceChangeListener(listener);
    }



    public static SPManager getSpManager() {
        return spManager;
    }

    public static ApiService getApiService() {
        return apiService;
    }

    private void buildService(){
        apiService = ServerApi.getServerApi("http://"+spManager.getIPAddress()+":"+spManager.getPort()+"/");
    }


}
