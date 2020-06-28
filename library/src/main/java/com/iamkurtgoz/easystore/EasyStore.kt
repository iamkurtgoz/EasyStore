package com.iamkurtgoz.easystore

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList

object EasyStore {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context, preferenceName: String = "default_preference_name", easyStoreMode: EasyStoreMode = EasyStoreMode.MODE_PRIVATE) {
        if (!this::sharedPreferences.isInitialized) {
            sharedPreferences = context.getSharedPreferences(preferenceName, getEasyStorePreferenceMode(easyStoreMode))
        }
    }

    private fun getEasyStorePreferenceMode(easyStoreMode: EasyStoreMode): Int {
        return when (easyStoreMode) {
            EasyStoreMode.MODE_PRIVATE -> {
                ContextWrapper.MODE_PRIVATE
            }
            EasyStoreMode.MODE_WORLD_READABLE -> {
                ContextWrapper.MODE_WORLD_READABLE
            }
            EasyStoreMode.MODE_WORLD_WRITEABLE -> {
                ContextWrapper.MODE_WORLD_WRITEABLE
            }
            EasyStoreMode.MODE_MULTI_PROCESS -> {
                ContextWrapper.MODE_MULTI_PROCESS
            }
        }
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

    fun save(key: String, value: String?){
        EasyStoreExtension(this@EasyStore).saveStringValue(key, value)
    }

    fun readString(key: Enum<*>?): String {
        return readString(key.toString(), "")
    }

    fun readString(key: String, defaultValue: String = ""): String {
        return EasyStoreExtension(this@EasyStore).readStringValue(key, defaultValue)
    }

    fun existString(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /************************************* INTEGER DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String, value: Int?){
        EasyStoreExtension(this@EasyStore).saveIntegerValue(key, value)
    }

    fun readInt(key: Enum<*>?): Int {
        return readInt(key.toString(), 0)
    }

    fun readInt(key: String?, defaultValue: Int = 0): Int {
        return EasyStoreExtension(this@EasyStore).readIntegerValue(key, defaultValue)
    }

    fun existInt(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** BOOLEAN DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String, value: Boolean?){
        EasyStoreExtension(this@EasyStore).saveBooleanValue(key, value)
    }

    fun readBoolean(key: Enum<*>?): Boolean {
        return readBoolean(key.toString(), false)
    }

    fun readBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return EasyStoreExtension(this@EasyStore).readBooleanValue(key, defaultValue)
    }

    fun existBoolean(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** FLOAT DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String, value: Float?){
        EasyStoreExtension(this@EasyStore).saveFloatValue(key, value)
    }

    fun readFloat(key: Enum<*>?): Float {
        return readFloat(key.toString(), 0f)
    }

    fun readFloat(key: String, defaultValue: Float = 0.0f): Float {
        return EasyStoreExtension(this@EasyStore).readFloatValue(key, defaultValue)
    }

    fun existFloat(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*************************************** LONG DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String, value: Long?){
        EasyStoreExtension(this@EasyStore).saveLongValue(key, value)
    }

    fun readLong(key: Enum<*>?): Long {
        return readLong(key.toString(), 0L)
    }

    fun readLong(key: String, defaultValue: Long = 0L): Long {
        return EasyStoreExtension(this@EasyStore).readLongValue(key, defaultValue)
    }

    fun existLong(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /********************************** STRING SET DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(key: String, value: Set<String>?){
        EasyStoreExtension(this@EasyStore).saveStringSetValue(key, value)
    }

    fun readStringSet(key: Enum<*>?): Set<String> {
        return readStringSet(key.toString(), TreeSet())
    }

    fun readStringSet(key: String, defaultValue: Set<String> = TreeSet()): Set<String> {
        return EasyStoreExtension(this@EasyStore).readStringSetValue(key, defaultValue)
    }

    fun existStringSet(key: String): Boolean{
        return EasyStoreExtension(this@EasyStore).isSavedStringValue(key)
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /********************************** EASY MODEL DATA *******************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    fun save(list: ArrayList<EasyModel>){
        list.forEach {
            val easyModel = it
            when (easyModel.value) {
                is String -> {
                    save(easyModel.key, easyModel.value.toString())
                }
                is Int -> {
                    save(easyModel.key, easyModel.value.toInt())
                }
                is Boolean -> {
                    save(easyModel.key, easyModel.value.toString().toBoolean())
                }
                is Float -> {
                    save(easyModel.key, easyModel.value.toFloat())
                }
                is Long -> {
                    save(easyModel.key, easyModel.value.toLong())
                }
            }
        }
    }

    fun save(list: List<EasyModel>){
        list.forEach {
            val easyModel = it
            when (easyModel.value) {
                is String -> {
                    save(easyModel.key, easyModel.value.toString())
                }
                is Int -> {
                    save(easyModel.key, easyModel.value.toInt())
                }
                is Boolean -> {
                    save(easyModel.key, easyModel.value.toString().toBoolean())
                }
                is Float -> {
                    save(easyModel.key, easyModel.value.toFloat())
                }
                is Long -> {
                    save(easyModel.key, easyModel.value.toLong())
                }
            }
        }
    }
}