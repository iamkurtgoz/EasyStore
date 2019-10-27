package com.iamkurtgoz.easystore;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class EasyStoreVariables {

    private static SharedPreferences sharedPreferences;

    public static EasyStoreVariables from(EasyStore easyStore){
        return new EasyStoreVariables(easyStore);
    }

    private EasyStoreVariables(EasyStore easyStore){
        sharedPreferences = easyStore.getSharedPreferences();
    }

    private SharedPreferences.Editor getEditor(){
        if (sharedPreferences == null){
            throw new NullPointerException("EasyStoreError, Preference is a null.");
        }
        return sharedPreferences.edit();
    }

    public boolean hasExistStringKey(String key){
        boolean isExist = false;
        if (!get(key, "").equalsIgnoreCase("")){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistIntegerKey(String key){
        boolean isExist = false;
        if (get(key, 0) != 0){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistFloatKey(String key){
        boolean isExist = false;
        if (get(key, 0f) != 0f){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistBooleanKey(String key){
        boolean isExist = false;
        if (get(key, false)){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistLongKey(String key){
        boolean isExist = false;
        if (get(key, 0L) != 0L){
            isExist = true;
        }
        return isExist;
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** WRITE DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    public void set(String key, String value){
        set(key, (Object) value);
    }

    public void set(String key, Integer value){
        set(key, (Object) value);
    }

    public void set(String key, Float value){
        set(key, (Object) value);
    }

    public void set(String key, Boolean value){
        set(key, (Object) value);
    }

    public void set(String key, Long value){
        set(key, (Object) value);
    }

    public void set(String key, Set<String> value){
        set(key, (Object) value);
    }

    public void set(ArrayList<EasyModel> values){
        for (EasyModel model : values){
            String key = model.getKey();
            Object value = model.getValue();
            set(key, value);
        }
    }

    public void set(EasyModel ...values){
        for (EasyModel model : values){
            String key = model.getKey();
            Object value = model.getValue();
            set(key, value);
        }
    }


    private void set(String key, Object value){
        if (key == null || value == null){
            throw new NullPointerException("NullPointerException, key or value!");
        }
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
        } else if (value instanceof Set) {
            getEditor().putStringSet(key, (Set<String>) value).commit();
        } else {
            throw new UnknownError("Unknown object");
        }
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** READ DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    //Read String
    public String get(String key, String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public String getString(String key){
        return get(key, "");
    }

    //Read Integer
    public Integer get(String key, Integer defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public Integer getInteger(String key){
        return get(key, 0);
    }

    //Read Float
    public Float get(String key, Float defaultValue){
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public Float getFloat(String key){
        return get(key, 0f);
    }

    //Read Boolean
    public Boolean get(String key, Boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public Boolean getBoolean(String key){
        return get(key, false);
    }

    //Read Long
    public Long get(String key, Long defaultValue){
        return sharedPreferences.getLong(key, defaultValue);
    }

    public Long getLong(String key){
        return get(key, 0L);
    }

    //Read Set<String>
    public Set<String> get(String key, Set<String> defaultValue){
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public Set<String> getStringSet(String key){
        return get(key, new TreeSet<String>());
    }


    //FILES
    public String getFilePath(){
        return sharedPreferences.getString(EasyStore.LAST_FILE_SAVE_PATH, "not_found");
    }

    public String getFilePathFromKey(String key){
        return sharedPreferences.getString(key, "not_found");
    }

}
