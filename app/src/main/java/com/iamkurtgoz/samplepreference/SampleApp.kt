package com.iamkurtgoz.samplepreference

import android.app.Application
import com.iamkurtgoz.easystore.EasyStore
import com.iamkurtgoz.easystore.EasyStoreBuilder
import com.iamkurtgoz.easystore.EasyStoreMode

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //EasyStore.getInstance().init(this, "defaultName", EasyStoreMode.MODE_PRIVATE)

        EasyStoreBuilder(this)
    }
}