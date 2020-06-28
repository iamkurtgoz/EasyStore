package com.iamkurtgoz.easystore

import android.content.Context

class EasyStoreBuilder {

    companion object {

        @Volatile private var instance: EasyStore? = null
        private val lock = Any()

        operator fun invoke(context: Context, preferenceName: String = "default_preference_name", easyStoreMode: EasyStoreMode = EasyStoreMode.MODE_PRIVATE) : EasyStore = instance ?: synchronized(lock) {
            instance ?: createEasyStore(context, preferenceName, easyStoreMode).also {
                instance = it
            }
        }

        private fun createEasyStore(context: Context, preferenceName: String?, easyStoreMode: EasyStoreMode) : EasyStore {
            return EasyStore.getInstance().init(context, preferenceName, easyStoreMode)
        }
    }

}