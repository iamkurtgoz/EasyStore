package com.iamkurtgoz.samplepreference

import android.app.Application
import com.iamkurtgoz.easystore.EasyStore
import com.iamkurtgoz.easystore.EasyStoreMode

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        EasyStore.init(this, "defaultName", EasyStoreMode.MODE_PRIVATE)
    }
}