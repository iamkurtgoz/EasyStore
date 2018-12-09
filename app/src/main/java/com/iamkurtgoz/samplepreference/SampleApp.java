package com.iamkurtgoz.samplepreference;

import android.app.Application;

import com.iamkurtgoz.easypreference.EasyPreference;
import com.iamkurtgoz.easypreference.PreferenceMode;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EasyPreference.with(getApplicationContext(), "mypreference", PreferenceMode.MODE_PRIVATE).create();
    }
}
