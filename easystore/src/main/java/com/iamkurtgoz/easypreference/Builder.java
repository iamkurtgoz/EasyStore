package com.iamkurtgoz.easypreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;

@Deprecated
public class Builder {


    private String preferenceName;
    private Context context;
    private int preferenceMode = PreferenceMode.MODE_PRIVATE;

    @Deprecated
    public Builder(Context context, String preferenceName, int preferenceMode){
        this.context = context;
        this.preferenceName = preferenceName;
        this.preferenceMode = preferenceMode;
    }

    @Deprecated
    public void create() {
        if (context == null) {
            throw new RuntimeException("Context is a null");
        }

        if (preferenceMode == -1) {
            preferenceMode = ContextWrapper.MODE_PRIVATE;
        }

        EasyPreference.initalize(this);
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
