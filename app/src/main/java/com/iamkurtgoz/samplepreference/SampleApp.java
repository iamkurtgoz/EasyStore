package com.iamkurtgoz.samplepreference;

import android.app.Application;

import com.iamkurtgoz.easypreference.Builder;
import com.iamkurtgoz.easypreference.EasyPreference;
import com.iamkurtgoz.easypreference.PreferenceMode;

public class SampleApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        /*Builder builder = new Builder(getApplicationContext(), "mypreference", PreferenceMode.MODE_PRIVATE);
        EasyPreference.createPreference(builder);*/

        EasyPreference.with(getApplicationContext(), "mypreference", PreferenceMode.MODE_PRIVATE).create();
    }
}
