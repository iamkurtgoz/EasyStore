package com.iamkurtgoz.samplepreference;

import android.app.Application;

import com.iamkurtgoz.easystore.EasyStoreBuilder;
import com.iamkurtgoz.easystore.PreferenceMode;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EasyStoreBuilder.from(getApplicationContext(), "mypreference",  PreferenceMode.MODE_PRIVATE).create();
    }
}
