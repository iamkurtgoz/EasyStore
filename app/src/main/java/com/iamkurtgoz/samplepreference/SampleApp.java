package com.iamkurtgoz.samplepreference;

import android.app.Application;

import com.iamkurtgoz.easystore.EasyStoreMode;
import com.iamkurtgoz.easystore.EasyStore;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EasyStore.getInstance().init(getApplicationContext(), "mypreference", EasyStoreMode.MODE_PRIVATE, true, "ASDASDASD");
    }
}
