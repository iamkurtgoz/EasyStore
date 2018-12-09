package com.iamkurtgoz.easypreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;

public class Builder {

    private String preferenceName;
    private Context context;
    private int preferenceMode = PreferenceMode.MODE_PRIVATE;

    public Builder(Context context, String preferenceName, int preferenceMode){
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

        EasyPreference.createPreference(this);
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
}
