package com.iamkurtgoz.easystore.architectures;

import android.content.Context;
import android.content.SharedPreferences;

import com.iamkurtgoz.easystore.EasyStoreMode;

public interface EasyStoreArchitectureListener {

    void init(Context context);
    void init(Context context, String cryptKey);
    void init(Context context, String preferenceName, EasyStoreMode easyStoreMode);
    void init(Context context, String preferenceName, EasyStoreMode easyStoreMode, boolean cryptData, String cryptKey);
    SharedPreferences getSharedPreferences();
    Boolean isCrypt();
    String cryptPass();

}
