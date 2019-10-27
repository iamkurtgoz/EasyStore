package com.iamkurtgoz.easystore;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.iamkurtgoz.easystore.architectures.EasyStoreArchitectureListener;
import com.iamkurtgoz.easystore.architectures.EasyStoreFilesCallback;

public class EasyStore implements EasyStoreArchitectureListener {

    protected static final String LAST_FILE_SAVE_PATH = "com.iamkurtgoz.easystory.LAST_FILE_SAVE_PATH";

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** INITALIZE *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    private static EasyStore easyStore;
    private SharedPreferences sharedPreferences;
    private static Context context;

    public static EasyStore getInstance(){
        if (easyStore == null){
            synchronized (EasyStore.class){
                if (easyStore == null){
                    easyStore = new EasyStore();
                }
            }
        }
        return easyStore;
    }

    public static EasyStoreVariables use() {
        return EasyStoreVariables.from(getInstance());
    }

    public static EasyStoreFiles useFile(String savePath, String saveExtension, EasyStoreFilesCallback easyStoreFilesCallback) {
        return EasyStoreFiles.from(getInstance(), context, savePath, saveExtension, false, easyStoreFilesCallback);
    }

    public static EasyStoreFiles useCacheFile(String saveExtension, EasyStoreFilesCallback easyStoreFilesCallback) {
        return EasyStoreFiles.from(getInstance(), context, "", saveExtension, true, easyStoreFilesCallback);
    }

    @Override
    public void init(Context ctx) {
        init(ctx, "default_preference_name", EasyStoreMode.MODE_PRIVATE);
    }

    public void init(Context ctx, String preferenceName, EasyStoreMode easyStoreMode) {
        context = ctx;
        int preferenceMode = ContextWrapper.MODE_PRIVATE;
        if (easyStoreMode == EasyStoreMode.MODE_PRIVATE){
            preferenceMode = ContextWrapper.MODE_PRIVATE;
        } else if (easyStoreMode == EasyStoreMode.MODE_WORLD_READABLE){
            preferenceMode = ContextWrapper.MODE_WORLD_READABLE;
        } else if (easyStoreMode == EasyStoreMode.MODE_WORLD_WRITEABLE){
            preferenceMode = ContextWrapper.MODE_WORLD_WRITEABLE;
        } else if (easyStoreMode == EasyStoreMode.MODE_MULTI_PROCESS){
            preferenceMode = ContextWrapper.MODE_MULTI_PROCESS;
        }

        if (context == null) {
            throw new RuntimeException("Context is a null");
        }
        sharedPreferences = context.getSharedPreferences(preferenceName, preferenceMode);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
