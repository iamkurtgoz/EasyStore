package com.iamkurtgoz.easystore.architectures;

import android.content.Context;
import android.content.SharedPreferences;

import com.iamkurtgoz.easystore.EasyStoreMode;

public interface EasyStoreArchitectureListener {

    void init(Context context);
    void init(Context context, String preferenceName, EasyStoreMode easyStoreMode);
    SharedPreferences getSharedPreferences();

}
