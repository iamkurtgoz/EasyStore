package com.iamkurtgoz.easystore

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList

object EasyStore {

    private lateinit var easyStore: EasyStore
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var context: Context

    fun getInstance(): EasyStore {
        if (!this::easyStore.isInitialized) {
            synchronized(EasyStore::class.java) {
                easyStore = EasyStore
            }
        }
        return easyStore
    }

    fun init(context: Context?){
        init(context, "default_preference_name", EasyStoreMode.MODE_PRIVATE)
    }

    fun init(ctx: Context?, cryptKey: String) {
        init(ctx, "default_preference_name", EasyStoreMode.MODE_PRIVATE)
    }

    fun init(ctx: Context?, preferenceName: String?, easyStoreMode: EasyStoreMode) {
        if (!this::easyStore.isInitialized){
            throw RuntimeException("Error EasyStore.getInstance()!")
        }

        if (ctx == null){
            throw RuntimeException("Context is null!")
        } else if (!this::context.isInitialized){
            this.context = ctx
            sharedPreferences = this.context.getSharedPreferences(preferenceName, getEasyStorePreferenceMode(easyStoreMode))
        }
    }

    private fun getEasyStorePreferenceMode(easyStoreMode: EasyStoreMode): Int {
        var preferenceMode = ContextWrapper.MODE_PRIVATE
        if (easyStoreMode == EasyStoreMode.MODE_PRIVATE) {
            preferenceMode = ContextWrapper.MODE_PRIVATE
        } else if (easyStoreMode == EasyStoreMode.MODE_WORLD_READABLE) {
            preferenceMode = ContextWrapper.MODE_WORLD_READABLE
        } else if (easyStoreMode == EasyStoreMode.MODE_WORLD_WRITEABLE) {
            preferenceMode = ContextWrapper.MODE_WORLD_WRITEABLE
        } else if (easyStoreMode == EasyStoreMode.MODE_MULTI_PROCESS) {
            preferenceMode = ContextWrapper.MODE_MULTI_PROCESS
        }
        return preferenceMode
    }

    fun editor(): SharedPreferences.Editor {
        if (!this::sharedPreferences.isInitialized){
            throw NullPointerException("SharedPreferences is null!")
        }
        return sharedPreferences.edit()
    }

    fun sharedPreference(): SharedPreferences {
        if (!this::sharedPreferences.isInitialized){
            throw NullPointerException("SharedPreferences is null!")
        }
        return sharedPreferences
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************** STRING DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: String?){
        EasyStoreExtension(getInstance()).saveStringValue(key, value)
    }

    fun readString(key: Enum<*>?): String {
        return readString(key.toString(), "")
    }

    fun readString(key: String?): String {
        return readString(key, "")
    }

    fun readString(key: String?, defaultValue: String?): String {
        return EasyStoreExtension(getInstance()).readStringValue(key, defaultValue)
    }

    fun existString(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************* INTEGER DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: Int?){
        EasyStoreExtension(getInstance()).saveIntegerValue(key, value)
    }

    fun readInt(key: Enum<*>?): Int {
        return readInt(key.toString(), 0)
    }

    fun readInt(key: String?): Int {
        return readInt(key, 0)
    }

    fun readInt(key: String?, defaultValue: Int?): Int {
        return EasyStoreExtension(getInstance()).readIntegerValue(key, defaultValue)
    }

    fun existInt(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** BOOLEAN DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: Boolean?){
        EasyStoreExtension(getInstance()).saveBooleanValue(key, value)
    }

    fun readBoolean(key: Enum<*>?): Boolean {
        return readBoolean(key.toString(), false)
    }

    fun readBoolean(key: String?): Boolean {
        return readBoolean(key, false)
    }

    fun readBoolean(key: String?, defaultValue: Boolean?): Boolean {
        return EasyStoreExtension(getInstance()).readBooleanValue(key, defaultValue)
    }

    fun existBoolean(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** FLOAT DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: Float?){
        EasyStoreExtension(getInstance()).saveFloatValue(key, value)
    }

    fun readFloat(key: Enum<*>?): Float {
        return readFloat(key.toString(), 0f)
    }

    fun readFloat(key: String?): Float {
        return readFloat(key, 0f)
    }

    fun readFloat(key: String?, defaultValue: Float?): Float {
        return EasyStoreExtension(getInstance()).readFloatValue(key, defaultValue)
    }

    fun existFloat(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** LONG DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: Long?){
        EasyStoreExtension(getInstance()).saveLongValue(key, value)
    }

    fun readLong(key: Enum<*>?): Long {
        return readLong(key.toString(), 0L)
    }

    fun readLong(key: String?): Long {
        return readLong(key, 0L)
    }

    fun readLong(key: String?, defaultValue: Long?): Long {
        return EasyStoreExtension(getInstance()).readLongValue(key, defaultValue)
    }

    fun existLong(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /********************************** STRING SET DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String?, value: Set<String>?){
        EasyStoreExtension(getInstance()).saveStringSetValue(key, value)
    }

    fun readStringSet(key: Enum<*>?): Set<String> {
        return readStringSet(key.toString(), TreeSet())
    }

    fun readStringSet(key: String?): Set<String> {
        return readStringSet(key, TreeSet())
    }

    fun readStringSet(key: String?, defaultValue: Set<String>?): Set<String> {
        return EasyStoreExtension(getInstance()).readStringSetValue(key, defaultValue)
    }

    fun existStringSet(key: String?): Boolean{
        return EasyStoreExtension(getInstance()).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /********************************** EASY MODEL DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(list: ArrayList<EasyModel>){
        list.forEach {
            val easyModel = it
            if (easyModel.value is String){
                save(easyModel.key, easyModel.value.toString())
            } else if (easyModel.value is Int){
                save(easyModel.key, easyModel.value.toInt())
            } else if (easyModel.value is Boolean){
                save(easyModel.key, easyModel.value.toString().toBoolean())
            } else if (easyModel.value is Float){
                save(easyModel.key, easyModel.value.toFloat())
            } else if (easyModel.value is Long){
                save(easyModel.key, easyModel.value.toLong())
            }
        }
    }

    fun save(list: List<EasyModel>){
        list.forEach {
            val easyModel = it
            if (easyModel.value is String){
                save(easyModel.key, easyModel.value.toString())
            } else if (easyModel.value is Int){
                save(easyModel.key, easyModel.value.toInt())
            } else if (easyModel.value is Boolean){
                save(easyModel.key, easyModel.value.toString().toBoolean())
            } else if (easyModel.value is Float){
                save(easyModel.key, easyModel.value.toFloat())
            } else if (easyModel.value is Long){
                save(easyModel.key, easyModel.value.toLong())
            }
        }
    }
}