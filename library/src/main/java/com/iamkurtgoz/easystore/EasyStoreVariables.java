package com.iamkurtgoz.easystore;

import android.content.SharedPreferences;

import com.iamkurtgoz.easystore.crypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class EasyStoreVariables {

    private static SharedPreferences sharedPreferences;
    private EasyStore easyStore;

    public static EasyStoreVariables from(EasyStore easyStore){
        return new EasyStoreVariables(easyStore);
    }

    private EasyStoreVariables(EasyStore easyStore){
        this.easyStore = easyStore;
        sharedPreferences = easyStore.getSharedPreferences();
    }

    private SharedPreferences.Editor getEditor(){
        if (sharedPreferences == null){
            throw new NullPointerException("EasyStoreError, Preference is a null.");
        }
        return sharedPreferences.edit();
    }

    public boolean hasExistStringKey(Enum key){
        return hasExistStringKey(key.name());
    }

    public boolean hasExistStringKey(String key){
        boolean isExist = false;
        if (!get(key, "").equalsIgnoreCase("")){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistIntegerKey(Enum key){
        return hasExistIntegerKey(key.name());
    }

    public boolean hasExistIntegerKey(String key){
        boolean isExist = false;
        if (get(key, 0) != 0){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistFloatKey(Enum key){
        return hasExistFloatKey(key.name());
    }

    public boolean hasExistFloatKey(String key){
        boolean isExist = false;
        if (get(key, 0f) != 0f){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistBooleanKey(Enum key){
        return hasExistBooleanKey(key.name());
    }

    public boolean hasExistBooleanKey(String key){
        boolean isExist = false;
        if (get(key, false)){
            isExist = true;
        }
        return isExist;
    }

    public boolean hasExistLongKey(Enum key){
        return hasExistLongKey(key.name());
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

    public void set(Enum key, String value){
        set(key.name(), (Object) value);
    }

    public void set(String key, String value){
        set(key, (Object) value);
    }

    public void set(Enum key, Integer value){
        set(key.name(), (Object) value);
    }
    public void set(String key, Integer value){
        set(key, (Object) value);
    }

    public void set(Enum key, Float value){
        set(key.name(), (Object) value);
    }

    public void set(String key, Float value){
        set(key, (Object) value);
    }

    public void set(Enum key, Boolean value){
        set(key.name(), (Object) value);
    }
    public void set(String key, Boolean value){
        set(key, (Object) value);
    }

    public void set(Enum key, Long value){
        set(key.name(), (Object) value);
    }
    public void set(String key, Long value){
        set(key, (Object) value);
    }

    public void set(Enum key, Set<String> value){
        set(key.name(), (Object) value);
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
            String stringValue = (String) value;
            if (easyStore.isCrypt()){
                try {
                    stringValue = AESCrypt.encrypt(easyStore.cryptPass(), String.valueOf(value));
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            getEditor().putString(key, stringValue).commit();
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
        String data = sharedPreferences.getString(key, defaultValue);
        if (easyStore.isCrypt()){
            try {
                data = AESCrypt.decrypt(easyStore.cryptPass(), data);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public String get(Enum key, String defaultValue){
        return get(key.name(), defaultValue);
    }

    public String getString(String key){
        return get(key, "");
    }

    public String getString(Enum key){
        return get(key, "");
    }

    //Read Integer
    public Integer get(String key, Integer defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }

    public Integer get(Enum key, Integer defaultValue){
        return sharedPreferences.getInt(key.name(), defaultValue);
    }

    public Integer getInteger(String key){
        return get(key, 0);
    }

    public Integer getInteger(Enum key){
        return get(key, 0);
    }

    //Read Float
    public Float get(String key, Float defaultValue){
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public Float get(Enum key, Float defaultValue){
        return sharedPreferences.getFloat(key.name(), defaultValue);
    }

    public Float getFloat(String key){
        return get(key, 0f);
    }

    public Float getFloat(Enum key){
        return get(key, 0f);
    }

    //Read Boolean
    public Boolean get(String key, Boolean defaultValue){
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public Boolean get(Enum key, Boolean defaultValue){
        return sharedPreferences.getBoolean(key.name(), defaultValue);
    }

    public Boolean getBoolean(String key){
        return get(key, false);
    }

    public Boolean getBoolean(Enum key){
        return get(key, false);
    }

    //Read Long
    public Long get(String key, Long defaultValue){
        return sharedPreferences.getLong(key, defaultValue);
    }

    public Long get(Enum key, Long defaultValue){
        return sharedPreferences.getLong(key.name(), defaultValue);
    }

    public Long getLong(String key){
        return get(key, 0L);
    }

    public Long getLong(Enum key){
        return get(key, 0L);
    }

    //Read Set<String>
    public Set<String> get(String key, Set<String> defaultValue){
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public Set<String> get(Enum key, Set<String> defaultValue){
        return sharedPreferences.getStringSet(key.name(), defaultValue);
    }

    public Set<String> getStringSet(String key){
        return get(key, new TreeSet<String>());
    }

    public Set<String> getStringSet(Enum key){
        return get(key, new TreeSet<String>());
    }


    //FILES
    public String getFilePath(){
        return sharedPreferences.getString(EasyStore.LAST_FILE_SAVE_PATH, "not_found");
    }

    public String getFilePathFromKey(String key){
        return sharedPreferences.getString(key, "not_found");
    }

    public String getFilePathFromKey(Enum key){
        return sharedPreferences.getString(key.name(), "not_found");
    }
}
