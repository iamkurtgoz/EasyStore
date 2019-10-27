package com.iamkurtgoz.easystore;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class EasyStoreBuilder {

    private static EasyStoreBuilder easyStoreBuilder;
    private String preferenceName;
    private Context context;
    private int preferenceMode = ContextWrapper.MODE_PRIVATE;
    public static SharedPreferences sharedPreferences;

    public static EasyStoreBuilder from(Context context, String preferenceName, int preferenceMode){
        return new EasyStoreBuilder(context, preferenceName, preferenceMode);
    }

    private EasyStoreBuilder(Context context, String preferenceName, int preferenceMode) {
        this.context = context;
        this.preferenceName = preferenceName;
        this.preferenceMode = preferenceMode;
    }

    public void create() {
        if (context == null) {
            throw new RuntimeException("Context is a null");
        }

        if (preferenceMode == -1) {
            preferenceMode = ContextWrapper.MODE_PRIVATE;
        }

        sharedPreferences = getContext().getSharedPreferences(getPreferenceName(), getPreferenceMode());
        easyStoreBuilder = this;
    }

    public static EasyStoreBuilder getBuilder(){
        return easyStoreBuilder;
    }

    public Context getContext() {
        return context;
    }

    public int getPreferenceMode() {
        return preferenceMode;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
