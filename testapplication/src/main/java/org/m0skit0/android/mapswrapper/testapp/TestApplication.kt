package org.m0skit0.android.mapswrapper.testapp

import android.app.Application
import org.m0skit0.android.mapswrapper.MapType
import org.m0skit0.android.mapswrapper.MapsConfiguration

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapsConfiguration.type = MapType.HUAWEI
    }
}