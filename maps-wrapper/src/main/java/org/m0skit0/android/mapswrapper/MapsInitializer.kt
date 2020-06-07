package org.m0skit0.android.mapswrapper

import android.content.Context
import com.google.android.gms.maps.MapsInitializer

object MapsInitializer {

    fun initialize(context: Context) {
        executeOrNull { MapsInitializer.initialize(context) }
        com.huawei.hms.maps.MapsInitializer.initialize(context)
    }
}
