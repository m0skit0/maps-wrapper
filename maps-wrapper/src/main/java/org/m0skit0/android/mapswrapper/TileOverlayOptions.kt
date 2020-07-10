package org.m0skit0.android.mapswrapper

class TileOverlayOptions {

    internal var google = com.google.android.gms.maps.model.TileOverlayOptions()
    internal var huawei = com.huawei.hms.maps.model.TileOverlayOptions()

    fun fadeIn(fadeIn: Boolean): TileOverlayOptions = apply {

    }
}
