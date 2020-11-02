package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.throwUnableToResolveGoogleOrHuawei

class TileOverlay(
    internal val google: com.google.android.gms.maps.model.TileOverlay?,
    internal val huawei: com.huawei.hms.maps.model.TileOverlay?
) {

    var fadeIn: Boolean
        get() = google?.fadeIn ?: huawei?.fadeIn ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.fadeIn = value
            huawei?.fadeIn = value
        }

    val id: String
        get() = google?.id ?: huawei?.id ?: throwUnableToResolveGoogleOrHuawei()

    var transparency: Float
        get() = google?.transparency ?: huawei?.transparency ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.transparency = value
            huawei?.transparency = value
        }

    var zIndex: Float
        get() = google?.zIndex ?: huawei?.zIndex ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.zIndex = value
            huawei?.zIndex = value
        }

    var isVisible: Boolean
        get() = google?.isVisible ?: huawei?.isVisible ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isVisible = value
            huawei?.isVisible = value
        }

    fun clearTileCache() {
        google?.clearTileCache()
        huawei?.clearTileCache()
    }

    fun remove() {
        google?.remove()
        huawei?.remove()
    }
}
