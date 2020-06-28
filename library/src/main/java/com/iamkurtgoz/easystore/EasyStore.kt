package com.iamkurtgoz.easystore

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.NullPointerException
import java.util.*

object EasyStore {

    private lateinit var easyStore: EasyStore
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var context: Context
    private var liveData = MutableLiveData<EasyState>()

    internal fun getInstance(): EasyStore {
        if (!this::easyStore.isInitialized) {
            synchronized(EasyStore::class.java) {
                easyStore = EasyStore
            }
        }
        return easyStore
    }

    internal fun init(context: Context?, preferenceName: String?, easyStoreMode: EasyStoreMode): EasyStore {
        if (!this::easyStore.isInitialized){
            throw RuntimeException("Error initialized. First call EasyStoreBuilder.invoke(..) or EasyStoreBuilder(..)")
        }

        if (context == null){
            throw RuntimeException("Context is null!")
        } else if (!this::context.isInitialized){
            this.context = context
            sharedPreferences = this.context.getSharedPreferences(preferenceName, getEasyStorePreferenceMode(easyStoreMode))
        }
        return this
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

    fun liveData(): MutableLiveData<EasyState> {
        return liveData
    }

    /************************************************************************************************
     ** SAVE DATA
     ************************************************************************************************/

    fun save(key: Enum<*>, value: Any?){
        save(key.name, value)
    }

    fun save(key: String, value: Any?){
        value?.let { safeValue->
            when(safeValue){
                is String -> sharedPreferences.edit(commit = true){
                    putString(key, safeValue)
                }
                is Int -> sharedPreferences.edit(commit = true){
                    putInt(key, safeValue)
                }
                is Long -> sharedPreferences.edit(commit = true){
                    putLong(key, safeValue)
                }
                is Float -> sharedPreferences.edit(commit = true){
                    putFloat(key, safeValue)
                }
                is Boolean -> sharedPreferences.edit(commit = true){
                    putBoolean(key, safeValue)
                }
            }
            liveData.postValue(EasyState.onDataSaved(key, value))
        }
    }

    fun save(key: String, value: Set<String>?){
        value?.let { safeValue ->
            sharedPreferences.edit(commit = true){
                putStringSet(key, safeValue)
            }
            liveData.postValue(EasyState.onDataSaved(key, value))
        }
    }

    fun save(list: ArrayList<EasyModel>){
        list.forEach {
            when(it.value){
                is String -> save(it.key, it.value)
                is Int -> save(it.key, it.value)
                is Boolean -> save(it.key, it.value)
                is Float -> save(it.key, it.value)
                is Long -> save(it.key, it.value)
            }
        }
    }

    fun save(list: List<EasyModel>){
        list.forEach {
            when(it.value){
                is String -> save(it.key, it.value)
                is Int -> save(it.key, it.value)
                is Boolean -> save(it.key, it.value)
                is Float -> save(it.key, it.value)
                is Long -> save(it.key, it.value)
            }
        }
    }

    /************************************************************************************************
     ** READ DATA
     ************************************************************************************************/

    //String
    fun readString(key: Enum<*>, defaultValue: String = ""): String {
        return readString(key.name, defaultValue)
    }

    fun readString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: return defaultValue
    }

    fun existString(key: Enum<*>): Boolean {
        return existString(key.name)
    }

    fun existString(key: String): Boolean {
        return sharedPreferences.getString(key, "__notfound") != "__notfound"
    }

    //Integer
    fun readInt(key: Enum<*>, defaultValue: Int = 0): Int {
        return readInt(key.name, defaultValue)
    }

    fun readInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun existInt(key: Enum<*>): Boolean {
        return existInt(key.name)
    }

    fun existInt(key: String): Boolean{
        return sharedPreferences.getInt(key, -1) != -1
    }

    //Long
    fun readLong(key: Enum<*>, defaultValue: Long = 0): Long {
        return readLong(key.name, defaultValue)
    }

    fun readLong(key: String, defaultValue: Long = 0): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun existLong(key: Enum<*>): Boolean {
        return existLong(key.name)
    }

    fun existLong(key: String): Boolean{
        return sharedPreferences.getLong(key, -1L) != -1L
    }

    //Float
    fun readFloat(key: Enum<*>, defaultValue: Float = 0F): Float {
        return readFloat(key.name, defaultValue)
    }

    fun readFloat(key: String, defaultValue: Float = 0F): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun existFloat(key: Enum<*>): Boolean {
        return existFloat(key.name)
    }

    fun existFloat(key: String): Boolean{
        return sharedPreferences.getFloat(key, -1F) != -1F
    }

    //Boolean
    fun readBoolean(key: Enum<*>, defaultValue: Boolean = false): Boolean {
        return readBoolean(key.name, defaultValue)
    }

    fun readBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun existBoolean(key: Enum<*>): Boolean {
        return existBoolean(key.name)
    }

    fun existBoolean(key: String): Boolean{
        return sharedPreferences.getBoolean(key, false)
    }

    //StringSet
    fun readStringSet(key: Enum<*>, defaultValue: Set<String> = TreeSet()): Set<String> {
        return readStringSet(key.name, defaultValue)
    }

    fun readStringSet(key: String, defaultValue: Set<String> = TreeSet()): Set<String> {
        return sharedPreferences.getStringSet(key, defaultValue) ?: TreeSet<String>()
    }

    fun existStringSet(key: Enum<*>): Boolean {
        return existStringSet(key.name)
    }

    fun existStringSet(key: String): Boolean{
        return sharedPreferences.getStringSet(key, TreeSet()) != TreeSet<String>()
    }
}