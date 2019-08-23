package com.iamkurtgoz.easypreference;

import android.content.ContextWrapper;

@Deprecated
public interface PreferenceMode {
    int MODE_PRIVATE = ContextWrapper.MODE_PRIVATE;
    int MODE_WORLD_READABLE = ContextWrapper.MODE_WORLD_READABLE;
    int MODE_WORLD_WRITEABLE = ContextWrapper.MODE_WORLD_WRITEABLE;
    int MODE_MULTI_PROCESS = ContextWrapper.MODE_MULTI_PROCESS;
}
