package com.iamkurtgoz.easystore

internal class EasyStoreExtension(private val easyStore: EasyStore){

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** STRING DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveStringValue(key: String?, value: String?){
        if (key == null || value == null){
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putString(key, value).commit()
    }

    fun readStringValue(key: String?, defaultValue: String?): String {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getString(key, defaultValue)!!
    }

    fun isSavedStringValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** INTEGER DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveIntegerValue(key: String?, value: Int?){
        if (key == null || value == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putInt(key, value).commit()
    }

    fun readIntegerValue(key: String?, defaultValue: Int?): Int {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getInt(key, defaultValue)
    }

    fun isSavedIntegerValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** BOOLEAN DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveBooleanValue(key: String?, value: Boolean?){
        if (key == null || value == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putBoolean(key, value).commit()
    }

    fun readBooleanValue(key: String?, defaultValue: Boolean?): Boolean {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getBoolean(key, defaultValue)
    }

    fun isSavedBooleanValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** FLOAT DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveFloatValue(key: String?, value: Float?){
        if (key == null || value == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putFloat(key, value).commit()
    }

    fun readFloatValue(key: String?, defaultValue: Float?): Float {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getFloat(key, defaultValue)
    }

    fun isSavedFloatValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** LONG DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveLongValue(key: String?, value: Long?){
        if (key == null || value == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putLong(key, value).commit()
    }

    fun readLongValue(key: String?, defaultValue: Long?): Long {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getLong(key, defaultValue)
    }

    fun isSavedLongValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /********************************** STRING SET DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun saveStringSetValue(key: String?, value: Set<String>?){
        if (key == null || value == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        easyStore.editor().putStringSet(key, value).commit()
    }

    fun readStringSetValue(key: String?, defaultValue: Set<String>?): Set<String> {
        if (key == null || defaultValue == null) {
            throw NullPointerException("NullPointerException, key or value!");
        }
        return easyStore.sharedPreference().getStringSet(key, defaultValue)!!
    }

    fun isSavedStringSetValue(key: String?): Boolean {
        return easyStore.sharedPreference().contains(key)
    }
}