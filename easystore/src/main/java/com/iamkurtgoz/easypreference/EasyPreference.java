package com.iamkurtgoz.easypreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.iamkurtgoz.easypreference.files.Files;
import com.iamkurtgoz.easypreference.files.Utils;

@Deprecated
public class EasyPreference {

    private static Builder builder;
    private static SharedPreferences sharedPreferences;

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** DEPRECATED *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    @Deprecated
    public static Files FileToExternalStorage(String savePath, String saveExtension){
        return new Files(builder,getSharedPreferences(), getEditor(), savePath, saveExtension, false);
    }

    @Deprecated
    public static Files FileToCache(String saveExtension){
        return new Files(builder,getSharedPreferences(), getEditor(), "", saveExtension, true);
    }

    @Deprecated
    public static Builder with(Context myContext, String preferenceName, int mode){
        return new Builder(myContext, preferenceName, mode);
    }

    @Deprecated
    public static void initalize(Builder myBuilder){
        builder = myBuilder;
        sharedPreferences = builder.getContext().getSharedPreferences(builder.getPreferenceName(), builder.getPreferenceMode());
    }


    @Deprecated
    private static SharedPreferences getSharedPreferences(){
        if (sharedPreferences == null){
            throw new NullPointerException("EasyStoreError, Preference is a null.");
        }
        return sharedPreferences;
    }
    @Deprecated
    private static SharedPreferences.Editor getEditor(){
        if (sharedPreferences == null){
            throw new NullPointerException("EasyStoreError, Preference is a null.");
        }
        return sharedPreferences.edit();
    }

    @Deprecated
    public static void clearData(){
        getEditor().clear().apply();
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** WRITE DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    @Deprecated
    public static void writeObjects(EasyModel[] values){
        for (EasyModel model : values){
            String key = model.getKey();
            Object value = model.getValue();
            writeValue(key, value);
        }
    }

    @Deprecated
    public static void writeObject(String key, Object value){
        writeValue(key, value);
    }

    @Deprecated
    //Write
    public static void writeString(String key, String value){
        writeValue(key, value);
    }

    @Deprecated
    public static void writeInteger(String key, int value){
        writeValue(key, value);
    }

    @Deprecated
    public static void writeFloat(String key, float value){
        writeValue(key, value);
    }

    @Deprecated
    public static void writeLong(String key, long value){
        writeValue(key, value);
    }

    @Deprecated
    public static void writeBoolean(String key, boolean value){
        writeValue(key, value);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** READ DATA ********************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    //Read String
    public static String readString(String key){
        return readStringObject(key, "");
    }

    public static String readString(String key, String defaultValue){
        return readStringObject(key, defaultValue);
    }

    //Read Integer
    public static Integer readInteger(String key){
        return readIntegerObject(key, 0);
    }

    public static Integer readInteger(String key, int defaultValue){
        return readIntegerObject(key, defaultValue);
    }

    //Read Float
    public static Float readFloat(String key){
        return readFloatObject(key, 0);
    }

    public static Float readFloat(String key, float defaultObject){
        return readFloatObject(key, defaultObject);
    }

    //Read Boolean
    public static Boolean readBoolean(String key){
        return readBooleanObject(key, false);
    }

    public static Boolean readBoolean(String key, boolean defaultObject){
        return readBooleanObject(key, defaultObject);
    }

    //Read Long
    public static Long readLong(String key){
        return readLongObject(key, 0);
    }

    public static Long readLong(String key, long defaultObject){
        return readLongObject(key, defaultObject);
    }

    public static boolean hasExistKey(String key){
        boolean isExist = false;
        if (!readString(key, "").equalsIgnoreCase("")){
            isExist = true;
        }
        return isExist;
    }

    public static String readFileString(String key, String defaultValue){
        return readStringObject(Utils.md5(key), defaultValue);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**************************************** PROGRESS ********************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    private static void writeValue(String key, Object value){
        if (value instanceof String){
            getEditor().putString(key, (String) value).commit();
        } else if (value instanceof Integer){
            getEditor().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float){
            getEditor().putFloat(key, (Float) value).commit();
        } else if (value instanceof Boolean){
            getEditor().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Long){
            getEditor().putLong(key, (Long) value).commit();
        } else {
            throw new UnknownError("Unknown object");
        }
    }

    private static String readStringObject(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    private static Integer readIntegerObject(String key, int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    private static Float readFloatObject(String key, float defaultValue){
        return sharedPreferences.getFloat(key, defaultValue);
    }

    private static Boolean readBooleanObject(String key, boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    private static Long readLongObject(String key, long defaultValue){
        return sharedPreferences.getLong(key, defaultValue);
    }
}