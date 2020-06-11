package org.m0skit0.android.mapswrapper

import android.content.Context

object MapsInitializer {

    fun initialize(context: Context) {
        executeOrNull { com.google.android.gms.maps.MapsInitializer.initialize(context) }
        com.huawei.hms.maps.MapsInitializer.initialize(context)
    }
}
