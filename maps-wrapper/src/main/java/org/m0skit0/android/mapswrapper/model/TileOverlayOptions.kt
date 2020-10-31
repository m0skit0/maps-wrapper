package org.m0skit0.android.mapswrapper.model

class TileOverlayOptions {

    internal var google = com.google.android.gms.maps.model.TileOverlayOptions()
    internal var huawei = com.huawei.hms.maps.model.TileOverlayOptions()

    fun fadeIn(fadeIn: Boolean): TileOverlayOptions = apply {
        google.fadeIn(fadeIn)
        huawei.fadeIn(fadeIn)
    }

    fun transparency(transparency: Float): TileOverlayOptions = apply {
        google.transparency(transparency)
        huawei.transparency(transparency)
    }

    fun visible(isVisible: Boolean): TileOverlayOptions = apply {
        google.visible(isVisible)
        huawei.visible(isVisible)
    }

    fun zIndex(index: Float): TileOverlayOptions = apply {
        google.zIndex(index)
        huawei.zIndex(index)
    }
}
