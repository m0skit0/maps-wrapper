package org.m0skit0.android.mapswrapper.model

class TileOverlayOptions {

    internal var google = com.google.android.gms.maps.model.TileOverlayOptions()
    internal var huawei = com.huawei.hms.maps.model.TileOverlayOptions()

    val fadeIn: Boolean
        get() = google.fadeIn

    val transparency: Float
        get() = google.transparency

    val isVisible: Boolean
        get() = google.isVisible

    val zIndex: Float
        get() = google.zIndex

    var tileProvider: TileProvider? = null
        private set

    fun fadeIn(fadeIn: Boolean): TileOverlayOptions = apply {
        google = google.fadeIn(fadeIn)
        huawei.fadeIn(fadeIn)
    }

    fun transparency(transparency: Float): TileOverlayOptions = apply {
        google = google.transparency(transparency)
        huawei = huawei.transparency(transparency)
    }

    fun visible(isVisible: Boolean): TileOverlayOptions = apply {
        google = google.visible(isVisible)
        huawei = huawei.visible(isVisible)
    }

    fun zIndex(index: Float): TileOverlayOptions = apply {
        google = google.zIndex(index)
        huawei = huawei.zIndex(index)
    }

    fun tileProvider(tileProvider: TileProvider): TileOverlayOptions = apply {
        this.tileProvider = tileProvider
        google = google.tileProvider { x, y, zoom -> tileProvider.getTile(x, y, zoom)?.google }
        huawei = huawei.tileProvider { x, y, zoom -> tileProvider.getTile(x, y, zoom)?.huawei }
    }
}
