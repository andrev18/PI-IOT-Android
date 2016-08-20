package com.pi_iot.andrev92.pi_iot.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by avlad92 on 8/20/2016.
 */
public class SPManager {
    private static final String KEY_SERVER_SETTINGS = "SERVER_SETTINGS";
    public static final String KEY_SERVER_IP = "SERVER_IP";
    public static final String KEY_SERVER_PORT = "SERVER_PORT";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SPManager(Context context) {
        preferences = context.getSharedPreferences(KEY_SERVER_SETTINGS,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean saveNewServerAddress(String IP,String PORT){
        editor.putString(KEY_SERVER_IP,IP);
        editor.putString(KEY_SERVER_PORT,PORT);
        return editor.commit();
    }

    public String getIPAddress(){
        return preferences.getString(KEY_SERVER_IP,"");
    }

    public String getPort(){
        return preferences.getString(KEY_SERVER_PORT,"");
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
